package ru.netology.manager;
import org.junit.jupiter.api.Test;
import ru.netology.service.Book;
import ru.netology.service.Product;
import ru.netology.service.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book = new Book(100, "First book about iPhone", 400, "First author");
    Product smartphone = new Smartphone(120, "iPhone", 80000, "Apple");
    Product product = new Product(130, "Milk", 80);
    @Test
    void shouldAdd() {
        manager.add(book);
        Product[] expected = {book};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldAddAll() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        Product[] expected = { book, smartphone, product };
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldSearchBy() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        String name = "First";
        Product[] expected = {book};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldSearchWhenFewProductsSuit() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        String name = "iPhone";
        Product[] expected = { book, smartphone };
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldSearchWhenProductsNotSuit() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        String name = "Samsung";
        Product[] expected = {};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }
}

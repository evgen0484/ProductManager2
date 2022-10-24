package ru.netology.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.exception.NotFoundException;
import ru.netology.service.Book;
import ru.netology.service.Product;
import ru.netology.service.Smartphone;
import static org.junit.jupiter.api.Assertions.*;


public class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    Product book = new Book(100, "First Book", 400, "First Author");
    Product smartphone = new Smartphone(120, "iPhone", 80000, "Apple");
    Product product = new Product(130, "Milk", 80);


    @BeforeEach
    void setup() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
    }

    @Test
    void shouldRemoveIdSuccess() {
        Product[] expected = new Product[]{
                book,
                product
                };
        int id = 120;
        repository.removeById(id);
        assertArrayEquals(expected, repository.findAll());
    }

    @Test
    void shouldRemoveByIdFailed() {
        int id = 121;
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(id);
        });
    }
}

package ru.netology.repository;
import ru.netology.exception.NotFoundException;
import ru.netology.service.Product;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void save(Product item) {
       int length = products.length + 1;
       Product[] tmp = new Product[length];
       System.arraycopy(products, 0, tmp, 0, products.length);
       int lastIndex = tmp.length - 1;
       tmp[lastIndex] = item;
       products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Товар с ID: " + id + " не найден!!!");
        }
        Product[] tmp = new Product[products.length - 1];
        int i = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[i] = product;
                i++;
            }
        }
        products = tmp;
    }

    //поиска товара в репозитории по его id
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}

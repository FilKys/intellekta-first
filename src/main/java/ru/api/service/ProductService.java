package ru.api.service;

import ru.api.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Object id);

    Product create(Product product);

    Product update(Product product);

    void delete(Object id);
}

package ru.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.api.entity.Product;
import ru.api.entity.SalesPeriod;
import ru.api.exceptions.EntityAlreadyExistsException;
import ru.api.exceptions.EntityHasDetailsException;
import ru.api.exceptions.EntityIllegalArgumentException;
import ru.api.exceptions.EntityNotFoundException;
import ru.api.jpa.ProductRepository;
import ru.api.jpa.SalesPeriodRepository;
import ru.api.service.ProductService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultProductService implements ProductService {

    private ProductRepository productRepository;

    private SalesPeriodRepository salesPeriodRepository;

    @Autowired
    public DefaultProductService(ProductRepository productRepository, SalesPeriodRepository salesPeriodRepository) {
        this.productRepository = productRepository;
        this.salesPeriodRepository = salesPeriodRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Object id) {
        Product product;
        if (id == null) {
            throw new EntityIllegalArgumentException("Индификатор объекта не может быть null");
        }
        Integer productId;
        try {
            productId = Integer.valueOf((String) id);
        } catch (NumberFormatException ex) {
            throw new EntityIllegalArgumentException(String.format("Не удалось преоброзавать идентификатор " +
                    "к нужному типу, текст ошибки: %s", ex));
        }
        try {
            product = productRepository.findById(productId).get();
        } catch (NoSuchElementException ex) {
            throw new EntityNotFoundException(Product.TYPE_NAME, productId);
        }

        return product;
    }

    public Product create(Product product) {
        if (product == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (product.getId() == null) {
            throw new EntityIllegalArgumentException("Индификатор объекта не может быть null");
        }
        try {
            Product existedProduct = productRepository.findById(product.getId()).get();
        } catch (NoSuchElementException ex) {
            return productRepository.save(product);
        }
        throw new EntityAlreadyExistsException(Product.TYPE_NAME, product.getId());
    }

    @Override
    public Product update(Product product) {
        if (product == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (product.getId() == null) {
            throw new EntityIllegalArgumentException("Индификатор объекта не может быть null");
        }
        try {
            Product existedProduct = productRepository.findById(product.getId()).get();
        } catch (NoSuchElementException ex) {
            throw new EntityNotFoundException(Product.TYPE_NAME, product.getId());
        }
        return productRepository.save(product);
    }

    public void delete(Object id) {
        Product product = findById(id);
        List<SalesPeriod> salesPeriods = salesPeriodRepository.findByProduct(product);
        if (salesPeriods.size() > 0) {
            throw new EntityHasDetailsException(SalesPeriod.TYPE_NAME, product.getId());
        }
        productRepository.delete(product);
    }
}

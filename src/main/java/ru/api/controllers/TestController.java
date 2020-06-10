package ru.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.api.entity.Product;
import ru.api.entity.SalesPeriodJdbcDemo;
import ru.api.entity.SalesPeriodJpaDemo;
import ru.api.jdbc.SalesProductJdbcRepository;
import ru.api.jpa.ProductRepository;
import ru.api.jpa.SalesPeriodRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TestController {

    private final ProductRepository productRepository;

    private final SalesProductJdbcRepository salesProductJdbcRepository;

    private final SalesPeriodRepository salesPeriodRepository;

    @Autowired
    public TestController(ProductRepository productRepository, SalesProductJdbcRepository salesProductJdbcRepository, SalesPeriodRepository salesPeriodRepository) {
        this.productRepository = productRepository;
        this.salesProductJdbcRepository = salesProductJdbcRepository;
        this.salesPeriodRepository = salesPeriodRepository;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello, World!";
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }


    @GetMapping("/sales/count")
    public Integer getSalesCount()
    {
        return salesProductJdbcRepository.count();
    }

    @GetMapping("/sales")
    public List<SalesPeriodJdbcDemo> getSalesPeriod(){
        return salesProductJdbcRepository.getSalesPeriods();
    }

    @GetMapping("/sales/byhigherprice")
    public List<SalesPeriodJdbcDemo> getSalesPeriodsByHigherPrice(){
        return salesProductJdbcRepository.getSalesPeriodsIsHigher(90);
    }

    @GetMapping("/products/sales/active")
    public List<Product> getProductsWhitActivePeriod() {
        return salesProductJdbcRepository.getProductWithActivePeriod();
    }

    @GetMapping("/sales/jpa")
    public List<SalesPeriodJpaDemo> getSalesPeriodsJpa() {
        return salesPeriodRepository.findAll();
    }

    @PostMapping("/sales/jpa")
    public SalesPeriodJpaDemo addSalesPeriodsJpa(@RequestBody SalesPeriodJpaDemo salesPeriodJpaDemo) {
        return salesPeriodRepository.save(salesPeriodJpaDemo);
    }

    @GetMapping("/sales/jpa/max/price")
    public Integer getMaxPrice() {
        return salesPeriodRepository.getMaxPriceByProduct(1);
    }

    @GetMapping("/sales/jpa/exists/price")
    public boolean existsByPrice() {
        return salesPeriodRepository.existsByPrice(60);
    }

    @GetMapping("/sales/jpa/active")
    public List<SalesPeriodJpaDemo> findByDateToIsNull() {
        return salesPeriodRepository.findByDateToIsNull();
    }

    @GetMapping("/sales/jpa/byproductname")
    public List<SalesPeriodJpaDemo> findByProductName() {
        return salesPeriodRepository.findByProductName("bike");
    }
}

package service;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.api.entity.Product;
import ru.api.exceptions.EntityAlreadyExistsException;
import ru.api.exceptions.EntityHasDetailsException;
import ru.api.exceptions.EntityIllegalArgumentException;
import ru.api.exceptions.EntityNotFoundException;
import ru.api.service.ProductService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findAllTest() {
        List<Product> products = productService.findAll();
        Assert.assertEquals(products.size(), 3);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByIdNullId() {
        productService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByIdNotInteger() {
        productService.findById("aaa");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFound() {
        productService.findById("1000");
    }

    @Test
    public void findBiIdTest() {
        Product product = productService.findById("1");
        Assert.assertNotNull(product);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullProductException() {
        productService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullProductIdException() {
        productService.create(new Product(null, "aa"));
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createProductIdException() {
        productService.create(new Product(1, "aa"));
    }

    @Test
    public void createProductTest() {
        productService.create(new Product(3, "car"));
    }

    @Test(expected = EntityHasDetailsException.class)
    public void deleteProductException() {
        productService.delete("2");
    }

    @Test
    public void deleteProductTest() {
        productService.create(new Product(3, "car"));
        productService.delete("3");
    }
}

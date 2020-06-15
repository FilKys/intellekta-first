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
import ru.api.service.impl.DefaultProductService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class ProductServiceTest {

    @Autowired
    private DefaultProductService defaultProductService;

    @Test
    public void findAllTest() {
        List<Product> products = defaultProductService.findAll();
        Assert.assertEquals(products.size(), 3);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByIdNullId() {
        defaultProductService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByIdNotInteger() {
        defaultProductService.findById("aaa");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFound() {
        defaultProductService.findById("1000");
    }

    @Test
    public void findBiIdTest() {
        Product product = defaultProductService.findById("1");
        Assert.assertNotNull(product);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullProductException() {
        defaultProductService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullProductIdException() {
        defaultProductService.create(new Product(null, "aa"));
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createProductIdException() {
        defaultProductService.create(new Product(1, "aa"));
    }

    @Test
    public void createProductTest() {
        defaultProductService.create(new Product(3, "car"));
    }

    @Test(expected = EntityHasDetailsException.class)
    public void deleteProductException() {
        defaultProductService.delete("2");
    }

    @Test
    public void deleteProductTest() {
        defaultProductService.create(new Product(3, "car"));
        defaultProductService.delete("3");
    }
}

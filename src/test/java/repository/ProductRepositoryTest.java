package repository;

import config.TestConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.api.entity.Product;
import ru.api.jpa.ProductRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void createProductTest() {
        Product product = new Product(3, "product_test");
        productRepository.save(product);
    }

    @Test
    public void findAllTest() {
        List<Product> products = productRepository.findAll();
        Assert.assertNotNull(products);
        Assert.assertEquals(products.size(), 3);
    }

    @Test
    public void findByName() {
        List<Product> products = productRepository.findByName("product_test");
        Assert.assertNotNull(products);
        Assert.assertEquals(products.size(), 1);
    }

    @After
    public void deleteProductTest() {
        productRepository.deleteById(3);
        List<Product> products = productRepository.findAll();
        Assert.assertNotNull(products);
        Assert.assertEquals(products.size(), 2);
    }
}

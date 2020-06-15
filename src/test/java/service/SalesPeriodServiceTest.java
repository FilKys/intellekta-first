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
import ru.api.entity.SalesPeriod;
import ru.api.exceptions.EntityConflictException;
import ru.api.exceptions.EntityIllegalArgumentException;
import ru.api.exceptions.EntityNotFoundException;
import ru.api.service.SalesPeriodService;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class SalesPeriodServiceTest {

    @Autowired
    private SalesPeriodService salesPeriodService;

    @Test
    public void findAllTest() {
        List<SalesPeriod> salesPeriods = salesPeriodService.findAll();
        Assert.assertEquals(salesPeriods.size(), 6);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByIdNullId() {
        salesPeriodService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByIdNotInteger() {
        salesPeriodService.findById("aaa");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFound() {
        salesPeriodService.findById("1000");
    }

    @Test
    public void findByIdTest() {
        SalesPeriod salesPeriods = salesPeriodService.findById("1");
        Assert.assertNotNull(salesPeriods);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullSalesPeriodException() {
        salesPeriodService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullSalesPeriodIdException() {
        salesPeriodService.create(new SalesPeriod());
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createSalesPeriodNullProductException() {
        SalesPeriod salesPeriod = new SalesPeriod();
        salesPeriod.setId(1);
        salesPeriodService.create(salesPeriod);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createSalesPeriodNullProductIdException() {
        SalesPeriod salesPeriod = new SalesPeriod();
        salesPeriod.setId(1);
        salesPeriod.setProduct(new Product());
        salesPeriodService.create(salesPeriod);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createSalesPeriodNullDateFromException() {
        SalesPeriod salesPeriod = new SalesPeriod();
        salesPeriod.setId(1);
        salesPeriod.setProduct(new Product(1, "aa"));
        salesPeriod.setDateFrom(null);
        salesPeriodService.create(salesPeriod);
    }

    @Test(expected = EntityConflictException.class)
    public void createSalesPeriodException() {
        SalesPeriod salesPeriod = new SalesPeriod();
        salesPeriod.setId(7);
        salesPeriod.setPrice(100);
        salesPeriod.setProduct(new Product(2, null));
        salesPeriod.setDateFrom(new Date());
        salesPeriodService.create(salesPeriod);
    }

    @Test
    public void createSalesPeriodTest() {
        SalesPeriod salesPeriod = new SalesPeriod();
        salesPeriod.setId(7);
        salesPeriod.setPrice(100);
        salesPeriod.setProduct(new Product(1, "bicycle"));
        Date date = new Date();
        salesPeriod.setDateFrom(date);
        salesPeriodService.create(salesPeriod);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteIdNullId() {
        salesPeriodService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteIdNotInteger() {
        salesPeriodService.findById("aaa");
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteIdNotFound() {
        salesPeriodService.findById("1000");
    }

    @Test
    public void deleteSalesPeriodTest() {
        salesPeriodService.delete("2");
    }
}

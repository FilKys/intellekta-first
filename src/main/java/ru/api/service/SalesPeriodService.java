package ru.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.api.entity.SalesPeriod;
import ru.api.exceptions.EntityConflictException;
import ru.api.exceptions.EntityIllegalArgumentException;
import ru.api.exceptions.EntityNotFoundException;
import ru.api.jpa.SalesPeriodRepository;

import java.util.List;

@Service
public class SalesPeriodService {

    private SalesPeriodRepository salesPeriodRepository;

    @Autowired
    public SalesPeriodService(SalesPeriodRepository salesPeriodRepository) {
        this.salesPeriodRepository = salesPeriodRepository;
    }

    public List<SalesPeriod> findAll() {
        return salesPeriodRepository.findAll();
    }

    public SalesPeriod findById(Object id) {
        SalesPeriod salesPeriod;
        if (id == null) {
            throw new EntityIllegalArgumentException("Индификатор объекта не может быть null");
        }
        Integer salesPeriodId;
        try {
            salesPeriodId = Integer.valueOf((String) id);
        } catch (NumberFormatException ex) {
            throw new EntityIllegalArgumentException(String.format("Не удалось преоброзавать идентификатор " +
                    "к нужному типу, текст ошибки: %s", ex));
        }
        try {
            salesPeriod = salesPeriodRepository.findById(salesPeriodId).get(0);
        } catch (IndexOutOfBoundsException exception) {
            throw new EntityNotFoundException(SalesPeriod.TYPE_NAME, salesPeriodId);
        }
        return salesPeriod;
    }

    public SalesPeriod create(SalesPeriod salesPeriod) {
        if (salesPeriod == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (salesPeriod.getId() == null) {
            throw new EntityIllegalArgumentException("Индификатор объекта не может быть null");
        }
        if (salesPeriod.getProduct() == null) {
            throw new EntityIllegalArgumentException("Продукт не может быть null");
        }
        if (salesPeriod.getProduct().getId() == null) {
            throw new EntityIllegalArgumentException("Индификатор продукта не может быть null");
        }
        if (salesPeriod.getDateFrom() == null) {
            throw new EntityIllegalArgumentException("Дата начала периода не может быть null");
        }
        List<SalesPeriod> lastSalesPeriod = salesPeriodRepository.findByDateToIsNullAndProductId(salesPeriod.getProduct().getId());
        if (lastSalesPeriod.size() > 0) {
            throw new EntityConflictException(String.format("В системе имеются открытые торговые периоды для продукта с id %s", salesPeriod.getProduct().getId()));
        }
        return salesPeriodRepository.save(salesPeriod);
    }

    public void delete(Object id) {
        SalesPeriod salesPeriod = findById(id);
        salesPeriodRepository.delete(salesPeriod);
    }
}


package ru.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales_period")
@NoArgsConstructor
@Getter
@Setter
public class SalesPeriod {

    public static String TYPE_NAME = "Торговый период";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_periods_id_seq")
    @SequenceGenerator(name = "sales_periods_id_seq", sequenceName = "sales_periods_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "dateFrom", nullable = false)
    private Date dateFrom;

    @Column(name = "dateTo")
    private Date dateTo;

    @OneToOne
    @JoinColumn(name = "product", referencedColumnName = "id", nullable = false)
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

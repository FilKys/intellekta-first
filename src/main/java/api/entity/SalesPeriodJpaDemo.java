package api.entity;

import api.jpa.Product;
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
public class SalesPeriodJpaDemo {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_periods_id_seq")
    @SequenceGenerator(name = "sales_periods_id_seq", sequenceName = "sales_periods_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "dateFrom", nullable = false)
    private Date dateFrom;

    @Column(name = "dateTo", nullable = false)
    private Date dateTo;

    @OneToOne
    @JoinColumn(name = "product", referencedColumnName = "id", nullable = false)
    private Product product;
}

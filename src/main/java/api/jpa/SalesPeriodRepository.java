package api.jpa;

import api.entity.SalesPeriodJpaDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesPeriodRepository extends JpaRepository<SalesPeriodJpaDemo, Long> {

    @Query(value = "select max(price) from sales_period where product= :productId", nativeQuery = true)
    Integer getMaxPriceByProduct(@Param("productId") long productId);

    boolean existsByPrice(long price);

    List<SalesPeriodJpaDemo> findByDateToIsNull();

    List<SalesPeriodJpaDemo> findByProductName(String productName);
}

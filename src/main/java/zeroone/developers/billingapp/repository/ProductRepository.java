package zeroone.developers.billingapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zeroone.developers.billingapp.entity.Product;

public interface ProductRepository extends BaseRepository<Product, Long> {


    // Product name exists check
    @Query(value = "select count(*) > 0 from product p where p.name = :name", nativeQuery = true)
    boolean existsByName(@Param("name") String name);

    // Product ID exists check
    @Query(value = "select count(*) > 0 from product p where p.id = :id", nativeQuery = true)
    boolean existsByProductId(@Param("id") Long id);

}

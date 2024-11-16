package zeroone.developers.billingapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zeroone.developers.billingapp.entity.Category;

public interface CategoryRepository extends BaseRepository<Category, Long> {


    // Category name exists check
    @Query(value = "select count(*) > 0 from category c where c.name = :name", nativeQuery = true)
    boolean existsByName(@Param("name") String name);

}

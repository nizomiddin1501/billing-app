package zeroone.developers.billingapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zeroone.developers.billingapp.entity.Transaction;

import java.util.List;

public interface TransactionRepository extends BaseRepository<Transaction, Long> {

//    // User ID exists check
//    @Query(value = "select count(*) > 0 from users u where u.id = :id", nativeQuery = true)
//    boolean existsByUserId(@Param("id") Long id);

    // Product ID exists check
    @Query(value = "select count(*) > 0 from transaction t where t.id = :id", nativeQuery = true)
    boolean existsByTransactionId(@Param("id") Long id);

    List<Transaction> findByUserId(Long userId);

}

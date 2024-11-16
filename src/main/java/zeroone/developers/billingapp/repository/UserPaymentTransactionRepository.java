package zeroone.developers.billingapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zeroone.developers.billingapp.entity.UserPaymentTransaction;

import java.util.List;

public interface UserPaymentTransactionRepository extends BaseRepository<UserPaymentTransaction, Long> {



    List<UserPaymentTransaction> findByUserId(Long userId);



}

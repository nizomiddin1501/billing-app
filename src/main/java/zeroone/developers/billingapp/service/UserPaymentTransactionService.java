package zeroone.developers.billingapp.service;

import org.springframework.data.domain.Page;
import zeroone.developers.billingapp.payload.UserPaymentTransactionDto;

import java.util.Optional;

public interface UserPaymentTransactionService {

    Page<UserPaymentTransactionDto> getAllUserPaymentTransactions(int page, int size);

    Optional<UserPaymentTransactionDto> getUserPaymentTransactionById(Long userPaymentTransactionId);

    UserPaymentTransactionDto createUserPaymentTransaction(UserPaymentTransactionDto userPaymentTransactionDto);

    UserPaymentTransactionDto updateUserPaymentTransaction(Long userPaymentTransactionId, UserPaymentTransactionDto userPaymentTransactionDto);

    void deleteUserPaymentTransaction(Long userPaymentTransactionId);

}

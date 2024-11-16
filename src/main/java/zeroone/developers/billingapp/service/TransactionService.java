package zeroone.developers.billingapp.service;

import org.springframework.data.domain.Page;
import zeroone.developers.billingapp.payload.TransactionDto;

import java.util.Optional;

public interface TransactionService {

    Page<TransactionDto> getAllTransactions(int page, int size);

    Optional<TransactionDto> getTransactionById(Long transactionId);

    TransactionDto createTransaction(TransactionDto transactionDto);

    TransactionDto updateTransaction(Long transactionId, TransactionDto transactionDto);

    void deleteTransaction(Long transactionId);
}

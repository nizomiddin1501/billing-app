package zeroone.developers.billingapp.service;

import org.springframework.data.domain.Page;
import zeroone.developers.billingapp.payload.TransactionItemDto;

import java.util.Optional;

public interface TransactionItemService {

    Page<TransactionItemDto> getAllTransactionItems(int page, int size);

    Optional<TransactionItemDto> getTransactionItemById(Long transactionItemId);

    TransactionItemDto createTransactionItem(TransactionItemDto transactionItemDto);

    TransactionItemDto updateTransactionItem(Long transactionItemId, TransactionItemDto transactionItemDto);

    void deleteTransactionItem(Long transactionItemId);
}

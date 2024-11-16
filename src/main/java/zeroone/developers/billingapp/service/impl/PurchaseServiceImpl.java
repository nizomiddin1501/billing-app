package zeroone.developers.billingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import zeroone.developers.billingapp.payload.ProductDto;
import zeroone.developers.billingapp.payload.TransactionDto;
import zeroone.developers.billingapp.payload.TransactionItemDto;
import zeroone.developers.billingapp.payload.UserPaymentTransactionDto;
import zeroone.developers.billingapp.service.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final TransactionService transactionService;
    private final TransactionItemService transactionItemService;
    private final UserPaymentTransactionService userPaymentTransactionService;
    private final ProductService productService;

    @Override
    @Transactional
    public void completePurchase(TransactionDto transactionDto,
                                 List<TransactionItemDto> transactionItems,
                                 UserPaymentTransactionDto paymentDto) {
        // Process 1: Create Transaction
        TransactionDto savedTransaction = transactionService.createTransaction(transactionDto);

        // Process 2: Create Transaction Items
        for (TransactionItemDto item : transactionItems) {
            item.setTransactionId(savedTransaction.getId());
            transactionItemService.createTransactionItem(item);
        }

        // Process 3: Create User Payment Transaction
        userPaymentTransactionService.createUserPaymentTransaction(paymentDto);

        // Process 4: Update Products
        for (TransactionItemDto item : transactionItems) {
            ProductDto product = productService.updateProduct(item.getProductId(),
                    new ProductDto(item.getProductId(), null, null, item.getCount()));
        }

    }
}

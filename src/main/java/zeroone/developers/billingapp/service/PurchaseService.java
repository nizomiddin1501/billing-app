package zeroone.developers.billingapp.service;

import zeroone.developers.billingapp.payload.TransactionDto;
import zeroone.developers.billingapp.payload.TransactionItemDto;
import zeroone.developers.billingapp.payload.UserPaymentTransactionDto;

import java.util.List;

public interface PurchaseService {

    void completePurchase(TransactionDto transactionDto,
                          List<TransactionItemDto> transactionItems,
                          UserPaymentTransactionDto paymentDto);

}

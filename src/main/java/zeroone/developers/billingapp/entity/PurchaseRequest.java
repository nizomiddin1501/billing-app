package zeroone.developers.billingapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zeroone.developers.billingapp.payload.TransactionDto;
import zeroone.developers.billingapp.payload.TransactionItemDto;
import zeroone.developers.billingapp.payload.UserPaymentTransactionDto;
/**
 * Class representing a purchase request containing transaction, items, and payment details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Details for completing a purchase, including transaction, items, and payment information.")
public class PurchaseRequest {

    @Schema(description = "Transaction details for the purchase.",
            example = "{ 'id': 1, 'user': { 'id': 3 }, 'totalAmount': 150.00, 'date': '2023-07-11' }")
    private TransactionDto transaction;

    @Schema(description = "Item involved in the transaction.",
            example = "{ 'productId': 5, 'quantity': 2, 'price': 50.00 }")
    private TransactionItemDto items;

    @Schema(description = "Payment details for the purchase.",
            example = "{ 'id': 1, 'userId': 2, 'amount': 150.00, 'date': '2023-07-11' }")
    private UserPaymentTransactionDto payment;
}

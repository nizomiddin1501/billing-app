package zeroone.developers.billingapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zeroone.developers.billingapp.payload.TransactionDto;
import zeroone.developers.billingapp.payload.TransactionItemDto;
import zeroone.developers.billingapp.payload.UserPaymentTransactionDto;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Product details")
public class PurchaseRequest {
    private TransactionDto transaction;
    private List<TransactionItemDto> items;
    private UserPaymentTransactionDto payment;
}

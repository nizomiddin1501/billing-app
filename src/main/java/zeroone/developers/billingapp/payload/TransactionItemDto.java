package zeroone.developers.billingapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data transfer object for Transaction Item")
public class TransactionItemDto {

    @Schema(description = "Transaction item ID",
            example = "1")
    private Long id;

    @NotNull
    @Schema(description = "Product ID in the transaction",
            example = "10")
    private Long productId;

    @NotNull
    @Schema(description = "Quantity of the product",
            example = "2")
    private Long count;

    @NotNull
    @Schema(description = "Price of the product",
            example = "75.00")
    private BigDecimal amount;

    @NotNull
    @Schema(description = "Total amount for this item",
            example = "150.00")
    private BigDecimal totalAmount;

    @NotNull
    @Schema(description = "Transaction ID for the item",
            example = "5")
    private Long transactionId;
}

package zeroone.developers.billingapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data transfer object for Transaction")
public class TransactionDto {

    @Schema(description = "Transaction ID",
            example = "1")
    private Long id;

    @NotNull
    @Schema(description = "User ID who made the transaction",
            example = "2")
    private Long userId;

    @NotNull
    @Schema(description = "Total amount of the transaction",
            example = "150.00")
    private BigDecimal totalAmount;

    @NotNull
    @Schema(description = "Date of the transaction",
            example = "2023-07-11")
    private Date date;

}

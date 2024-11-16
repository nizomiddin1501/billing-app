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
@Schema(description = "Data transfer object for User Payment Transaction")
public class UserPaymentTransactionDto {

    @Schema(description = "Payment transaction ID",
            example = "1")
    private Long id;

    @NotNull
    @Schema(description = "User ID who made the payment",
            example = "2")
    private Long userId;

    @NotNull
    @Schema(description = "Amount of the payment",
            example = "200.00")
    private BigDecimal amount;

    @NotNull
    @Schema(description = "Date of the payment",
            example = "2023-07-11")
    private Date date;

}

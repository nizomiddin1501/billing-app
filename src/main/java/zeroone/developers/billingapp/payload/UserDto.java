package zeroone.developers.billingapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data transfer object for User")
public class UserDto {

    @Schema(description = "User ID",
            example = "1")
    private Long id;

    @NotNull
    @Schema(description = "User full name",
            example = "Nizomiddin Mirzanazarov")
    private String fullname;

    @NotNull
    @Schema(description = "Unique username",
            example = "nizomiddin097")
    private String username;

    @NotNull
    @Schema(description = "User balance",
            example = "1000.00")
    private BigDecimal balance;

}

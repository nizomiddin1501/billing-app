package zeroone.developers.billingapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data transfer object for Product")
public class ProductDto {

    @Schema(description = "Product ID",
            example = "1")
    private Long id;

    @NotNull
    @Schema(description = "Product name",
            example = "Laptop")
    private String name;

    @NotNull
    @Schema(description = "Product count",
            example = "50")
    private Long count;

    @NotNull
    @Schema(description = "Category ID for the product",
            example = "3")
    private Long categoryId;
}

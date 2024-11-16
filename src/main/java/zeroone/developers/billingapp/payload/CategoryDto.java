package zeroone.developers.billingapp.payload;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data transfer object for Category")
public class CategoryDto {

    @Schema(description = "Category ID",
            example = "1")
    private Long id;

    @NotNull
    @Schema(description = "Category name",
            example = "Electronics")
    private String name;

    @Schema(description = "Category order",
            example = "1")
    private Long order;

    @Schema(description = "Category description",
            example = "All electronic products")
    private String description;


}

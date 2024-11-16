package zeroone.developers.billingapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
@Schema(description = "Category of the product")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Category ID",
            example = "1")
    private Long id;

    @Schema(description = "Category name",
            example = "Electronics")
    private String name;

    @Column(name = "order_value")
    @Schema(description = "Category order",
            example = "1")
    private Long orderValue;

    @Schema(description = "Category description",
            example = "All electronic products")
    private String description;
}

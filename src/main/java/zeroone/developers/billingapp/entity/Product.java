package zeroone.developers.billingapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@Schema(description = "Product details")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Product ID",
            example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Product name",
            example = "Laptop")
    private String name;

    @Column(nullable = false)
    @Schema(description = "Product count",
            example = "50")
    private Long count;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Schema(description = "Product category",
            example = "Category ID: 3")
    private Category category;


}

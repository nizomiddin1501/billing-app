package zeroone.developers.billingapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction_item")
@Schema(description = "Details of items in a transaction")
public class TransactionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Transaction item ID",
            example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @Schema(description = "Product in the transaction",
            example = "Product ID: 10")
    private Product product;

    @Column(nullable = false)
    @Schema(description = "Quantity of the product",
            example = "2")
    private Long count;

    @Column(nullable = false)
    @Schema(description = "Price of the product",
            example = "75.00")
    private BigDecimal amount;

    @Column(nullable = false)
    @Schema(description = "Total amount for this item",
            example = "150.00")
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    @Schema(description = "Reference to the transaction",
            example = "Transaction ID: 5")
    private Transaction transaction;


}

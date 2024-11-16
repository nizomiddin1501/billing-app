package zeroone.developers.billingapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
@Schema(description = "Transaction information")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Transaction ID",
            example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @Schema(description = "User who made the transaction",
            example = "User ID: 3")
    private User user;

    @Column(nullable = false)
    @Schema(description = "Total amount of the transaction",
            example = "150.00")
    private BigDecimal totalAmount;

    @Column(nullable = false)
    @Schema(description = "Transaction date",
            example = "2023-07-11")
    private Date date;


}

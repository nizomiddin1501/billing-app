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
@Table(name = "user_payment_transaction")
@Schema(description = "User payment transaction history")
public class UserPaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Payment transaction ID",
            example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @Schema(description = "User who made the payment",
            example = "User ID: 3")
    private User user;

    @Column(nullable = false)
    @Schema(description = "Amount of the payment",
            example = "200.00")
    private BigDecimal amount;

    @Column(nullable = false)
    @Schema(description = "Date of the payment",
            example = "2023-07-11")
    private Date date;


}

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
@Table(name = "users")
@Schema(description = "User information")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "User ID",
            example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "User full name",
            example = "Nizomiddin Mirzanazarov")
    private String fullname;

    @Column(unique = true, nullable = false)
    @Schema(description = "Unique username",
            example = "john_doe")
    private String username;

    @Column(nullable = false)
    @Schema(description = "User balance",
            example = "1000.00")
    private BigDecimal balance;
}

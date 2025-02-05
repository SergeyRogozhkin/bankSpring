package base.example.demo.paymentHisorya;

import base.example.demo.entity.LoanSimple;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Double {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date paymentDate;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private LoanSimple loan;

    // Геттеры, сеттеры и другие поля
}

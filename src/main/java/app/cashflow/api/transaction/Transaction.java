package app.cashflow.api.transaction;

import app.cashflow.api.bank_account.BankAccount;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "transaction_description")
    private String transactionDescription;

    @NonNull
    @Column(name = "transaction_type")
    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

    @NonNull
    @Column(name = "transaction_category")
    @Enumerated(EnumType.ORDINAL)
    private TransactionCategory transactionCategory;

    @NonNull
    @Column(name = "transaction_value")
    private Double transactionValue;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "bank_account_id", foreignKey = @ForeignKey(name = "fk_bank_account_id"))
    @JsonBackReference
    private BankAccount bankAccount;
}

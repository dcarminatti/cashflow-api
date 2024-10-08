package app.cashflow.api.bank_account;

import app.cashflow.api.transaction.Transaction;
import app.cashflow.api.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccount {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "account_BankName")
    private String accountBankName;

    @NonNull
    @Column(name = "account_agency")
    private String accountAgency;

    @NonNull
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_balance")
    private Double accountBalance;

    @Column(name = "account_started")
    private LocalDateTime accountStarted;

    @NonNull
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_account_id", foreignKey = @ForeignKey(name = "fk_bank_account_id"))
    @JsonManagedReference
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"))
    @JsonBackReference
    private User user;
}

package app.cashflow.api.account_plan;

import app.cashflow.api.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_plan")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountPlan {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "category")
    private String planCategory;

    @Column(name = "account_category")
    @Enumerated(EnumType.STRING)
    private AccountPlanCategoryType planCategoryType;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id_2"))
    @JsonBackReference
    private User user;
}

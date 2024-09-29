package app.cashflow.api.user;

import app.cashflow.api.account_plan.AccountPlan;
import app.cashflow.api.bank_account.BankAccount;

import java.util.List;

public record UserResponseDTO(
        String name,
        String email,
        List<BankAccount> bankAccounts,
        List<AccountPlan> accountPlans
) {
}

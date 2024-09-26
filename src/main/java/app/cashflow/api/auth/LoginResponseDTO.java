package app.cashflow.api.auth;

import app.cashflow.api.bank_account.BankAccount;
import app.cashflow.api.user.User;

import java.util.List;

public record LoginResponseDTO(String name, String email, String token, List<BankAccount> bankAccounts) {
    public LoginResponseDTO(User user, String token) {
        this(user.getName(), user.getEmail(), token, user.getBankAccounts());
    }
}

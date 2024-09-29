package app.cashflow.api.auth;

import app.cashflow.api.user.User;
import app.cashflow.api.user.UserResponseDTO;


public record LoginResponseDTO(UserResponseDTO user, String token) {
    public LoginResponseDTO(User user, String token) {
        this(new UserResponseDTO(user.getName(), user.getEmail(), user.getBankAccounts(), user.getAccountPlans()), token);
    }
}

package app.cashflow.api.auth;

public record LoginRequestDTO(
        String email,
        String password
) {
}

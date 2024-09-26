package app.cashflow.api.auth;

public record RegisterRequestDTO(
        String email,
        String password,
        String name
) {
}

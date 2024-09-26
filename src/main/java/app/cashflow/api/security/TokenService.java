package app.cashflow.api.security;

import app.cashflow.api.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${application.security.jwt.secret-key}")
    private String SECRET;
    @Value("${application.security.jwt.secret-expiration}")
    private Long EXPIRATION;

    private static final String ISSUER = "Cashflow API";

    public String generateToken(User user) {
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(this.getExpiration())
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (Exception e) {
            throw new RuntimeException("Error generating token: ", e);
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    private Instant getExpiration() {
        return LocalDateTime.now().plusMinutes(EXPIRATION).toInstant(ZoneOffset.of("-03:00"));
    }
}

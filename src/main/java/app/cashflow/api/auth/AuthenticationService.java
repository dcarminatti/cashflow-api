package app.cashflow.api.auth;

import app.cashflow.api.security.TokenService;
import app.cashflow.api.user.User;
import app.cashflow.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());
        var authentication = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) authentication.getPrincipal());

        return new LoginResponseDTO((User) authentication.getPrincipal(), token);
    }

    public boolean register(RegisterRequestDTO registerRequestDTO) {
        if(this.userRepository.findByEmail(registerRequestDTO.email()).isPresent()) {
            return false;
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(registerRequestDTO.password());
        User user = User.builder()
                .name(registerRequestDTO.name())
                .email(registerRequestDTO.email())
                .password(encodedPassword)
                .build();
        this.userRepository.save(user);

        return true;
    }
}

package app.cashflow.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        var auth = authenticationService.login(loginRequestDTO);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        if(authenticationService.register(registerRequestDTO))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}

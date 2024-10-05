package app.cashflow.api.bank_account;

import app.cashflow.api.user.User;
import app.cashflow.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public BankAccount save(BankAccount bankAccount) {
        User authenticatedUser = getAuthenticatedUser();
        bankAccount.setUser(authenticatedUser);
        bankAccount.setAccountStarted(java.time.LocalDateTime.now());
        return bankAccountRepository.save(bankAccount);
    }

    public boolean update(Long id, BankAccount bankAccount) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(id);
        User authenticatedUser = getAuthenticatedUser();
        if(bankAccountOptional.isEmpty() || !bankAccountOptional.get().getUser().equals(authenticatedUser)) {
            return false;
        }
        bankAccountRepository.save(bankAccount);
        return true;
    }

    public boolean deleteById(Long id) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(id);
        User authenticatedUser = getAuthenticatedUser();
        if(bankAccountOptional.isEmpty() || !bankAccountOptional.get().getUser().equals(authenticatedUser)) {
            return false;
        }
        bankAccountRepository.deleteById(id);
        return true;
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return null;
        }

        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }
}

package app.cashflow.api.account_plan;

import app.cashflow.api.user.User;
import app.cashflow.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountPlanService {
    private final AccountPlanRepository accountPlanRepository;
    private final UserRepository userRepository;

    public AccountPlan create(AccountPlan accountPlan) {
        User authenticatedUser = getAuthenticatedUser();
        accountPlan.setUser(authenticatedUser);
        return accountPlanRepository.save(accountPlan);
    }

    public boolean update(Long id, AccountPlan accountPlan) {
        Optional<AccountPlan> accountPlanOptional = accountPlanRepository.findById(id);
        User authenticatedUser = getAuthenticatedUser();
        if(accountPlanOptional.isEmpty() || !accountPlanOptional.get().getUser().equals(authenticatedUser)) {
            return false;
        }
        accountPlanRepository.save(accountPlan);
        return false;
    }

    public boolean deleteById(Long id) {
        Optional<AccountPlan> accountPlanOptional = accountPlanRepository.findById(id);
        User authenticatedUser = getAuthenticatedUser();
        if(accountPlanOptional.isEmpty() || !accountPlanOptional.get().getUser().equals(authenticatedUser)) {
            return false;
        }
        accountPlanRepository.deleteById(id);
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

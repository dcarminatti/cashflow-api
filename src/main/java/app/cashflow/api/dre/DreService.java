package app.cashflow.api.dre;

import app.cashflow.api.transaction.Transaction;
import app.cashflow.api.transaction.TransactionRepository;
import app.cashflow.api.user.User;
import app.cashflow.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DreService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public Dre create(LocalDateTime startDate, LocalDateTime endDate) {
        User user = this.getAuthenticatedUser();
        double grossRevenue = 0;
        double expenses = 0;
        double variableCosts = 0;
        double fixedCosts = 0;
        double netRevenue = 0;
        double financialRevenues = 0;
        double financialExpenses = 0;
        double taxes = 0;
        double netProfit = 0;


        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transaction -> {
            if(transaction.getBankAccount().getUser().equals(user)) {
                transactions.add(transaction);
            }
        });

        for(Transaction transaction : transactions) {
            if(transaction.getTransactionDate().isAfter(endDate) && transaction.getTransactionDate().isBefore(startDate)) {
                continue;
            }

            switch (transaction.getTransactionCategory()) {
                case INCOME:
                    grossRevenue += transaction.getTransactionValue();
                    break;
                case EXPENSE:
                    expenses += transaction.getTransactionValue();
                    break;
                case VARIABLE_COSTS:
                    variableCosts += transaction.getTransactionValue();
                    break;
                case FIXED_COSTS:
                    fixedCosts += transaction.getTransactionValue();
                    break;
                case FINANCIAL_REVENUES:
                    financialRevenues += transaction.getTransactionValue();
                    break;
                case FINANCIAL_EXPENSES:
                    financialExpenses += transaction.getTransactionValue();
                    break;
                case TAXES:
                    taxes += transaction.getTransactionValue();
                    break;
            }
        }

        return Dre.builder()
                .grossRevenue(grossRevenue)
                .expenses(expenses)
                .variableCosts(variableCosts)
                .fixedCosts(fixedCosts)
                .netRevenue(netRevenue)
                .financialRevenues(financialRevenues)
                .financialExpenses(financialExpenses)
                .taxes(taxes)
                .netProfit(netProfit)
                .startDate(startDate)
                .endDate(endDate)
                .build();
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

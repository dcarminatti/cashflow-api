package app.cashflow.api.transaction;

import app.cashflow.api.bank_account.BankAccount;

import app.cashflow.api.bank_account.BankAccountRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    public Transaction save(Transaction transaction) {
        transaction.setTransactionDate(LocalDateTime.now().atOffset(ZoneOffset.of("-03:00")).toLocalDateTime());
        return transactionRepository.save(transaction);
    }

    public Transaction save(Long contaId, Transaction transaction) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(contaId);
        if(bankAccount.isEmpty()) {
            return null;
        }

        transaction.setBankAccount(bankAccount.get());
        transaction.setTransactionDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public boolean update(Long id, Transaction transaction) {
        boolean exists = transactionRepository.existsById(id);
        if (exists) {
            transaction.setId(id);
            transactionRepository.save(transaction);
            return true;
        }
        return false;
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}

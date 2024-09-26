package app.cashflow.api.transaction;

import app.cashflow.api.bank_account.BankAccountService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/{contaId}")
    ResponseEntity<Transaction> save(@PathVariable Long contaId, @RequestBody Transaction transaction) {
        Transaction transactionSaved = transactionService.save(contaId, transaction);
        if(transactionSaved == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactionSaved);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Transaction transaction) {
        boolean updated = transactionService.update(id, transaction);
        if(!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id) {
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

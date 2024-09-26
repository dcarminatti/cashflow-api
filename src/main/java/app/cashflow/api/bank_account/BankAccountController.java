package app.cashflow.api.bank_account;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank-account")
@RequiredArgsConstructor
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @PostMapping
    ResponseEntity<BankAccount> save(@RequestBody BankAccount bankAccount) {
        BankAccount bankAccountSaved = bankAccountService.save(bankAccount);
        if(bankAccountSaved == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bankAccountSaved);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        boolean updated = bankAccountService.update(id, bankAccount);
        if(!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean deleted = bankAccountService.deleteById(id);
        if(!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

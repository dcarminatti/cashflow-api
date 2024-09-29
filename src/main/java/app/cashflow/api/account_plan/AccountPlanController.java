package app.cashflow.api.account_plan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account-plan")
public class AccountPlanController {
    private final AccountPlanService accountPlanService;

    @PostMapping
    ResponseEntity<AccountPlan> create(@RequestBody AccountPlan accountPlan) {
        AccountPlan accountPlanCreated = accountPlanService.create(accountPlan);
        if(accountPlanCreated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountPlanCreated);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean deleted = accountPlanService.deleteById(id);
        if(!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

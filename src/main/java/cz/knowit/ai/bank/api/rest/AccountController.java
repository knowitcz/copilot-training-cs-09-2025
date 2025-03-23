package cz.knowit.ai.bank.api.rest;

import cz.knowit.ai.bank.database.product.Account;
import cz.knowit.ai.bank.database.product.AccountProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/bank/v1")
class AccountController {

    private final AccountProdService accountService;

    @Autowired
    AccountController(AccountProdService accountService) {
        this.accountService = Objects.requireNonNull(accountService);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable(name = "id") long id) {
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/account/{id}/withdraw/{amount}")
    public ResponseEntity<Void> withdrawMoney(@PathVariable(name = "id") long id, @PathVariable(name = "amount") int amount) {
        accountService.withdrawMoney(id, amount);

        return ResponseEntity.ok().build();
    }
}

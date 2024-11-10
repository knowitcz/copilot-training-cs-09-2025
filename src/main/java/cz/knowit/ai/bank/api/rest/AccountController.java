package cz.knowit.ai.bank.api.rest;

import cz.knowit.ai.bank.database.product.Account;
import cz.knowit.ai.bank.database.product.AccountProdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "Get account by ID", description = "Returns account details for the given account ID")
    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccount(
            @Parameter(description = "ID of the account to retrieve", required = true) @PathVariable(name = "id") long id) {
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Withdraw money from account", description = "Withdraws the specified amount of money from the given account ID")
    @PostMapping("/account/{id}/withdraw/{amount}")
    public ResponseEntity<Void> withdrawMoney(
            @Parameter(description = "ID of the account to withdraw money from", required = true) @PathVariable(name = "id") long id,
            @Parameter(description = "Amount of money to withdraw", required = true) @PathVariable(name = "amount") int amount) {
        accountService.withdrawMoney(id, amount);

        return ResponseEntity.ok().build();
    }
}

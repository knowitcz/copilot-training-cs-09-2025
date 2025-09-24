package cz.knowit.ai.bank.api.rest;

import cz.knowit.ai.bank.database.transaction.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import cz.knowit.ai.bank.database.transaction.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/bank/v1")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = Objects.requireNonNull(transactionService);
    }

    @Operation(summary = "Create transaction", description = "Creates a new transaction")
    @RequestMapping(method = RequestMethod.POST, value = "/transaction")
    public ResponseEntity<Void> createTransaction(@RequestBody Transaction transaction) {
        transactionService.save(transaction);
        
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get transaction by ID", description = "Returns transaction details for the given transaction ID")
    @RequestMapping(method = RequestMethod.GET, value = "/transaction/{id}")
    public ResponseEntity<Transaction> getTransactionById(
            @Parameter(description = "ID of the transaction to retrieve", required = true) @PathVariable(name = "id") long id) {
        return transactionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok().build());
    }

    @Operation(summary = "Update transaction", description = "Updates an existing transaction")
    @RequestMapping(method = RequestMethod.PUT, value = "/transaction/{id}")
    public ResponseEntity<Void> updateTransaction(
            @Parameter(description = "ID of the transaction to update", required = true) @PathVariable(name = "id") long id,
            @RequestBody Transaction transaction) {
        transaction.setId(id);
        transactionService.save(transaction);
        
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete transaction", description = "Deletes the specified transaction by ID")
    @RequestMapping(method = RequestMethod.DELETE, value = "/transaction/{id}")
    public ResponseEntity<Void> deleteTransaction(
            @Parameter(description = "ID of the transaction to delete", required = true) @PathVariable(name = "id") long id) {
        transactionService.deleteById(id);
        
        return ResponseEntity.ok().build();
    }
}

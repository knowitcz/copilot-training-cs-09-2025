package cz.knowit.ai.bank.database.product;

import cz.knowit.ai.bank.database.transaction.Locality;
import cz.knowit.ai.bank.database.transaction.TransactionType;

import java.util.Optional;

public interface AccountService {

    Optional<Account> getAccountById(long id);
    void transferMoney(long fromAccountId, long toAccountId, int amount);
    void withdrawMoney(long accountId, int amount);
    void depositMoney(long accountId, int amount);
    
    // New methods with transaction type and locality
    void transferMoney(long fromAccountId, long toAccountId, int amount, TransactionType type, Locality locality);
    void withdrawMoney(long accountId, int amount, TransactionType type, Locality locality);
    void depositMoney(long accountId, int amount, TransactionType type, Locality locality);
}

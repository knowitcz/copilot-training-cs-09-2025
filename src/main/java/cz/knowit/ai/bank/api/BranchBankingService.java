package cz.knowit.ai.bank.api;

import cz.knowit.ai.bank.database.product.AccountService;
import cz.knowit.ai.bank.database.transaction.LocalityService;
import cz.knowit.ai.bank.database.transaction.TransactionType;
import cz.knowit.ai.bank.validator.AmountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Branch implementation of the {@link BankingService}.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@Service
final class BranchBankingService implements BankingService {

    private final AccountService accountService;
    private final LocalityService localityService;

    @Autowired
    BranchBankingService(AccountService accountService, LocalityService localityService) {
        this.accountService = accountService;
        this.localityService = localityService;
    }

    @Override
    public void makeTransfer(long fromAccountId, long toAccountId, int amount) {
        AmountValidator.validateEdgeCases(amount);
        // Use Main Branch locality (ID -1 from our data.sql)
        var locality = localityService.getLocalityById(-1).orElse(null);
        accountService.transferMoney(fromAccountId, toAccountId, amount, TransactionType.BRANCH, locality);
    }

    @Override
    public void withdraw(long accountId, int amount) {
        AmountValidator.validateEdgeCases(amount);
        // Use Main Branch locality (ID -1 from our data.sql)
        var locality = localityService.getLocalityById(-1).orElse(null);
        accountService.withdrawMoney(accountId, amount, TransactionType.BRANCH, locality);
    }

    @Override
    public void deposit(long accountId, int amount) {
        AmountValidator.validateEdgeCases(amount);
        // Use Main Branch locality (ID -1 from our data.sql)
        var locality = localityService.getLocalityById(-1).orElse(null);
        accountService.depositMoney(accountId, amount, TransactionType.BRANCH, locality);
    }
}

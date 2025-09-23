package cz.knowit.ai.bank.api;

import cz.knowit.ai.bank.database.product.AccountService;
import cz.knowit.ai.bank.database.product.TransactionType;
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
    private static final Long DEFAULT_BRANCH_LOCALITY_ID = -1L; // Prague Main Branch

    @Autowired
    BranchBankingService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void makeTransfer(long fromAccountId, long toAccountId, int amount) {
        AmountValidator.validateEdgeCases(amount);
        accountService.transferMoney(fromAccountId, toAccountId, amount, TransactionType.BRANCH, DEFAULT_BRANCH_LOCALITY_ID);
    }

    @Override
    public void withdraw(long accountId, int amount) {
        AmountValidator.validateEdgeCases(amount);
        accountService.withdrawMoney(accountId, amount, TransactionType.BRANCH, DEFAULT_BRANCH_LOCALITY_ID);
    }

    @Override
    public void deposit(long accountId, int amount) {
        AmountValidator.validateEdgeCases(amount);
        accountService.depositMoney(accountId, amount, TransactionType.BRANCH, DEFAULT_BRANCH_LOCALITY_ID);
    }
}

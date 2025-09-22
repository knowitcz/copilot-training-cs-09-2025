package cz.knowit.ai.bank.api;

import cz.knowit.ai.bank.database.product.AccountService;
import cz.knowit.ai.bank.database.transaction.TransactionType;
import cz.knowit.ai.bank.validator.AmountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Online implementation of the {@link TransferBankingService}
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@Service
final class OnlineBankingService implements TransferBankingService {

    private final AccountService accountService;

    @Autowired
    OnlineBankingService(AccountService accountService) {
        this.accountService = Objects.requireNonNull(accountService);
    }

    @Override
    public void makeTransfer(long fromAccountId, long toAccountId, int amount) {
        AmountValidator.validateEdgeCases(amount);
        // Online transactions have no locality (null)
        accountService.transferMoney(fromAccountId, toAccountId, amount, TransactionType.ONLINE, null);
    }
}

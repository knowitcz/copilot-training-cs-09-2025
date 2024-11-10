package cz.knowit.ai.bank.api;

import cz.knowit.ai.bank.validator.ValidationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link BranchBankingService}.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class OnlineBankingServiceTest {

    private final AccountTestService accountService = new AccountTestService();
    private final TransferBankingService bankingService = new OnlineBankingService(accountService);

    @Test
    void makeTransfer() {
        var account1 = accountService.getAccountById(1);
        var account2 = accountService.getAccountById(2);

        assertThat(account1).isPresent();
        assertThat(account1.get().getBalance()).isEqualTo(1000);

        assertThat(account2).isPresent();
        assertThat(account2.get().getBalance()).isEqualTo(2000);

        bankingService.makeTransfer(1, 2, 500);
        assertThat(account1.get().getBalance()).isEqualTo(500);
        assertThat(account2.get().getBalance()).isEqualTo(2500);
    }

    @Test
    void makeTransferWithNegativeAmount() {
        var account1 = accountService.getAccountById(1);
        var account2 = accountService.getAccountById(2);

        assertThat(account1).isPresent();
        assertThat(account1.get().getBalance()).isEqualTo(1000);

        assertThat(account2).isPresent();
        assertThat(account2.get().getBalance()).isEqualTo(2000);

        assertThrows(ValidationException.class, () -> bankingService.makeTransfer(1, 2, -500));
    }

    @Test
    void makeTransferWithZeroAmount() {
        var account1 = accountService.getAccountById(1);
        var account2 = accountService.getAccountById(2);

        assertThat(account1).isPresent();
        assertThat(account1.get().getBalance()).isEqualTo(1000);

        assertThat(account2).isPresent();
        assertThat(account2.get().getBalance()).isEqualTo(2000);

        assertThrows(ValidationException.class, () -> bankingService.makeTransfer(1, 2, 0));
    }

    @Test
    void makeTransferWithBigAmount() {
        var account1 = accountService.getAccountById(1);
        var account2 = accountService.getAccountById(2);

        assertThat(account1).isPresent();
        assertThat(account1.get().getBalance()).isEqualTo(1000);

        assertThat(account2).isPresent();
        assertThat(account2.get().getBalance()).isEqualTo(2000);

        bankingService.makeTransfer(1, 2, 1000000);
        assertThat(account1.get().getBalance()).isEqualTo(-999000);
        assertThat(account2.get().getBalance()).isEqualTo(1002000);
    }
}
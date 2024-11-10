package cz.knowit.ai.bank.api;

import cz.knowit.ai.bank.database.product.AccountService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link BranchBankingService}.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class BranchBankingServiceTest {

    private final AccountService accountService = new AccountTestService();
    private final BankingService bankingService = new BranchBankingService(accountService);

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
    void withdraw() {
        var account1 = accountService.getAccountById(1);

        assertThat(account1).isPresent();
        assertThat(account1.get().getBalance()).isEqualTo(1000);

        bankingService.withdraw(1, 500);
        assertThat(account1.get().getBalance()).isEqualTo(500);
    }

    @Test
    void deposit() {
        var account1 = accountService.getAccountById(1);

        assertThat(account1).isPresent();
        assertThat(account1.get().getBalance()).isEqualTo(1000);

        bankingService.deposit(1, 500);
        assertThat(account1.get().getBalance()).isEqualTo(1500);
    }
}
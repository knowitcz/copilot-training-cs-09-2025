package cz.knowit.ai.bank.api;

import cz.knowit.ai.bank.validator.ValidationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link AtmBankingService}.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class AtmBankingServiceTest {

    private final AccountTestService accountService = new AccountTestService();
    private final LocalityTestService localityService = new LocalityTestService();
    private final BankingService bankingService = new AtmBankingService(accountService, localityService);

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

    @Test
    void depositMoreThanAtmLimit() {
        var account1 = accountService.getAccountById(1);

        assertThat(account1).isPresent();
        assertThat(account1.get().getBalance()).isEqualTo(1000);

        assertThrows(ValidationException.class, () -> bankingService.deposit(1, 100000));
        assertThat(account1.get().getBalance()).isEqualTo(1000);
    }
}
package cz.knowit.ai.bank.database.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link TransactionType}.
 *
 * @author AI Assistant
 * @since 1.0.0
 */
final class TransactionTypeTest {

    @Test
    void transactionTypeEnumShouldHaveCorrectValues() {
        // Then
        assertThat(TransactionType.values()).containsExactly(
            TransactionType.ONLINE,
            TransactionType.ATM,
            TransactionType.BRANCH
        );
    }

    @Test
    void transactionTypesShouldHaveCorrectNames() {
        // Then
        assertThat(TransactionType.ONLINE.name()).isEqualTo("ONLINE");
        assertThat(TransactionType.ATM.name()).isEqualTo("ATM");
        assertThat(TransactionType.BRANCH.name()).isEqualTo("BRANCH");
    }
}
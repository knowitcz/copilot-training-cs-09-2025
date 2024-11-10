package cz.knowit.ai.bank.validator;

/**
 * Validator for amount
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
public final class AmountValidator {

    private static final int ATM_LIMIT = 10000;

    private AmountValidator() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static void validateAtmLimit(int amount) {
        if (amount > ATM_LIMIT) {
            throw new ValidationException("Amount must be lower than ATM limit: " + ATM_LIMIT);
        }
    }

    public static void validateEdgeCases(int amount) {
        if (amount < 0) {
            throw new ValidationException("Amount must be positive");
        }
        if (amount == 0) {
            throw new ValidationException("Amount must be greater than zero");
        }
    }
}

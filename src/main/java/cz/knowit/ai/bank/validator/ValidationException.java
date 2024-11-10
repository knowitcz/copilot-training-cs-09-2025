package cz.knowit.ai.bank.validator;

import java.io.Serial;

/**
 * Exception that is thrown when validation fails
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
public final class ValidationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8219814305559421209L;

    public ValidationException(String message) {
        super(message);
    }
}

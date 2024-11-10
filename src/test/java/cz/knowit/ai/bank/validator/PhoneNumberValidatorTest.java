package cz.knowit.ai.bank.validator;

import cz.knowit.ai.bank.database.client.Nationality;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Phone number validator tests
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class PhoneNumberValidatorTest {

    @Test
    void validPhoneNumber() {
        assertThat(PhoneNumberValidator.isValid("+420123456789", Nationality.CZECH_REPUBLIC)).isTrue();
    }

    @Test
    void invalidPhoneNumber() {
        assertThat(PhoneNumberValidator.isValid("123456798", Nationality.ANDORRA)).isFalse();
    }

    @Test
    void nullPhoneNumber() {
        assertThat(PhoneNumberValidator.isValid(null, Nationality.CZECH_REPUBLIC)).isFalse();
    }

    @Test
    void nullNationality() {
        assertThrows(NullPointerException.class,
                () -> PhoneNumberValidator.isValid("+420123456789", null));
    }

    @Test
    void nullPhoneNumberAndNationality() {
        assertThat(PhoneNumberValidator.isValid(null, null)).isFalse();
    }

    @Test
    void invalidPhoneNumberAndNationality() {
        assertThrows(NullPointerException.class,
                () -> PhoneNumberValidator.isValid("123456798", null));
    }
}
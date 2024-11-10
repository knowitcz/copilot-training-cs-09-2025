package cz.knowit.ai.bank.validator;

import cz.knowit.ai.bank.database.client.Nationality;

/**
 * Validator for phone numbers
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
public final class PhoneNumberValidator {

    private PhoneNumberValidator() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static boolean isValid(String phoneNumber, Nationality nationality) {
        return phoneNumber != null && phoneNumber.matches(nationality.getPhoneNumberRegex());
    }
}

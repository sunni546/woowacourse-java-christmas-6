package christmas.validator;

import static christmas.validator.InputValidator.validateBoolean;
import static christmas.validator.InputValidator.validateDigit;
import static christmas.validator.InputValidator.validateEmpty;
import static christmas.view.message.ErrorMessage.INVALID_DATE;

public class DateValidator {
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;

    public static void validate(String date) {
        validateBoolean(validateEmpty(date), INVALID_DATE);
        validateBoolean(validateDigit(date), INVALID_DATE);
        validateBoolean(validateDateOnlyFrom1To31(Integer.parseInt(date)), INVALID_DATE);
    }

    private static boolean validateDateOnlyFrom1To31(int date) {
        return date >= MIN_DATE && date <= MAX_DATE;
    }
}

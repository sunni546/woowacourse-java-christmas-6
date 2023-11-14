package christmas.validator;

import christmas.view.message.ErrorMessage;

public class InputValidator {
    public static boolean validateEmpty(String input) {
        return !input.isEmpty();
    }

    public static boolean validateDigit(String input) {
        return input.chars().allMatch(Character::isDigit);
    }

    public static void validateBoolean(boolean condition, ErrorMessage errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage.getErrorMessage());
        }
    }
}

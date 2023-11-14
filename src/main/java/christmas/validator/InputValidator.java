package christmas.validator;

public class InputValidator {
    public static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateDigit(String input) {
        if (input.chars().anyMatch(ch -> !Character.isDigit(ch))) {
            throw new IllegalArgumentException();
        }
    }
}

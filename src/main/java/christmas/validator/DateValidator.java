package christmas.validator;

public class DateValidator {
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;

    public static void validate(String date) {
        validateEmpty(date);
        validateDigit(date);
        validateDateOnlyFrom1To31(Integer.parseInt(date));
    }

    private static void validateEmpty(String date) {
        if (date.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateDigit(String date) {
        if (date.chars().anyMatch(ch -> !Character.isDigit(ch))) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateDateOnlyFrom1To31(int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException();
        }
    }
}

package christmas.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateValidatorTest {
    @DisplayName("빈 입력 예외 처리")
    @Test
    void validateEmpty() {
        String input = "";

        assertValidationFailsForInvalidDate(input);
    }

    @DisplayName("숫자가 아닌 문자가 포함된 입력 예외 처리")
    @ValueSource(strings = {"a", "1a", "abc"})
    @ParameterizedTest
    void validateDigit(String input) {
        assertValidationFailsForInvalidDate(input);
    }

    @DisplayName("1 ~ 31 사이의 숫자가 아닌 입력 예외 처리")
    @ValueSource(strings = {"0", "-99", "32", "67"})
    @ParameterizedTest
    void validateDateOnlyFrom1To31(String input) {
        assertValidationFailsForInvalidDate(input);
    }

    void assertValidationFailsForInvalidDate(String input) {
        assertThatThrownBy(() -> DateValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
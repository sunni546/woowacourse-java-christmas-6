package christmas.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuValidatorTest {
    @DisplayName("메뉴 입력 형식이 예시와 다른 입력 예외 처리")
    @ValueSource(strings = {"", "타파스", "1", "타파스-", "타파스-1,", "타파스-1제로콜라-1", "타파스-1,제로콜라-"})
    @ParameterizedTest
    void validateMenuFormat(String input) {
        assertValidationFailsForInvalidMenu(input);
    }

    @DisplayName("메뉴판에 없는 메뉴 입력 예외 처리")
    @ValueSource(strings = {"쉬림프파스타-1", "해산물파스타-2,콜라-2,레드와인-1"})
    @ParameterizedTest
    void validateMenuExistence(String input) {
        assertValidationFailsForInvalidMenu(input);
    }

    @DisplayName("각 메뉴의 개수가 1 이상의 숫자가 아닌 입력 예외 처리")
    @ValueSource(strings = {"바비큐립-0", "티본스테이크-1,초코케이크-0,레드와인-2"})
    @ParameterizedTest
    void validateMenuQuantity(String input) {
        assertValidationFailsForInvalidMenu(input);
    }

    @DisplayName("중복 메뉴 입력 예외 처리")
    @ValueSource(strings = {"양송이수프-1,크리스마스파스타-1,레드와인-2,양송이수프-2", "아이스크림-2,아이스크림-2"})
    @ParameterizedTest
    void validateDuplicateMenu(String input) {
        assertValidationFailsForInvalidMenu(input);
    }

    @DisplayName("음료만 주문한 입력 예외 처리")
    @ValueSource(strings = {"샴페인-2", "제로콜라-3,레드와인-1"})
    @ParameterizedTest
    void validateDrinkOnlyOrder(String input) {
        assertValidationFailsForInvalidMenu(input);
    }

    @DisplayName("주문한 메뉴가 20개를 초과하는 입력 예외 처리")
    @ValueSource(strings = {"해산물파스타-22", "시저샐러드-3,티본스테이크-5,바비큐립-1,초코케이크-2,제로콜라-10"})
    @ParameterizedTest
    void validateOrderQuantityLimit(String input) {
        assertValidationFailsForInvalidMenu(input);
    }

    void assertValidationFailsForInvalidMenu(String input) {
        assertThatThrownBy(() -> MenuValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
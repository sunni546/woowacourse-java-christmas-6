package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class EventTest {
    @DisplayName("Event의 orderTotal 얻기")
    @ValueSource(ints = {6000, 142000})
    @ParameterizedTest
    void getOrderTotal(int orderTotal) {
        Event event = new Event(orderTotal);

        assertThat(event.orderTotal()).isEqualTo(orderTotal);
    }

    @DisplayName("증정 이벤트에 해당하는 지 확인")
    @CsvSource({"120000, true", "142000, true", "7000, false"})
    @ParameterizedTest
    void hasGiftEvent(int orderTotal, boolean condition) {
        Event event = new Event(orderTotal);

        assertThat(event.hasGiftEvent()).isEqualTo(condition);
    }

    @DisplayName("특별 할인에 해당하는 지 확인")
    @CsvSource({"15000, 10, true", "142000, 6, false", "7000, 10, false"})
    @ParameterizedTest
    void canApplySpecialDiscount(int orderTotal, int date, boolean condition) {
        Event event = new Event(orderTotal);

        assertThat(event.canApplySpecialDiscount(date)).isEqualTo(condition);
    }

    @DisplayName("주말 할인에 해당하는 지 확인")
    @CsvSource({"15000, 1, true", "142000, 4, false", "7000, 1, false"})
    @ParameterizedTest
    void canApplyWeekendDiscount(int orderTotal, int date, boolean condition) {
        Event event = new Event(orderTotal);

        assertThat(event.canApplyWeekendDiscount(date)).isEqualTo(condition);
    }

    @DisplayName("평일 할인에 해당하는 지 확인")
    @CsvSource({"15000, 4, true", "142000, 1, false", "7000, 4, false"})
    @ParameterizedTest
    void canApplyWeekdayDiscount(int orderTotal, int date, boolean condition) {
        Event event = new Event(orderTotal);

        assertThat(event.canApplyWeekdayDiscount(date)).isEqualTo(condition);
    }

    @DisplayName("크리스마스 디데이 할인에 해당하는 지 확인")
    @CsvSource({"15000, 4, true", "142000, 26, false", "7000, 4, false"})
    @ParameterizedTest
    void canApplyChristmasDiscount(int orderTotal, int date, boolean condition) {
        Event event = new Event(orderTotal);

        assertThat(event.canApplyChristmasDiscount(date)).isEqualTo(condition);
    }

    @DisplayName("크리스마스 당일까지, 1일부터 지나온 Day 수 얻기")
    @CsvSource({"15000, 4, 3", "142000, 16, 15"})
    @ParameterizedTest
    void getDaysUntilChristmas(int orderTotal, int date, int days) {
        Event event = new Event(orderTotal);

        assertThat(event.getDaysUntilChristmas(date)).isEqualTo(days);
    }
}
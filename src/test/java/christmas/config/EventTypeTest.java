package christmas.config;

import static christmas.config.EventType.CHRISTMAS_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTypeTest {
    @DisplayName("EventType의 title 얻기")
    @Test
    void getTitle() {
        assertThat(CHRISTMAS_DISCOUNT.getTitle()).isEqualTo("크리스마스 디데이 할인");
    }
}
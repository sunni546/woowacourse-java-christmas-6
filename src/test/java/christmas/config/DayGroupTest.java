package christmas.config;

import static christmas.config.DayGroup.WEEKEND;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DayGroupTest {
    @DisplayName("DayGroup에 해당 date가 있는 지 확인")
    @Test
    void hasDate() {
        assertThat(WEEKEND.hasDate(16)).isEqualTo(true);
    }
}
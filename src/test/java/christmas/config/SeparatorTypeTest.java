package christmas.config;

import static christmas.config.SeparatorType.LINE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SeparatorTypeTest {
    @DisplayName("SeparatorType의 symbol 얻기")
    @Test
    void getSymbol() {
        assertThat(LINE.getSymbol()).isEqualTo(System.lineSeparator());
    }
}
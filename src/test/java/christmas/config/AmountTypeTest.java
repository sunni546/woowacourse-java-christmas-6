package christmas.config;

import static christmas.config.AmountType.DECEMBER_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountTypeTest {
    @DisplayName("AmountType의 amount 얻기")
    @Test
    void getAmount() {
        assertThat(DECEMBER_DISCOUNT.getAmount()).isEqualTo(2023);
    }
}
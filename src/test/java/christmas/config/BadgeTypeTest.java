package christmas.config;

import static christmas.config.BadgeType.TREE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTypeTest {
    @DisplayName("price를 통해 해당하는 BadgeType 결정")
    @ValueSource(ints = {10000, 12000, 19999})
    @ParameterizedTest
    void determineBadgeByPrice(int price) {
        assertThat(BadgeType.determineBadgeByPrice(price)).isEqualTo(TREE);
    }

    @DisplayName("BadgeType의 name 얻기")
    @Test
    void getName() {
        assertThat(TREE.getName()).isEqualTo("트리");
    }
}
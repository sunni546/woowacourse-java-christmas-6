package christmas.config;

import static christmas.config.MenuGroup.APPETIZER;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuGroupTest {
    @DisplayName("MenuType을 통해 해당하는 MenuGroup 찾기")
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드"})
    @ParameterizedTest
    void findByMenuType(String name) {
        MenuType menuType = MenuType.findByName(name);

        assertThat(MenuGroup.findByMenuType(menuType)).isEqualTo(APPETIZER);
    }
}
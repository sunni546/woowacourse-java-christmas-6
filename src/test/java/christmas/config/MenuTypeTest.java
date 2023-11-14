package christmas.config;

import static christmas.config.MenuType.CHRISTMAS_PASTA;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTypeTest {
    @DisplayName("name를 통해 해당하는 MenuType 찾기")
    @Test
    void findByName() {
        String name = "크리스마스파스타";

        assertThat(MenuType.findByName(name)).isEqualTo(CHRISTMAS_PASTA);
    }

    @DisplayName("MenuType의 name 얻기")
    @Test
    void getName() {
        assertThat(CHRISTMAS_PASTA.getName()).isEqualTo("크리스마스파스타");
    }

    @DisplayName("MenuType의 price 얻기")
    @Test
    void getPrice() {
        assertThat(CHRISTMAS_PASTA.getPrice()).isEqualTo(25000);
    }
}
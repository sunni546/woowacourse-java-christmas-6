package christmas.view.message;

import static christmas.view.message.SystemMessage.INTRO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SystemMessageTest {
    @DisplayName("SystemMessage의 title 얻기")
    @Test
    void getMessage() {
        assertThat(INTRO.getMessage()).isEqualTo("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }
}
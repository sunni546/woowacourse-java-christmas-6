package christmas.view.message;

import static christmas.view.message.ErrorMessage.INVALID_DATE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ErrorMessageTest {
    @DisplayName("[ERROR] 포함 확인")
    @Test
    void containDefaultErrorMessage() {
        assertThat(INVALID_DATE.getErrorMessage()).contains("[ERROR]");
    }

    @DisplayName("ErrorMessage의 에러 메세지 얻기")
    @Test
    void getErrorMessage() {
        assertThat(INVALID_DATE.getErrorMessage()).isEqualTo("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
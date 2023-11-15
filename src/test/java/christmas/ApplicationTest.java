package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.view.message.ErrorMessage.INVALID_DATE;
import static christmas.view.message.ErrorMessage.INVALID_ORDER;
import static christmas.view.message.SystemMessage.PREVIEW_EVENT_BENEFITS;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 전체_기능_테스트() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!",
                    "티본스테이크 1개",
                    "바비큐립 1개",
                    "초코케이크 2개",
                    "제로콜라 1개",
                    "142,000원",
                    "샴페인 1개",
                    "크리스마스 디데이 할인: -1,200원",
                    "평일 할인: -4,046원",
                    "특별 할인: -1,000원",
                    "증정 이벤트: -25,000원",
                    "-31,246원",
                    "135,754원",
                    "산타"
            );
        });
    }

    @Test
    void 증정_메뉴_없음_출력() {
        assertSimpleTest(() -> {
            run("9", "시저샐러드-1,아이스크림-1");
            assertThat(output()).contains("<증정 메뉴>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 이벤트_배지_없음_출력() {
        assertSimpleTest(() -> {
            run("30", "제로콜라-1,양송이수프-1");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 날짜_에러_메시지_출력_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("a1", "1", "크리스마스파스타-1");
            assertThat(output()).contains(
                    INVALID_DATE.getErrorMessage(),
                    String.format(PREVIEW_EVENT_BENEFITS.getMessage(), 1)
            );
        });
    }

    @Test
    void 주문_에러_메시지_출력_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1", "크리스마스파스타-b", "크리스마스파스타-2");
            assertThat(output()).contains(
                    INVALID_ORDER.getErrorMessage(),
                    "크리스마스파스타 2개"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}

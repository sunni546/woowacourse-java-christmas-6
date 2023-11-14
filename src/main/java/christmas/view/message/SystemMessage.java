package christmas.view.message;

import static christmas.config.SeparatorType.LINE;

public enum SystemMessage {
    INTRO("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    INPUT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INPUT_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    PREVIEW_EVENT_BENEFITS("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + LINE.getSymbol()),
    ORDERED_MENU(LINE.getSymbol() + "<주문 메뉴>" + LINE.getSymbol() + "%s"),
    UNDISCOUNTED_ORDER_TOTAL(LINE.getSymbol() + "<할인 전 총주문 금액>" + LINE.getSymbol() + "%,d원" + LINE.getSymbol()),
    GIFT_MENU(LINE.getSymbol() + "<증정 메뉴>" + LINE.getSymbol() + "%s"),
    PROMOTION_DETAILS(LINE.getSymbol() + "<혜택 내역>" + LINE.getSymbol() + "%s"),
    TOTAL_BENEFIT_AMOUNT(LINE.getSymbol() + "<총혜택 금액>" + LINE.getSymbol() + "%,d원" + LINE.getSymbol()),
    DISCOUNTED_PAYMENT_TOTAL(LINE.getSymbol() + "<할인 후 예상 결제 금액>" + LINE.getSymbol() + "%,d원" + LINE.getSymbol()),
    DECEMBER_EVENT_BADGE(LINE.getSymbol() + "<12월 이벤트 배지>" + LINE.getSymbol() + "%s");

    private final String message;

    SystemMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

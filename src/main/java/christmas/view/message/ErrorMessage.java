package christmas.view.message;

public enum ErrorMessage {
    DEFAULT_ERROR_MESSAGE("[ERROR] "),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return DEFAULT_ERROR_MESSAGE.message + message;
    }
}

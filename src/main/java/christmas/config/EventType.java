package christmas.config;

public enum EventType {
    GIFT_EVENT("증정 이벤트"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인");

    private final String title;

    EventType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

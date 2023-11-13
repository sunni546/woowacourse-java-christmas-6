package christmas.domain;

public class Event {
    private static final int EVENT_THRESHOLD_AMOUNT = 10000;
    private static final int GIFT_THRESHOLD_AMOUNT = 120000;

    private final int orderTotal;

    public Event(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public boolean hasGiftEvent() {
        return orderTotal > GIFT_THRESHOLD_AMOUNT;
    }


}

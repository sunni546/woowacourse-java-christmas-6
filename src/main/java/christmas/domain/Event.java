package christmas.domain;

public class Event {
    private static final int GIFT_THRESHOLD_AMOUNT = 120000;

    public boolean hasGiftEvent(int total) {
        return total > GIFT_THRESHOLD_AMOUNT;
    }
}

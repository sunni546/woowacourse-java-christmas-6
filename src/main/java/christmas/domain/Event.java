package christmas.domain;

import static christmas.config.DayGroup.STAR;
import static christmas.config.DayGroup.WEEKDAY;
import static christmas.config.DayGroup.WEEKEND;

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

    private boolean canApplyEvent() {
        return orderTotal > EVENT_THRESHOLD_AMOUNT;
    }

    public boolean canApplySpecialDiscount(int date) {
        if (canApplyEvent()) {
            return STAR.hasDate(date);
        }
        return false;
    }

    public boolean canApplyWeekendDiscount(int date) {
        if (canApplyEvent()) {
            return WEEKEND.hasDate(date);
        }
        return false;
    }

    public boolean canApplyWeekdayDiscount(int date) {
        if (canApplyEvent()) {
            return WEEKDAY.hasDate(date);
        }
        return false;
    }
}

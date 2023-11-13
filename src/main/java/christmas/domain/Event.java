package christmas.domain;

import static christmas.config.AmountType.EVENT_THRESHOLD;
import static christmas.config.AmountType.GIFT_THRESHOLD;
import static christmas.config.DayGroup.STAR;
import static christmas.config.DayGroup.WEEKDAY;
import static christmas.config.DayGroup.WEEKEND;

public class Event {
    private static final int CHRISTMAS_EVENT_START_DATE = 1;
    private static final int CHRISTMAS_EVENT_END_DATE = 25;

    private final int orderTotal;

    public Event(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public boolean hasGiftEvent() {
        return orderTotal > GIFT_THRESHOLD.getAmount();
    }

    private boolean canApplyEvent() {
        return orderTotal > EVENT_THRESHOLD.getAmount();
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

    public boolean canApplyChristmasDiscount(int date) {
        if (canApplyEvent()) {
            return isInChristmasEventPeriod(date);
        }
        return false;
    }

    private boolean isInChristmasEventPeriod(int date) {
        return date <= CHRISTMAS_EVENT_END_DATE;
    }

    public int getDaysUntilChristmas(int date) {
        return date - CHRISTMAS_EVENT_START_DATE;
    }
}

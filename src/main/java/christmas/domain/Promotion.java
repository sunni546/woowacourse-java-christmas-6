package christmas.domain;

import static christmas.config.EventType.GIFT_EVENT;
import static christmas.config.EventType.SPECIAL_DISCOUNT;
import static christmas.config.MenuType.CHAMPAGNE;
import static christmas.domain.Order.ORDER_OUTPUT_FORMAT;

public class Promotion {
    private static final String NO_CONTENT = "없음\n";
    private static final String PROMOTION_OUTPUT_FORMAT = "%s: -%,d원\n";
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;

    private final Event event;
    private final StringBuilder details;

    public Promotion(Order order) {
        StringBuilder details = new StringBuilder();
        Event event = new Event(order.getUndiscountedOrderTotal());
        int date = order.getDate();

        addGiftEventDetails(details, event);
        addSpecialDiscountDetails(details, event, date);

        this.event = event;
        this.details = details;
    }

    public String getGiftMenu() {
        if (event.hasGiftEvent()) {
            return String.format(ORDER_OUTPUT_FORMAT, CHAMPAGNE.getName(), 1);
        }
        return NO_CONTENT;
    }

    public StringBuilder getDetails() {
        if (details.length() == 0) {
            return new StringBuilder(NO_CONTENT);
        }

        return details;
    }

    private void addPromotionDetail(StringBuilder details, String title, int price) {
        details.append(String.format(PROMOTION_OUTPUT_FORMAT, title, price));
    }

    private void addGiftEventDetails(StringBuilder details, Event event) {
        if (event.hasGiftEvent()) {
            addPromotionDetail(details, GIFT_EVENT.getTitle(), CHAMPAGNE.getPrice());
        }
    }

    private void addSpecialDiscountDetails(StringBuilder details, Event event, int date) {
        if (event.canApplySpecialDiscount(date)) {
            addPromotionDetail(details, SPECIAL_DISCOUNT.getTitle(), SPECIAL_DISCOUNT_AMOUNT);
        }
    }
}

package christmas.domain;

import static christmas.config.EventType.GIFT_EVENT;
import static christmas.config.EventType.SPECIAL_DISCOUNT;
import static christmas.config.EventType.WEEKEND_DISCOUNT;
import static christmas.config.MenuGroup.MAIN;
import static christmas.config.MenuType.CHAMPAGNE;
import static christmas.domain.Order.ORDER_OUTPUT_FORMAT;

import christmas.config.MenuGroup;
import christmas.config.MenuType;
import java.util.HashMap;
import java.util.Set;

public class Promotion {
    private static final String NO_CONTENT = "없음\n";
    private static final String PROMOTION_OUTPUT_FORMAT = "%s: -%,d원\n";
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;
    private static final int DECEMBER_DISCOUNT_AMOUNT = 2023;

    private final Event event;
    private final StringBuilder details;

    public Promotion(Order order) {
        StringBuilder details = new StringBuilder();
        Event event = new Event(order.getUndiscountedOrderTotal());
        int date = order.getDate();

        addGiftEventDetails(details, event);
        addSpecialDiscountDetails(details, event, date);
        addWeekendDiscountDetails(details, event, date, order);

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

    private void addWeekendDiscountDetails(StringBuilder details, Event event, int date, Order order) {
        if (event.canApplyWeekendDiscount(date)) {
            int discountAmount = calculateDiscount(order.getOrders(), MAIN);
            addPromotionDetail(details, WEEKEND_DISCOUNT.getTitle(), discountAmount);
        }
    }

    private int calculateDiscount(HashMap<MenuType, Integer> orders, MenuGroup menuGroup) {
        int count = 0;

        Set<MenuType> menuTypes = orders.keySet();
        for (MenuType menuType : menuTypes) {
            if (MenuGroup.findByMenuType(menuType) == menuGroup) {
                count += orders.get(menuType);
            }
        }

        return DECEMBER_DISCOUNT_AMOUNT * count;
    }
}

package christmas.domain;

import static christmas.config.AmountType.CHRISTMAS_INCREMENT;
import static christmas.config.AmountType.DECEMBER_DISCOUNT;
import static christmas.config.AmountType.DEFAULT_DISCOUNT;
import static christmas.config.EventType.CHRISTMAS_DISCOUNT;
import static christmas.config.EventType.GIFT_EVENT;
import static christmas.config.EventType.SPECIAL_DISCOUNT;
import static christmas.config.EventType.WEEKDAY_DISCOUNT;
import static christmas.config.EventType.WEEKEND_DISCOUNT;
import static christmas.config.MenuGroup.DESSERT;
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

    private final Event event;
    private final StringBuilder details;

    public Promotion(Order order) {
        StringBuilder details = new StringBuilder();
        int date = order.getDate();

        this.event = new Event(order.getUndiscountedOrderTotal());

        addGiftEventDetails(details);
        addSpecialDiscountDetails(details, date);
        addWeekendDiscountDetails(details, date, order);
        addWeekdayDiscountDetails(details, date, order);
        addChristmasDiscountDetails(details, date);

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

    private void addGiftEventDetails(StringBuilder details) {
        if (event.hasGiftEvent()) {
            addPromotionDetail(details, GIFT_EVENT.getTitle(), CHAMPAGNE.getPrice());
        }
    }

    private void addSpecialDiscountDetails(StringBuilder details, int date) {
        if (event.canApplySpecialDiscount(date)) {
            addPromotionDetail(details, SPECIAL_DISCOUNT.getTitle(), DEFAULT_DISCOUNT.getAmount());
        }
    }

    private void addWeekendDiscountDetails(StringBuilder details, int date, Order order) {
        if (event.canApplyWeekendDiscount(date)) {
            int discountAmount = calculateDecemberDiscount(order.getOrders(), MAIN);

            addPromotionDetail(details, WEEKEND_DISCOUNT.getTitle(), discountAmount);
        }
    }

    private void addWeekdayDiscountDetails(StringBuilder details, int date, Order order) {
        if (event.canApplyWeekdayDiscount(date)) {
            int discountAmount = calculateDecemberDiscount(order.getOrders(), DESSERT);

            addPromotionDetail(details, WEEKDAY_DISCOUNT.getTitle(), discountAmount);
        }
    }

    private int calculateDecemberDiscount(HashMap<MenuType, Integer> orders, MenuGroup menuGroup) {
        int count = 0;

        Set<MenuType> menuTypes = orders.keySet();
        for (MenuType menuType : menuTypes) {
            if (MenuGroup.findByMenuType(menuType) == menuGroup) {
                count += orders.get(menuType);
            }
        }

        return DECEMBER_DISCOUNT.getAmount() * count;
    }

    private void addChristmasDiscountDetails(StringBuilder details, int date) {
        if (event.canApplyChristmasDiscount(date)) {
            int discountAmount = calculateChristmasDiscount(date);

            addPromotionDetail(details, CHRISTMAS_DISCOUNT.getTitle(), discountAmount);
        }
    }

    private int calculateChristmasDiscount(int date) {
        int discountAmountByDays = CHRISTMAS_INCREMENT.getAmount() * event.getDaysUntilChristmas(date);
        return DEFAULT_DISCOUNT.getAmount() + discountAmountByDays;
    }
}

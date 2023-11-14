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
import static christmas.domain.Order.LINE_SEPARATOR;
import static christmas.domain.Order.ORDER_OUTPUT_FORMAT;

import christmas.config.BadgeType;
import christmas.config.EventType;
import christmas.config.MenuGroup;
import christmas.config.MenuType;
import java.util.HashMap;
import java.util.Set;

public class Promotion {
    private static final String NO_CONTENT = "없음" + LINE_SEPARATOR;
    private static final String PROMOTION_OUTPUT_FORMAT = "%s: %,d원" + LINE_SEPARATOR;

    private final Event event;
    private final HashMap<EventType, Integer> details;

    public Promotion(Order order) {
        this.event = new Event(order.getUndiscountedOrderTotal());
        this.details = makeDetails(order);
    }

    public String getGiftMenu() {
        if (event.hasGiftEvent()) {
            return String.format(ORDER_OUTPUT_FORMAT, CHAMPAGNE.getName(), 1);
        }
        return NO_CONTENT;
    }

    public HashMap<EventType, Integer> makeDetails(Order order) {
        HashMap<EventType, Integer> details = new HashMap<>();

        makeGiftEventDetail(details);
        makeSpecialDiscountDetail(details, order.getDate());
        makeWeekendDiscountDetail(details, order);
        makeWeekdayDiscountDetail(details, order);
        makeChristmasDiscountDetail(details, order.getDate());

        return details;
    }

    private void makeGiftEventDetail(HashMap<EventType, Integer> details) {
        if (event.hasGiftEvent()) {
            details.put(GIFT_EVENT, CHAMPAGNE.getPrice());
        }
    }

    private void makeSpecialDiscountDetail(HashMap<EventType, Integer> details, int date) {
        if (event.canApplySpecialDiscount(date)) {
            details.put(SPECIAL_DISCOUNT, DEFAULT_DISCOUNT.getAmount());
        }
    }

    private void makeWeekendDiscountDetail(HashMap<EventType, Integer> details, Order order) {
        if (event.canApplyWeekendDiscount(order.getDate())) {
            int discountAmount = calculateDecemberDiscount(order.getOrders(), MAIN);

            if (discountAmount > 0) {
                details.put(WEEKEND_DISCOUNT, discountAmount);
            }
        }
    }

    private void makeWeekdayDiscountDetail(HashMap<EventType, Integer> details, Order order) {
        if (event.canApplyWeekdayDiscount(order.getDate())) {
            int discountAmount = calculateDecemberDiscount(order.getOrders(), DESSERT);

            if (discountAmount > 0) {
                details.put(WEEKDAY_DISCOUNT, discountAmount);
            }
        }
    }

    private void makeChristmasDiscountDetail(HashMap<EventType, Integer> details, int date) {
        if (event.canApplyChristmasDiscount(date)) {
            int discountAmount = calculateChristmasDiscount(date);

            details.put(CHRISTMAS_DISCOUNT, discountAmount);
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

    private int calculateChristmasDiscount(int date) {
        int discountAmountByDays = CHRISTMAS_INCREMENT.getAmount() * event.getDaysUntilChristmas(date);
        return DEFAULT_DISCOUNT.getAmount() + discountAmountByDays;
    }

    public StringBuilder getDetails() {
        StringBuilder details = new StringBuilder();

        this.details.forEach((eventType, discountAmount) -> {
            details.append(String.format(PROMOTION_OUTPUT_FORMAT, eventType.getTitle(), -discountAmount));
        });

        if (details.length() == 0) {
            return new StringBuilder(NO_CONTENT);
        }
        return details;
    }

    public int getTotalBenefitAmount() {
        return calculateGiftMenuPrice() + calculateTotalDiscountAmount();
    }

    private int calculateGiftMenuPrice() {
        if (event.hasGiftEvent()) {
            return CHAMPAGNE.getPrice();
        }
        return 0;
    }

    public int calculateTotalDiscountAmount() {
        int totalDiscountAmount = 0;

        Set<EventType> eventTypes = details.keySet();
        for (EventType eventType : eventTypes) {
            if (eventType != GIFT_EVENT) {
                totalDiscountAmount += details.get(eventType);
            }
        }

        return totalDiscountAmount;
    }

    public String getDecemberEventBadge() {
        BadgeType badge = BadgeType.determineBadgeByPrice(getTotalBenefitAmount());

        if (badge != null) {
            return badge.getName() + LINE_SEPARATOR;
        }

        return NO_CONTENT;
    }
}

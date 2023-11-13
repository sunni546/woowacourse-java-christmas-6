package christmas.view;

import static christmas.view.message.SystemMessage.DISCOUNTED_PAYMENT_TOTAL;
import static christmas.view.message.SystemMessage.GIFT_MENU;
import static christmas.view.message.SystemMessage.INPUT_DATE;
import static christmas.view.message.SystemMessage.INPUT_MENU;
import static christmas.view.message.SystemMessage.INTRO;
import static christmas.view.message.SystemMessage.ORDERED_MENU;
import static christmas.view.message.SystemMessage.PREVIEW_EVENT_BENEFITS;
import static christmas.view.message.SystemMessage.PROMOTION_DETAILS;
import static christmas.view.message.SystemMessage.TOTAL_BENEFIT_AMOUNT;
import static christmas.view.message.SystemMessage.UNDISCOUNTED_ORDER_TOTAL;

public class OutputView {
    public static void printIntro() {
        System.out.println(INTRO.getMessage());
    }

    public static void printInputDate() {
        System.out.println(INPUT_DATE.getMessage());
    }

    public static void printInputMenu() {
        System.out.println(INPUT_MENU.getMessage());
    }

    public static void printDate(int date) {
        System.out.printf(PREVIEW_EVENT_BENEFITS.getMessage(), date);
    }

    public static void printOrderedMenu(StringBuilder menu) {
        System.out.printf(ORDERED_MENU.getMessage(), menu);
    }

    public static void printUndiscountedOrderTotal(int amount) {
        System.out.printf(UNDISCOUNTED_ORDER_TOTAL.getMessage(), amount);
    }

    public static void printGiftMenu(String gift) {
        System.out.printf(GIFT_MENU.getMessage(), gift);
    }

    public static void printPromotionDetails(StringBuilder promotionDetails) {
        System.out.printf(PROMOTION_DETAILS.getMessage(), promotionDetails);
    }

    public static void printTotalBenefitAmount(int amount) {
        System.out.printf(TOTAL_BENEFIT_AMOUNT.getMessage(), -amount);
    }

    public static void printDiscountedPaymentTotal(int amount) {
        System.out.printf(DISCOUNTED_PAYMENT_TOTAL.getMessage(), amount);
    }
}

package christmas.view;

import static christmas.view.message.SystemMessage.INPUT_DATE;
import static christmas.view.message.SystemMessage.INPUT_MENU;
import static christmas.view.message.SystemMessage.INTRO;
import static christmas.view.message.SystemMessage.ORDERED_MENU;
import static christmas.view.message.SystemMessage.PREVIEW_EVENT_BENEFITS;
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

    public static void printUndiscountedOrderTotal(int price) {
        System.out.printf(UNDISCOUNTED_ORDER_TOTAL.getMessage(), price);
    }
}

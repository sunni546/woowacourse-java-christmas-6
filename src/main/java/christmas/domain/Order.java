package christmas.domain;

import static christmas.config.EventType.GIFT_EVENT;
import static christmas.config.MenuType.CHAMPAGNE;

import christmas.config.MenuType;
import java.util.HashMap;
import java.util.Set;

public class Order {
    private static final String MENU_SEPARATOR = ",";
    private static final String ORDER_SEPARATOR = "-";
    private static final String ORDER_OUTPUT_FORMAT = "%s %d개\n";
    private static final String NO_CONTENT = "없음\n";
    private static final String PROMOTION_OUTPUT_FORMAT = "%s: -%,d원\n";

    private final HashMap<MenuType, Integer> orders;

    public Order(String menu) {
        this.orders = makeOrders(menu);
    }

    public HashMap<MenuType, Integer> makeOrders(String orderedMenu) {
        HashMap<MenuType, Integer> orders = new HashMap<>();

        String[] menus = orderedMenu.split(MENU_SEPARATOR);
        for (String menu : menus) {
            String[] order = menu.split(ORDER_SEPARATOR);
            orders.put(MenuType.findByName(order[0]), Integer.parseInt(order[1]));
        }

        return orders;
    }

    public StringBuilder getOrderedMenu() {
        StringBuilder orderedMenu = new StringBuilder();
        orders.forEach((menuType, number) -> {
            orderedMenu.append(String.format(ORDER_OUTPUT_FORMAT, menuType.getName(), number));
        });

        return orderedMenu;
    }

    public int getUndiscountedOrderTotal() {
        int undiscountedOrderTotal = 0;
        Set<MenuType> menuTypes = orders.keySet();
        for (MenuType menuType : menuTypes) {
            undiscountedOrderTotal += menuType.getPrice() * orders.get(menuType);
        }

        return undiscountedOrderTotal;
    }

    public String getGiftMenu() {
        Event event = new Event(getUndiscountedOrderTotal());
        if (event.hasGiftEvent()) {
            return String.format(ORDER_OUTPUT_FORMAT, CHAMPAGNE.getName(), 1);
        }
        return NO_CONTENT;
    }

    public StringBuilder getPromotionDetails() {
        StringBuilder promotionDetails = new StringBuilder();

        Event event = new Event(getUndiscountedOrderTotal());
        if (event.hasGiftEvent()) {
            promotionDetails.append(
                    String.format(PROMOTION_OUTPUT_FORMAT, GIFT_EVENT.getTitle(), CHAMPAGNE.getPrice()));
        }

        return promotionDetails;
    }
}

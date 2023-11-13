package christmas.domain;

import christmas.config.MenuType;
import java.util.HashMap;
import java.util.Set;

public class Order {
    private static final String MENU_SEPARATOR = ",";
    private static final String ORDER_SEPARATOR = "-";
    public static final String ORDER_OUTPUT_FORMAT = "%s %d개\n";

    private final HashMap<MenuType, Integer> orders;
    private final int date;

    public Order(int date, String menu) {
        this.date = date;
        this.orders = makeOrders(menu);
    }

    public int getDate() {
        return date;
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

    public HashMap<MenuType, Integer> getOrders() {
        return orders;
    }
}

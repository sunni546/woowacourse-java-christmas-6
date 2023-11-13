package christmas.domain;

import christmas.MenuType;
import java.util.HashMap;

public class Order {
    private static final String MENU_SEPARATOR = ",";
    private static final String ORDER_SEPARATOR = "-";

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

    public String getOrderedMenu() {
        StringBuilder orderedMenu = new StringBuilder();
        orders.forEach((menuType, number) -> {
            orderedMenu.append(String.format("%s %d개\n", menuType.getName(), number));
        });
        return String.valueOf(orderedMenu);
    }
}

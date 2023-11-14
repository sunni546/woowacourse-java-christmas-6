package christmas.domain;

import static christmas.config.SeparatorType.COMMA;
import static christmas.config.SeparatorType.HYPHEN;
import static christmas.config.SeparatorType.LINE;

import christmas.config.MenuType;
import java.util.HashMap;
import java.util.Set;

public class Order {
    public static final String ORDER_OUTPUT_FORMAT = "%s %d개" + LINE.getSymbol();

    private final HashMap<MenuType, Integer> orders;
    private final int date;

    public Order(int date, String orderedMenu) {
        this.date = date;
        this.orders = makeOrders(orderedMenu);
    }

    public int getDate() {
        return date;
    }

    private HashMap<MenuType, Integer> makeOrders(String orderedMenu) {
        HashMap<MenuType, Integer> orders = new HashMap<>();

        String[] menus = orderedMenu.split(COMMA.getSymbol());
        for (String menu : menus) {
            String[] order = menu.split(HYPHEN.getSymbol());
            orders.put(MenuType.findByName(order[0]), Integer.parseInt(order[1]));
        }

        return orders;
    }

    public StringBuilder getOrderedMenu() {
        StringBuilder orderedMenu = new StringBuilder();

        orders.forEach((menuType, quantity) -> {
            orderedMenu.append(String.format(ORDER_OUTPUT_FORMAT, menuType.getName(), quantity));
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

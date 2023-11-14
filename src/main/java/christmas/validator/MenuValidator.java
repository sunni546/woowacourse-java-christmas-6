package christmas.validator;

import static christmas.config.MenuGroup.DRINK;
import static christmas.config.SeparatorType.COMMA;
import static christmas.config.SeparatorType.HYPHEN;

import christmas.config.MenuGroup;
import christmas.config.MenuType;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuValidator {
    private static final String MENU_ORDER_PATTERN = "^([가-힣]+-[0-9]+)(,([가-힣]+-[0-9]+))*$";
    private static final int MIN_MENU_ORDER_QUANTITY = 1;
    private static final int MAX_TOTAL_MENU_ORDER_QUANTITY = 20;

    public static void validate(String orderedMenu) {
        validateMenuFormat(orderedMenu);

        HashMap<MenuType, Integer> orders = new HashMap<>();
        stringToMap(orderedMenu, orders);

        validateDrinkOnlyOrder(orders);
        validateOrderQuantityLimit(orders);
    }

    private static void validateMenuFormat(String orderedMenu) {
        if (!isMenuFormatValid(orderedMenu)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isMenuFormatValid(String orderedMenu) {
        Pattern pattern = Pattern.compile(MENU_ORDER_PATTERN);
        Matcher matcher = pattern.matcher(orderedMenu);

        return matcher.matches();
    }

    private static void stringToMap(String orderedMenu, HashMap<MenuType, Integer> orders) {
        String[] menus = orderedMenu.split(COMMA.getSymbol());

        for (String menu : menus) {
            String[] order = menu.split(HYPHEN.getSymbol());

            validateMenuExistence(order[0]);
            validateMenuQuantity(Integer.parseInt(order[1]));
            validateDuplicateMenu(orders, MenuType.findByName(order[0]));

            orders.put(MenuType.findByName(order[0]), Integer.parseInt(order[1]));
        }
    }

    private static void validateMenuExistence(String name) {
        try {
            MenuType.findByName(name);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateMenuQuantity(int quantity) {
        if (quantity < MIN_MENU_ORDER_QUANTITY) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateDuplicateMenu(HashMap<MenuType, Integer> orders, MenuType menu) {
        if (orders.containsKey(menu)) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateDrinkOnlyOrder(HashMap<MenuType, Integer> orders) {
        boolean hasDrinkOnly = true;

        Set<MenuType> menuTypes = orders.keySet();
        for (MenuType menuType : menuTypes) {
            if (MenuGroup.findByMenuType(menuType) != DRINK) {
                hasDrinkOnly = false;
                break;
            }
        }

        if (hasDrinkOnly) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateOrderQuantityLimit(HashMap<MenuType, Integer> orders) {
        int total = 0;

        Set<MenuType> menuTypes = orders.keySet();
        for (MenuType menuType : menuTypes) {
            total += orders.get(menuType);
        }

        if (total > MAX_TOTAL_MENU_ORDER_QUANTITY) {
            throw new IllegalArgumentException();
        }
    }
}

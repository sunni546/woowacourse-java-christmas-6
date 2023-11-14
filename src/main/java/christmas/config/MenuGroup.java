package christmas.config;

import java.util.Arrays;
import java.util.List;

public enum MenuGroup {
    APPETIZER(Arrays.asList(MenuType.BUTTON_MUSHROOM_SOUP, MenuType.TAPAS, MenuType.CAESAR_SALAD)),
    MAIN(Arrays.asList(MenuType.T_BONE_STEAK, MenuType.BARBECUE_RIBS, MenuType.SEAFOOD_PASTA,
            MenuType.CHRISTMAS_PASTA)),
    DESSERT(Arrays.asList(MenuType.CHOCOLATE_CAKE, MenuType.ICE_CREAM)),
    DRINK(Arrays.asList(MenuType.ZERO_COLA, MenuType.RED_WINE, MenuType.CHAMPAGNE));

    private final List<MenuType> menus;

    MenuGroup(List<MenuType> menus) {
        this.menus = menus;
    }

    public static MenuGroup findByMenuType(MenuType menuType) {
        return Arrays.stream(MenuGroup.values())
                .filter(menuGroup -> menuGroup.hasMenuType(menuType))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean hasMenuType(MenuType menuType) {
        return menus.stream()
                .anyMatch(menu -> menu == menuType);
    }
}

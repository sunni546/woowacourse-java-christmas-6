package christmas.domain;

import static christmas.config.MenuType.TAPAS;
import static christmas.config.MenuType.ZERO_COLA;
import static christmas.domain.Order.ORDER_OUTPUT_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.config.MenuType;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    private static final int TEST_DATE = 5;
    private static final int TEST_QUANTITY = 1;
    private static final String TEST_ORDERED_MENU = "타파스-1,제로콜라-1";
    private static final int TEST_UNDISCOUNTED_ORDER_TOTAL = 8500;

    private final Order order = new Order(TEST_DATE, TEST_ORDERED_MENU);

    @DisplayName("Order의 date 얻기")
    @Test
    void getDate() {
        assertThat(order.getDate()).isEqualTo(TEST_DATE);
    }

    @DisplayName("주문 메뉴 얻기")
    @Test
    void getOrderedMenu() {
        String expectedMenu = String.format(ORDER_OUTPUT_FORMAT, TAPAS.getName(), TEST_QUANTITY)
                + String.format(ORDER_OUTPUT_FORMAT, ZERO_COLA.getName(), TEST_QUANTITY);

        StringBuilder orderedMenu = order.getOrderedMenu();

        assertThat(orderedMenu.toString()).isEqualTo(expectedMenu);
    }

    @DisplayName("할인 전 총주문 금액 얻기")
    @Test
    void getUndiscountedOrderTotal() {
        assertThat(order.getUndiscountedOrderTotal()).isEqualTo(TEST_UNDISCOUNTED_ORDER_TOTAL);
    }

    @DisplayName("Order의 orders 얻기")
    @Test
    void getOrders() {
        HashMap<MenuType, Integer> orders = new HashMap<>();
        orders.put(TAPAS, TEST_QUANTITY);
        orders.put(ZERO_COLA, TEST_QUANTITY);

        assertThat(order.getOrders()).isEqualTo(orders);
    }
}
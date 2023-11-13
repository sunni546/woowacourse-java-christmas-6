package christmas.controller;

import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {
    public void run() {
        int date = InputView.readDate();
        String menu = InputView.readMenu();

        OutputView.printDate(date);

        Order order = new Order(menu);
        printEventBenefits(order);
    }

    private void printEventBenefits(Order order) {
        OutputView.printOrderedMenu(order.getOrderedMenu());
        OutputView.printUndiscountedOrderTotal(order.getUndiscountedOrderTotal());
        OutputView.printGiftMenu(order.getGiftMenu());
    }
}

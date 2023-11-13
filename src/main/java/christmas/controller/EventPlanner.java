package christmas.controller;

import christmas.domain.Order;
import christmas.domain.Promotion;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {
    public void run() {
        Order order = new Order(InputView.readDate(), InputView.readMenu());

        OutputView.printDate(order.getDate());
        printEventBenefits(order);
    }

    private void printEventBenefits(Order order) {
        OutputView.printOrderedMenu(order.getOrderedMenu());
        OutputView.printUndiscountedOrderTotal(order.getUndiscountedOrderTotal());

        Promotion promotion = new Promotion(order);
        OutputView.printGiftMenu(promotion.getGiftMenu());
        promotion.getDetails();
    }
}

package christmas.controller;

import christmas.domain.Order;
import christmas.domain.Promotion;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {
    public void run() {
        Order order = new Order(InputView.readDate(), InputView.readMenu());

        OutputView.printDate(order.getDate());
        OutputView.printOrderedMenu(order.getOrderedMenu());
        OutputView.printUndiscountedOrderTotal(order.getUndiscountedOrderTotal());

        printEventBenefits(order);
    }

    private void printEventBenefits(Order order) {
        Promotion promotion = new Promotion(order);

        OutputView.printGiftMenu(promotion.getGiftMenu());
        OutputView.printPromotionDetails(promotion.getDetails());
        OutputView.printTotalBenefitAmount(promotion.getTotalBenefitAmount());

        int discountedPaymentTotal = order.getUndiscountedOrderTotal() - promotion.calculateTotalDiscountAmount();
        OutputView.printDiscountedPaymentTotal(discountedPaymentTotal);

        OutputView.printDecemberEventBadge(promotion.getDecemberEventBadge());
    }
}

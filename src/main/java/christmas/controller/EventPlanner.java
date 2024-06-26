package christmas.controller;

import christmas.domain.Order;
import christmas.domain.Promotion;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {
    public void run() {
        OutputView.printIntro();

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

        OutputView.printDiscountedPaymentTotal(promotion.getDiscountedPaymentTotal());
        OutputView.printDecemberEventBadge(promotion.getDecemberEventBadge());
    }
}

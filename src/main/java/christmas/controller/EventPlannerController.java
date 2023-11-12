package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    public void run() {
        int date = InputView.readDate();
        String menu = InputView.readMenu();

        OutputView.printDate(date);
    }
}

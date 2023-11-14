package christmas.view;

import static christmas.view.message.ErrorMessage.INVALID_DATE;
import static christmas.view.message.ErrorMessage.INVALID_ORDER;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.DateValidator;
import christmas.validator.MenuValidator;

public class InputView {
    public static int readDate() {
        OutputView.printInputDate();

        while (true) {
            try {
                String date = Console.readLine();
                DateValidator.validate(date);
                return Integer.parseInt(date);
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(INVALID_DATE.getErrorMessage());
            }
        }
    }

    public static String readMenu() {
        OutputView.printInputMenu();

        while (true) {
            try {
                String orderedMenu = Console.readLine();
                MenuValidator.validate(orderedMenu);
                return orderedMenu;
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(INVALID_ORDER.getErrorMessage());
            }
        }
    }
}

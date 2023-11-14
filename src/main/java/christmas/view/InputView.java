package christmas.view;

import static christmas.view.message.ErrorMessage.INVALID_DATE;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.DateValidator;

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
        return Console.readLine();
    }
}

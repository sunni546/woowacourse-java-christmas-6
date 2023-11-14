package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.DateValidator;

public class InputView {
    public static int readDate() {
        OutputView.printIntro();
        OutputView.printInputDate();

        while (true) {
            try {
                String date = Console.readLine();
                DateValidator.validate(date);
                return Integer.parseInt(date);
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
            }
        }
    }

    public static String readMenu() {
        OutputView.printInputMenu();
        return Console.readLine();
    }
}

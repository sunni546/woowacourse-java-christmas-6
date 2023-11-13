package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static int readDate() {
        OutputView.printIntro();
        OutputView.printInputDate();
        String input = Console.readLine();
        return Integer.parseInt(input);
    }

    public static String readMenu() {
        OutputView.printInputMenu();
        return Console.readLine();
    }
}

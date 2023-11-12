package christmas.view;

import static java.lang.Integer.parseInt;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static int readDate() {
        OutputView.printIntro();
        OutputView.printInputDate();
        String input = Console.readLine();
        return parseInt(input);
    }
}

package christmas.view;

import static christmas.view.message.SystemMessage.INPUT_DATE;
import static christmas.view.message.SystemMessage.INTRO;

public class OutputView {
    public static void printIntro() {
        System.out.println(INTRO.getMessage());
    }

    public static void printInputDate() {
        System.out.println(INPUT_DATE.getMessage());
    }
}

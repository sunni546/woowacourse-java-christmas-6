package christmas;

import christmas.controller.EventPlannerController;

public class Application {
    public static void main(String[] args) {
        EventPlannerController eventPlanner = new EventPlannerController();
        eventPlanner.run();
    }
}

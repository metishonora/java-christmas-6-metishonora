package christmas;

import christmas.controller.Controller;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(InputView.getInstance(), OutputView.getInstance());
        controller.run();
    }

}

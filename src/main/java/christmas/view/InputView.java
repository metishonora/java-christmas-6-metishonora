package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.day.Day;
import christmas.model.day.December2023;
import christmas.model.order.EntireOrder;
import christmas.service.DayReader;
import christmas.service.MenuReader;

public class InputView {
    private static final String REQUEST_VISIT_DATE = "%d월 중 식당 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String REQUEST_ORDERS =
            "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EXCEPTION_DAY = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String EXCEPTION_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final InputView instance = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return instance;
    }

    // TODO: December2023의 맵핑 config에 위임하기
    public Day requestDayOfVisit() {
        System.out.printf(REQUEST_VISIT_DATE + System.lineSeparator(), December2023.MONTH);
        return tryUntilSuccessDay();
    }

    private Day tryUntilSuccessDay() {
        try {
            return DayReader.readDay(Console.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println(EXCEPTION_DAY);
        }
        return tryUntilSuccessDay();
    }

    public EntireOrder requestOrders() {
        System.out.println(REQUEST_ORDERS);
        return tryUntilSuccessOrder();
    }

    private EntireOrder tryUntilSuccessOrder() {
        try {
            return MenuReader.readOrders(Console.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println(EXCEPTION_ORDER);
        }
        return tryUntilSuccessOrder();
    }
}

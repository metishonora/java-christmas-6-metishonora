package christmas;

import christmas.model.menu.Appetizer;
import christmas.model.menu.Dessert;
import christmas.model.menu.Drink;
import christmas.model.menu.Maindish;
import christmas.model.menu.Menu;
import christmas.model.order.Order;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class Logic {
    private static final String DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String MENU_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public static int readDate(String line) {
        try {
            return Optional.ofNullable(line)
                    .map(Integer::parseInt)
                    .filter(i -> i >= 1 && i <= 31)
                    .orElseThrow();
        } catch (NumberFormatException | NoSuchElementException e) {
            throw new IllegalArgumentException(DATE_EXCEPTION);
        }
    }

    public static Menu readMenuName(String line) {
        // 주의: 같은 이름의 메뉴가 있을 경우, 먼저 찾은 메뉴를 반환합니다.
        // 주의: 인터페이스 Menu의 새로운 구현 클래스가 추가될 경우, 아래 목록에도 추가해야 합니다.
        return Stream.of(Appetizer.values(), Maindish.values(), Dessert.values(), Drink.values())
                .flatMap(Stream::of)
                .filter(i -> i.contains(line))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MENU_EXCEPTION));
    }

    public static Integer readCount(String token) {
        try {
            return Optional.of(Integer.parseInt(token))
                    .filter(i -> i > 0)
                    .orElseThrow();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MENU_EXCEPTION);
        }
    }

    public static Order readSingleOrder(String line) {
        String[] tokens = line.split("-");
        if (tokens.length != 2) {
            throw new IllegalArgumentException(MENU_EXCEPTION);
        }

        Menu menu = readMenuName(tokens[0]);
        Integer count = readCount(tokens[1]);

        return new Order(menu, count);
    }
}

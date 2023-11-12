package christmas;

import christmas.model.menu.Appetizer;
import christmas.model.menu.Dessert;
import christmas.model.menu.Drink;
import christmas.model.menu.Maindish;
import christmas.model.menu.Menu;
import christmas.model.order.Order;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
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

    public static Menu readSingleMenu(String line) {
        // 주의: 인터페이스 Menu의 새로운 구현 클래스가 추가될 경우, 이 코드에도 추가되어야 합니다.
        List<Menu> menus = Stream.of(Appetizer.values(), Maindish.values(), Dessert.values(), Drink.values())
                .flatMap(Stream::of)
                .collect(Collectors.toList());

        // 주의: 같은 이름의 메뉴가 있을 경우, 먼저 찾은 메뉴를 반환합니다.
        return menus.stream()
                .filter(i -> i.contains(line))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MENU_EXCEPTION));
    }

    public static Order readSingleOrder(String line) {
        String[] tokens = line.split("-");
        if (tokens.length != 2) {
            throw new IllegalArgumentException(MENU_EXCEPTION);
        }
        try {
            Menu menu = readSingleMenu(tokens[0]);
            Integer count = Integer.parseInt(tokens[1]);
            return new Order(menu, count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MENU_EXCEPTION);
        }
    }

}

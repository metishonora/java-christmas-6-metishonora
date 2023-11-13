package christmas.service;

import christmas.model.menu.Appetizer;
import christmas.model.menu.Dessert;
import christmas.model.menu.Drink;
import christmas.model.menu.Maindish;
import christmas.model.menu.Menu;
import christmas.model.order.EntireOrder;
import christmas.model.order.Order;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MenuReader {
    private static final String MENU_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String MENU_SEPARATOR = "-";
    private static final String ORDER_SEPARATOR = ",";
    private static final Integer MAX_ORDER = 20;

    public static Menu readMenuName(String line) {
        // 주의: 같은 이름의 메뉴가 있을 경우, 먼저 찾은 메뉴를 반환합니다.
        // 주의: 인터페이스 Menu의 새로운 구현 클래스가 추가될 경우, 아래 목록에도 추가해야 합니다.
        return Stream.of(Appetizer.values(), Maindish.values(), Dessert.values(), Drink.values())
                .flatMap(Stream::of)
                .filter(i -> i.getName().equals(line))
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
        String[] tokens = line.split(MENU_SEPARATOR);
        if (tokens.length != 2) {
            throw new IllegalArgumentException(MENU_EXCEPTION);
        }

        Menu menu = readMenuName(tokens[0]);
        Integer count = readCount(tokens[1]);

        return new Order(menu, count);
    }

    public static EntireOrder readOrders(String line) {
        List<Order> orders = Arrays.stream(line.split(ORDER_SEPARATOR))
                .map(MenuReader::readSingleOrder)
                .toList();

        validateOrdersDistinct(orders);
        validateOrdersSize(orders);
        validateOrdersWithNonDrink(orders);

        return new EntireOrder(orders);
    }

    private static void validateOrdersWithNonDrink(List<Order> orders) {
        boolean hasNonDrinkOrder = orders.stream()
                .anyMatch(i -> !(i.menu() instanceof Drink));

        if (!hasNonDrinkOrder) {
            throw new IllegalArgumentException(MENU_EXCEPTION);
        }
    }

    private static void validateOrdersSize(List<Order> orders) {
        int totalOrderCount = orders.stream()
                .mapToInt(Order::count)
                .sum();

        if (totalOrderCount > MAX_ORDER) {
            throw new IllegalArgumentException(MENU_EXCEPTION);
        }
    }

    private static void validateOrdersDistinct(List<Order> orders) {
        int distinctOrderCount = orders.stream()
                .map(Order::menu)
                .distinct()
                .toList()
                .size();

        if (distinctOrderCount != orders.size()) {
            throw new IllegalArgumentException(MENU_EXCEPTION);
        }
    }
}

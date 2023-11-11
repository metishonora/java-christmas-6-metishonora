package christmas;

import christmas.model.menu.Appetizer;
import christmas.model.menu.Dessert;
import christmas.model.menu.Drink;
import christmas.model.menu.Maindish;
import christmas.model.menu.Menu;
import java.util.stream.Stream;

public class Logic {
    private static final String DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String MENU_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public static int readDate(String line) {
        int date;
        try {
            date = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_EXCEPTION);
        }

        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(DATE_EXCEPTION);
        }
        return date;
    }

    public static Menu readSingleMenu(String line) {
        Menu[] menus = Stream.of(Appetizer.values(), Maindish.values(), Dessert.values(), Drink.values())
                .flatMap(Stream::of)
                .toArray(Menu[]::new);

        for (Menu menu : menus) {
            if (menu.contains(line)) {
                return menu;
            }
        }
        throw new IllegalArgumentException(MENU_EXCEPTION);
    }

}

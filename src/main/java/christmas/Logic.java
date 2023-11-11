package christmas;

import christmas.model.menu.Appetizer;
import christmas.model.menu.Dessert;
import christmas.model.menu.Drink;
import christmas.model.menu.Maindish;
import christmas.model.menu.Menu;

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
        if (Appetizer.contains((line))) {
            return Appetizer.valueOf(line);
        }
        if (Maindish.contains(line)) {
            return Maindish.valueOf(line);
        }
        if (Dessert.contains(line)) {
            return Dessert.valueOf(line);
        }
        if (Drink.contains(line)) {
            return Drink.valueOf(line);
        }
        throw new IllegalArgumentException(MENU_EXCEPTION);
    }

}

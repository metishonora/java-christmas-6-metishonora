package christmas;

import christmas.model.Appetizer;
import christmas.model.Dessert;
import christmas.model.Drink;
import christmas.model.Maindish;
import christmas.model.Menu;

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
        if (Appetizer.isAppetizer((line))) {
            return Appetizer.valueOf(line);
        }
        if (Maindish.isMaindish(line)) {
            return Maindish.valueOf(line);
        }
        if (Dessert.isDessert(line)) {
            return Dessert.valueOf(line);
        }
        if (Drink.isDrink(line)) {
            return Drink.valueOf(line);
        }
        throw new IllegalArgumentException(MENU_EXCEPTION);
    }

}

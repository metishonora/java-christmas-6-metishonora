package christmas.model.menu;

import java.util.Arrays;

public enum Drink implements Menu {
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final int price;

    Drink(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean contains(String line) {
        return Arrays.stream(Drink.values())
                .anyMatch(i -> i.getName().equals(line));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getPrice() {
        return price;
    }
}

package christmas.model.menu;

import java.util.Arrays;

public enum Appetizer implements Menu {
    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000);

    private final String name;
    private final int price;

    Appetizer(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static boolean contains(String line) {
        return Arrays.stream(Appetizer.values())
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

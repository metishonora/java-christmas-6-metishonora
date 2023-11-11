package christmas.model.menu;

import java.util.Arrays;

public enum Dessert implements Menu {
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICECREAM("아이스크림", 5000);


    private final String name;
    private final int price;

    private Dessert(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static boolean contains(String line) {
        return Arrays.stream(Dessert.values())
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

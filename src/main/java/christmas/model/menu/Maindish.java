package christmas.model.menu;

import java.util.Arrays;

public enum Maindish implements Menu {
    TBONE_STEAK("티본스테이크", 55000),
    BBQ_RIBS("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000);


    private final String name;
    private final int price;

    Maindish(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean contains(String line) {
        return Arrays.stream(Maindish.values())
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

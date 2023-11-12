package christmas.model.order;

import christmas.model.menu.Menu;

public class Order {
    private final Menu menu;
    private final Integer count;

    public Order(Menu menu, Integer count) {
        this.menu = menu;
        this.count = count;
    }

    public Menu getMenu() {
        return menu;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "[%s-%d]".formatted(menu.getName(), count);
    }
}

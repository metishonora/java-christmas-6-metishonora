package christmas.model.order;

import christmas.model.menu.Menu;

public record Order(Menu menu, Integer count) {
    public Integer calculatePrice() {
        return menu.getPrice() * count;
    }

    @Override
    public String toString() {
        return "[%s-%d]".formatted(menu.getName(), count);
    }
}

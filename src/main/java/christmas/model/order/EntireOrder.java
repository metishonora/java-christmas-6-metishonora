package christmas.model.order;

import christmas.model.menu.Dessert;
import christmas.model.menu.Maindish;
import java.util.List;

public class EntireOrder {
    private final List<Order> orders;

    public EntireOrder(List<Order> orders) {
        this.orders = orders;
    }

    public Integer calculateEntirePrice() {
        return orders.stream()
                .mapToInt(Order::calculatePrice)
                .sum();
    }

    public long countDessert() {
        return orders.stream()
                .filter(i -> i.getMenu() instanceof Dessert)
                .count();
    }

    public long countMaindish() {
        return orders.stream()
                .filter(i -> i.getMenu() instanceof Maindish)
                .count();
    }

    @Override
    public String toString() {
        return orders.toString();
    }
}

package christmas.model.order;

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

    @Override
    public String toString() {
        return orders.toString();
    }
}

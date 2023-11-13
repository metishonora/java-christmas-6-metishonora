package christmas.model.order;

import java.util.List;

public class EntireOrder {
    private final List<Order> orders;

    public EntireOrder(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return orders.toString();
    }
}

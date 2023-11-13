package christmas.model.order;

import christmas.dto.EntireOrderDto;
import christmas.model.menu.Dessert;
import christmas.model.menu.Maindish;
import java.util.Collections;
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

    public Integer countDessert() {
        return orders.stream()
                .filter(i -> i.getMenu() instanceof Dessert)
                .mapToInt(Order::getCount)
                .sum();
    }

    public Integer countMaindish() {
        return orders.stream()
                .filter(i -> i.getMenu() instanceof Maindish)
                .mapToInt(Order::getCount)
                .sum();
    }

    public EntireOrderDto createDto() {
        return new EntireOrderDto(Collections.unmodifiableList(orders), calculateEntirePrice());
    }

    @Override
    public String toString() {
        return orders.toString();
    }
}

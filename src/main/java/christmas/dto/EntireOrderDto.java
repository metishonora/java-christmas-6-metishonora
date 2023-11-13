package christmas.dto;

import christmas.model.order.Order;
import java.util.List;

public record EntireOrderDto(
        List<Order> orders,
        Integer entirePrice
) {
}

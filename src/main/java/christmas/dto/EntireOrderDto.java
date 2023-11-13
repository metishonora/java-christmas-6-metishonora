package christmas.dto;

import christmas.model.order.Order;
import java.util.List;

// TODO: unmodifiable List로 건네줄 것
public record EntireOrderDto(
        List<Order> orders,
        Integer entirePrice
) {
}

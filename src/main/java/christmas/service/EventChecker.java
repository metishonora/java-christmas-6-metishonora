package christmas.service;

import christmas.model.badge.Badge;
import christmas.model.day.Day;
import christmas.model.event.ChampagneGiveawayEvent;
import christmas.model.event.ChristmasDdayDiscountEvent;
import christmas.model.event.Event;
import christmas.model.event.SpecialDiscount;
import christmas.model.event.WeekdayDiscountEvent;
import christmas.model.event.WeekendDiscountEvent;
import christmas.model.order.EntireOrder;
import java.util.List;

public class EventChecker {
    private final List<Event> events;

    public EventChecker() {
        // 주의: 새로운 이벤트 구현체가 추가될 경우, 이 목록에도 추가해야 합니다.
        events = List.of(
                new ChampagneGiveawayEvent(),
                new ChristmasDdayDiscountEvent(),
                new SpecialDiscount(),
                new WeekdayDiscountEvent(),
                new WeekendDiscountEvent());
    }

    public Integer calculateTotalBenefitOf(EntireOrder orders, Day day) {
        return events.stream()
                .mapToInt(i -> i.getEventBenefitAmount(orders, day))
                .sum();
    }

    public Integer calculateFinalPrice(EntireOrder orders, Day day) {
        return orders.calculateEntirePrice() - calculateTotalBenefitOf(orders, day);
    }

    public Badge checkBadge(EntireOrder orders, Day day) {
        return Badge.getBadgeFrom(calculateTotalBenefitOf(orders, day));
    }
}

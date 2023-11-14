package christmas.service;

import christmas.dto.EventDto;
import christmas.model.badge.Badge;
import christmas.model.day.Day;
import christmas.model.event.ChampagneGiveawayEvent;
import christmas.model.event.ChristmasDdayDiscountEvent;
import christmas.model.event.Event;
import christmas.model.event.SpecialDiscount;
import christmas.model.event.WeekdayDiscountEvent;
import christmas.model.event.WeekendDiscountEvent;
import christmas.model.order.EntireOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public String checkGiveawayEvent(EntireOrder orders, Day day) {
        // 주의: 이벤트 구현체의 순서가 변경될 경우, 이 메서드의 인덱스도 변경되어야 합니다.
        return ((ChampagneGiveawayEvent) events.get(0)).giveAwayEventResult(orders, day);
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

    public EventDto createDto(EntireOrder orders, Day day) {
        String giveawayEventResult = checkGiveawayEvent(orders, day);
        Map<String, Integer> eventResults = events.stream()
                .collect(Collectors.toMap(Event::getEventName, i -> i.getEventBenefitAmount(orders, day)));

        List<String> eventList = new ArrayList<>();
        List<Integer> benefitAmount = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : eventResults.entrySet()) {
            if (entry.getValue() > 0) {
                eventList.add(entry.getKey());
                benefitAmount.add(entry.getValue());
            }
        }

        return new EventDto(giveawayEventResult,
                eventList,
                benefitAmount,
                eventList.size(),
                calculateTotalBenefitOf(orders, day),
                calculateFinalPrice(orders, day),
                checkBadge(orders, day).getName());
    }
}

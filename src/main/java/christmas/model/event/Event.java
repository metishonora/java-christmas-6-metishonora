package christmas.model.event;

import christmas.model.day.Day;
import christmas.model.order.EntireOrder;

public interface Event {
    int MINIMUM_PURCHASE_FOR_ENTIRE_EVENT = 10_000;

    static boolean isEligibleForEntireEvent(EntireOrder orders) {
        return orders.calculateEntirePrice() >= MINIMUM_PURCHASE_FOR_ENTIRE_EVENT;
    }

    boolean isEligible(EntireOrder orders, Day day);

    String getEventName();

    Integer getEventBenefitAmount(EntireOrder orders, Day day);
}

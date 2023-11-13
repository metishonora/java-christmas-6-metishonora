package christmas.model.event;

import christmas.model.day.Day;
import christmas.model.order.EntireOrder;

public interface Event {
    public static boolean isEligibleForEntireEvent(EntireOrder orders) {
        return orders.calculateEntirePrice() >= 10000;
    }

    boolean isEligible(EntireOrder orders, Day day);

    String getEventName();

    Integer getEventBenefitAmount(EntireOrder orders, Day day);
}

package christmas.model.event;

import christmas.model.day.Day;
import christmas.model.order.EntireOrder;

public interface Event {
    public static int MINUMUM_PURCHASE = 10_000;

    public static boolean isEligibleForEntireEvent(EntireOrder orders) {
        return orders.calculateEntirePrice() >= MINUMUM_PURCHASE;
    }

    boolean isEligible(EntireOrder orders, Day day);

    String getEventName();

    Integer getEventBenefitAmount(EntireOrder orders, Day day);
}

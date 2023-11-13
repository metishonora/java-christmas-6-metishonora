package christmas.model.event;

import christmas.model.order.EntireOrder;

public interface Event {
    public static boolean isEligibleForEntireEvent(EntireOrder orders) {
        return orders.calculateEntirePrice() >= 10000;
    }

    boolean isEligible(EntireOrder orders);

    String getEventName();

    Integer getEventBenefitAmount();
}

package christmas.model.event;

import christmas.model.day.Day;
import christmas.model.order.EntireOrder;

public class WeekdayDiscountEvent implements Event {
    private static final String WEEKDAY_DISCOUNT_EVENT = "평일 할인";
    private static final int DISCOUNT_MULTIPLIER = 2023;

    @Override
    public boolean isEligible(EntireOrder orders, Day day) {
        return Event.isEligibleForEntireEvent(orders) && !day.isWeekend();
    }

    @Override
    public String getEventName() {
        return WEEKDAY_DISCOUNT_EVENT;
    }

    @Override
    public Integer getEventBenefitAmount(EntireOrder orders, Day day) {
        if (!isEligible(orders, day)) {
            return 0;
        }
        return orders.countDessert() * DISCOUNT_MULTIPLIER;
    }
}

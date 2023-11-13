package christmas.model.event;

import christmas.model.day.Day;
import christmas.model.order.EntireOrder;

public class SpecialDiscount implements Event {
    private static final String SPECIAL_EVENT = "특별 할인";
    private static final int DISCOUNT_AMOUNT = 1_000;

    @Override
    public boolean isEligible(EntireOrder orders, Day day) {
        return Event.isEligibleForEntireEvent(orders) && day.isSpecialDay();
    }

    @Override
    public String getEventName() {
        return SPECIAL_EVENT;
    }

    @Override
    public Integer getEventBenefitAmount(EntireOrder orders, Day day) {
        if (!isEligible(orders, day)) {
            return 0;
        }
        return DISCOUNT_AMOUNT;
    }
}

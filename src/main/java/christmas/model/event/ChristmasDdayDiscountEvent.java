package christmas.model.event;

import christmas.model.day.Day;
import christmas.model.order.EntireOrder;

public class ChristmasDdayDiscountEvent implements Event {
    private static final String CHRISTMAS_DDAY_DISCOUNT_EVENT = "크리스마스 디데이 할인";

    @Override
    public boolean isEligible(EntireOrder orders, Day day) {
        return Event.isEligibleForEntireEvent(orders) && !day.isDayAfter(25);
    }

    @Override
    public String getEventName() {
        return CHRISTMAS_DDAY_DISCOUNT_EVENT;
    }

    @Override
    public Integer getEventBenefitAmount(EntireOrder orders, Day day) {
        if (!isEligible(orders, day)) {
            return 0;
        }
        return 1000 + (day.getDay() - 1) * 100;
    }
}

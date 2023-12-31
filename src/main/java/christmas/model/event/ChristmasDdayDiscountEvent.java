package christmas.model.event;

import christmas.model.day.Day;
import christmas.model.day.December2023;
import christmas.model.order.EntireOrder;

public class ChristmasDdayDiscountEvent implements DiscountEvent {
    private static final String CHRISTMAS_DDAY_DISCOUNT_EVENT = "크리스마스 디데이 할인";
    private static final int BASIC_DISCOUNT = 1_000;
    private static final int ADDED_EACH_DAY = 100;
    private static final int DAY_WHEN_EVENT_ENDS = 26;

    @Override
    public boolean isEligible(EntireOrder orders, Day day) {
        return Event.isEligibleForEntireEvent(orders) && day.isDayAfter(new December2023(DAY_WHEN_EVENT_ENDS));
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
        return BASIC_DISCOUNT + (day.day() - 1) * ADDED_EACH_DAY;
    }
}

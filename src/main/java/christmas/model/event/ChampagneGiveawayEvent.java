package christmas.model.event;

import christmas.model.day.Day;
import christmas.model.menu.Drink;
import christmas.model.order.EntireOrder;

public class ChampagneGiveawayEvent implements Event {
    private static final String CHAMPAGNE_GIVEAWAY_EVENT = "증정 이벤트";
    private static final String GIVEAWAY_EVENT_RESULT = "샴페인 1개";
    private static final String NO_PRIZE = "없음";

    private static final int MINIMUM_PURCHASE = 120_000;

    public String giveAwayEventResult(EntireOrder orders, Day day) {
        if (isEligible(orders, day)) {
            return GIVEAWAY_EVENT_RESULT;
        }
        return NO_PRIZE;
    }

    @Override
    public boolean isEligible(EntireOrder orders, Day day) {
        return Event.isEligibleForEntireEvent(orders) && orders.calculateEntirePrice() >= MINIMUM_PURCHASE;
    }

    @Override
    public String getEventName() {
        return CHAMPAGNE_GIVEAWAY_EVENT;
    }

    @Override
    public Integer getEventBenefitAmount(EntireOrder orders, Day day) {
        if (!isEligible(orders, day)) {
            return 0;
        }
        return Drink.CHAMPAGNE.getPrice();
    }
}

package christmas.model.event;

import christmas.model.day.Day;
import christmas.model.menu.Drink;
import christmas.model.order.EntireOrder;

public class ChampagneGiveawayEvent implements Event {
    private static final String CHAMPAGNE_GIVEAWAY_EVENT = "증정 이벤트";

    @Override
    public boolean isEligible(EntireOrder orders, Day day) {
        return Event.isEligibleForEntireEvent(orders) && orders.calculateEntirePrice() >= 120_000;
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

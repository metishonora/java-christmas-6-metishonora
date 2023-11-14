package christmas.model.event;

import christmas.model.day.Day;
import christmas.model.order.EntireOrder;

public interface GiveawayEvent extends Event {
    String giveawayEventResult(EntireOrder orders, Day day);
}

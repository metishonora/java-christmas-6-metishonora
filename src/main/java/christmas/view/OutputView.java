package christmas.view;

import christmas.dto.EntireOrderDto;
import christmas.dto.EventDto;
import christmas.model.day.Day;
import christmas.model.day.December2023;
import java.util.Optional;
import java.util.stream.IntStream;

public class OutputView {
    private static final String WELCOME_MSG = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.";
    private static final String RESULT_MSG = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String RESULT_ORDERS = "\n<주문 메뉴>";
    private static final String EACH_ORDER = "%s %d개\n";
    private static final String RESULT_BEFORE_EVENT = "\n<할인 전 총주문 금액>";
    private static final String PRICE = "%,d원\n";
    private static final String TOTAL_BENEFIT_AMOUNT = "-%,d원\n";
    private static final String RESULT_GIVEAWAY_EVENT = "\n<증정 메뉴>";
    private static final String RESULT_EVENT_LOG = "\n<혜택 내역>";
    private static final String EVENT_LOG = "%s: -%,d원\n";
    private static final String RESULT_TOTAL_BENEFIT = "\n<총혜택 금액>";
    private static final String RESULT_FINAL_PRICE = "\n<할인 후 예상 결제 금액>";
    private static final String RESULT_BADGE = "\n<%d월 이벤트 배지>";
    private static final String NONE = "없음";
    private static final OutputView instance = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void printWelcome() {
        System.out.printf(WELCOME_MSG + System.lineSeparator(), December2023.MONTH);
    }

    public void printOrderResult(Day day, EntireOrderDto orderDto) {
        System.out.printf(RESULT_MSG + System.lineSeparator(), December2023.MONTH, day.day());
        printEntireOrder(orderDto);
        printPriceBeforeDiscount(orderDto);
    }

    public void printEventResult(EventDto eventDto) {
        printGiveawayEventResult(eventDto);
        printEventLog(eventDto);
        printTotalBenefit(eventDto);
        printFinalPrice(eventDto);
        printBadge(eventDto);
    }

    private void printEntireOrder(EntireOrderDto orderDto) {
        System.out.println(RESULT_ORDERS);
        orderDto.orders()
                .forEach(order -> System.out.printf(EACH_ORDER, order.menu().getName(), order.count()));
    }

    private void printPriceBeforeDiscount(EntireOrderDto orderDto) {
        System.out.println(RESULT_BEFORE_EVENT);
        System.out.printf(PRICE, orderDto.entirePrice());
    }

    private void printGiveawayEventResult(EventDto eventDto) {
        System.out.println(RESULT_GIVEAWAY_EVENT);
        String result = eventDto.giveAwayEventResult().stream()
                .reduce((first, second) -> first + System.lineSeparator() + second)
                .orElse(NONE);
        System.out.println(result);
    }

    private void printEventLog(EventDto eventDto) {
        System.out.println(RESULT_EVENT_LOG);

        if (eventDto.count() == 0) {
            System.out.println(NONE);
        }

        IntStream.range(0, eventDto.count())
                .filter(i -> !eventDto.benefitAmount().get(i).equals(0))
                .forEach(i -> System.out.printf(EVENT_LOG, eventDto.eventList().get(i),
                        eventDto.benefitAmount().get(i)));

    }

    private void printTotalBenefit(EventDto eventDto) {
        System.out.println(RESULT_TOTAL_BENEFIT);
        String result = Optional.of(eventDto.totalBenefit())
                .filter(benefit -> benefit != 0)
                .map(benefit -> String.format(TOTAL_BENEFIT_AMOUNT, benefit))
                .orElse(String.format(PRICE, 0));
        System.out.print(result);
    }

    private void printFinalPrice(EventDto eventDto) {
        System.out.println(RESULT_FINAL_PRICE);
        System.out.printf(PRICE, eventDto.finalPrice());
    }

    private void printBadge(EventDto eventDto) {
        System.out.printf(RESULT_BADGE + System.lineSeparator(), December2023.MONTH);
        System.out.println(eventDto.badgeName());
    }
}

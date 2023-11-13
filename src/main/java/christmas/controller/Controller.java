package christmas.controller;

import christmas.dto.EntireOrderDto;
import christmas.dto.EventDto;
import christmas.model.day.Day;
import christmas.model.order.EntireOrder;
import christmas.service.EventChecker;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcome();
        final Day day = inputView.requestDayOfVisit();
        final EntireOrder entireOrder = inputView.requestOrders();
        final EntireOrderDto entireOrderDto = entireOrder.createDto();
        final EventChecker eventChecker = new EventChecker();
        final EventDto eventDto = eventChecker.createDto(entireOrder, day);
        outputView.printOrderResult(day, entireOrderDto);
        outputView.printEventResult(eventDto);
    }
}

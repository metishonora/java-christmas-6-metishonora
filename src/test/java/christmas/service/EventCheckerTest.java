package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.dto.EventDto;
import christmas.model.day.Day;
import christmas.model.order.EntireOrder;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventCheckerTest {
    private static Stream<Arguments> totalBenefitTestArgs() {
        return Stream.of(
                Arguments.of("타파스-1,제로콜라-1", "26", "0"),
                Arguments.of("티본스테이크-1,제로콜라-1,초코케이크-2,바비큐립-1", "3", "31246"),
                Arguments.of("바비큐립-1,초코케이크-2,아이스크림-3", "25", "14515"),  // 디데이, 평일, 특별
                Arguments.of("바비큐립-1,레드와인-1", "16", "4523"),  // 디데이, 주말
                Arguments.of("티본스테이크-4,아이스크림-3", "28", "31069"),  // 증정, 평일
                Arguments.of("티본스테이크-4", "29", "33092")  // 증정, 주말
        );
    }

    private static Stream<Arguments> finalPriceTestArgs() {
        return Stream.of(
                Arguments.of("타파스-1,제로콜라-1", "26", "8500"),
                Arguments.of("티본스테이크-1,제로콜라-1,초코케이크-2,바비큐립-1", "3", "110754"),
                Arguments.of("바비큐립-1,초코케이크-2,아이스크림-3", "25", "84485"),  // 디데이, 평일, 특별
                Arguments.of("바비큐립-1,레드와인-1", "16", "109477"),  // 디데이, 주말
                Arguments.of("티본스테이크-4,아이스크림-3", "28", "203931"),  // 증정, 평일
                Arguments.of("티본스테이크-4", "29", "186908")  // 증정, 주말
        );
    }

    private static Stream<Arguments> badgeTestArgs() {
        return Stream.of(
                Arguments.of("타파스-1,제로콜라-1", "26", "없음"),
                Arguments.of("바비큐립-1,레드와인-1", "23", "별"),
                Arguments.of("바비큐립-1,초코케이크-2,아이스크림-3", "25", "트리"),
                Arguments.of("티본스테이크-4,아이스크림-3", "28", "산타")
        );
    }

    private static Stream<Arguments> testEventDto() {
        return Stream.of(
                Arguments.of("타파스-1,제로콜라-1", "26", "없음", "0", "0", "8500"),
                Arguments.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1",
                        "3", "샴페인 1개", "4", "31246", "135754")
        );
    }

    @DisplayName("총 혜택 금액")
    @MethodSource("totalBenefitTestArgs")
    @ParameterizedTest
    void testTotalBenefitOf(String entireOrder, String givenDay, Integer answer) {
        EntireOrder order = MenuReader.readOrders(entireOrder);
        Day day = DayReader.readDay(givenDay);
        EventChecker eventChecker = new EventChecker();
        assertThat(eventChecker.calculateTotalBenefitOf(order, day))
                .isEqualTo(answer);
    }

    @DisplayName("할인 후 예상 결제 금액")
    @MethodSource("finalPriceTestArgs")
    @ParameterizedTest
    void calculateFinalPrice(String entireOrder, String givenDay, Integer answer) {
        EntireOrder order = MenuReader.readOrders(entireOrder);
        Day day = DayReader.readDay(givenDay);
        EventChecker eventChecker = new EventChecker();
        assertThat(eventChecker.calculateFinalPrice(order, day))
                .isEqualTo(answer);
    }

    @DisplayName("배지 증정")
    @MethodSource("badgeTestArgs")
    @ParameterizedTest
    void testBadge(String entireOrder, String givenDay, String answer) {
        EntireOrder order = MenuReader.readOrders(entireOrder);
        Day day = DayReader.readDay(givenDay);
        EventChecker eventChecker = new EventChecker();
        assertThat(eventChecker.checkBadge(order, day).getName())
                .isEqualTo(answer);
    }

    @DisplayName("EventDto 작성 확인")
    @MethodSource("testEventDto")
    @ParameterizedTest
    void createDto(String entireOrder, String givenDay, String giveaway, Integer logCount, Integer totalBenefit,
                   Integer finalPrice) {
        EntireOrder order = MenuReader.readOrders(entireOrder);
        Day day = DayReader.readDay(givenDay);
        EventChecker eventChecker = new EventChecker();
        EventDto eventDto = eventChecker.createDto(order, day);

        assertThat(eventDto.giveAwayEventResult()).isEqualTo(giveaway);
        assertThat(eventDto.totalBenefit()).isEqualTo(totalBenefit);
        assertThat(eventDto.eventList().size()).isEqualTo(logCount);
        assertThat(eventDto.totalBenefit()).isEqualTo(totalBenefit);
        assertThat(eventDto.finalPrice()).isEqualTo(finalPrice);
    }
}
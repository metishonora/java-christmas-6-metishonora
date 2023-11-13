package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.day.Day;
import christmas.model.order.EntireOrder;
import christmas.service.DayReader;
import christmas.service.MenuReader;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WeekendDiscountEventTest {
    private static Stream<Arguments> eligibilityArgs() {
        return Stream.of(
                Arguments.of("타파스-1", "1", "false"),  // 총 주문 부족
                Arguments.of("티본스테이크-1", "3", "false"),  // 주말 아님
                Arguments.of("양송이수프-1,제로콜라-1", "24", "false"),  // 주문 부족, 주말 아님
                Arguments.of("티본스테이크-1,초코케이크-1,바비큐립-1", "1", "true"),
                Arguments.of("티본스테이크-1,바비큐립-1", "30", "true")
        );
    }

    private static Stream<Arguments> eventBenefitArgs() {
        return Stream.of(
                Arguments.of("타파스-1", "1", "0"),  // 총 주문 부족
                Arguments.of("티본스테이크-1", "3", "0"),  // 주말 아님
                Arguments.of("양송이수프-1,제로콜라-1", "24", "0"),  // 주문 부족, 주말 아님
                Arguments.of("티본스테이크-1,초코케이크-1,레드와인-1", "8", "2023"),
                Arguments.of("바비큐립-2,아이스크림-4,해산물파스타-1", "16", "6069"),
                Arguments.of("양송이수프-1,타파스-1,아이스크림-5", "22", "0")
        );
    }


    @DisplayName("평일 할인 여부")
    @MethodSource("eligibilityArgs")
    @ParameterizedTest
    void testEligibility(String entireOrder, String givenDay, boolean answer) {
        EntireOrder orders = MenuReader.readOrders(entireOrder);
        Day day = DayReader.readDay(givenDay);
        assertThat(new WeekendDiscountEvent().isEligible(orders, day))
                .isEqualTo(answer);
    }

    @DisplayName("평일 할인 금액")
    @MethodSource("eventBenefitArgs")
    @ParameterizedTest
    void testEventBenefitAmount(String entireOrder, String givenDay, Integer answer) {
        EntireOrder orders = MenuReader.readOrders(entireOrder);
        Day day = DayReader.readDay(givenDay);
        assertThat(new WeekendDiscountEvent().getEventBenefitAmount(orders, day))
                .isEqualTo(answer);
    }
}
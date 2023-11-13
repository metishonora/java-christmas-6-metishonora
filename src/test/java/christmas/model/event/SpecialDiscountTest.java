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

class SpecialDiscountTest {
    private static Stream<Arguments> eligibilityArgs() {
        return Stream.of(
                Arguments.of("타파스-1", "1", "false"),  // 총 주문 부족
                Arguments.of("티본스테이크-1", "4", "false"),  // 별 있는 날 아님
                Arguments.of("양송이수프-1,제로콜라-1", "23", "false"),  // 주문 부족, 별 있는 날 아님
                Arguments.of("티본스테이크-1,초코케이크-1,바비큐립-1", "31", "true"),
                Arguments.of("티본스테이크-1,바비큐립-1", "25", "true")
        );
    }

    private static Stream<Arguments> eventBenefitArgs() {
        return Stream.of(
                Arguments.of("타파스-1", "1", "0"),  // 총 주문 부족
                Arguments.of("티본스테이크-1", "4", "0"),  // 별 있는 날
                Arguments.of("양송이수프-1,제로콜라-1", "23", "0"),  // 주문 부족, 별 있는 날 아님
                Arguments.of("티본스테이크-1,초코케이크-1,레드와인-1", "3", "1000"),
                Arguments.of("바비큐립-2,아이스크림-4,해산물파스타-1", "10", "1000"),
                Arguments.of("양송이수프-1,타파스-1,아이스크림-5", "17", "1000")
        );
    }


    @DisplayName("특별 할인 여부")
    @MethodSource("eligibilityArgs")
    @ParameterizedTest
    void testEligibility(String entireOrder, String givenDay, boolean answer) {
        EntireOrder orders = MenuReader.readOrders(entireOrder);
        Day day = DayReader.readDay(givenDay);
        assertThat(new SpecialDiscount().isEligible(orders, day))
                .isEqualTo(answer);
    }

    @DisplayName("특별 할인 금액")
    @MethodSource("eventBenefitArgs")
    @ParameterizedTest
    void testEventBenefitAmount(String entireOrder, String givenDay, Integer answer) {
        EntireOrder orders = MenuReader.readOrders(entireOrder);
        Day day = DayReader.readDay(givenDay);
        assertThat(new SpecialDiscount().getEventBenefitAmount(orders, day))
                .isEqualTo(answer);
    }
}
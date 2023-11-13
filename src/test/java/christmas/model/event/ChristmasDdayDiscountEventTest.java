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

class ChristmasDdayDiscountEventTest {
    private static Stream<Arguments> eligibilityArgs() {
        return Stream.of(
                Arguments.of("타파스-1", "1", "false"),
                Arguments.of("티본스테이크-1", "10", "true"),
                Arguments.of("티본스테이크-1,초코케이크-1,바비큐립-1", "24", "true"),
                Arguments.of("티본스테이크-1,초코케이크-1,바비큐립-1", "25", "true"),
                Arguments.of("티본스테이크-1,초코케이크-1,바비큐립-1", "26", "false"),
                Arguments.of("티본스테이크-1,초코케이크-1,바비큐립-1", "31", "false")
        );
    }

    private static Stream<Arguments> eventBenefitArgs() {
        return Stream.of(
                Arguments.of("타파스-1", "1", "0"),
                Arguments.of("티본스테이크-1", "10", "1900"),
                Arguments.of("티본스테이크-1,초코케이크-1,바비큐립-1", "25", "3400"),
                Arguments.of("티본스테이크-1,초코케이크-1,바비큐립-1", "31", "0")
        );
    }


    @DisplayName("크리스마스 디데이 이벤트 여부")
    @MethodSource("eligibilityArgs")
    @ParameterizedTest
    void testEligibility(String entireOrder, String givenDay, boolean answer) {
        EntireOrder orders = MenuReader.readOrders(entireOrder);
        Day day = DayReader.readDay(givenDay);
        assertThat(new ChristmasDdayDiscountEvent().isEligible(orders, day))
                .isEqualTo(answer);
    }

    @DisplayName("크리스마스 디데이 할인 금액")
    @MethodSource("eventBenefitArgs")
    @ParameterizedTest
    void testEventBenefitAmount(String entireOrder, String givenDay, Integer answer) {
        EntireOrder orders = MenuReader.readOrders(entireOrder);
        Day day = DayReader.readDay(givenDay);
        assertThat(new ChristmasDdayDiscountEvent().getEventBenefitAmount(orders, day))
                .isEqualTo(answer);
    }
}
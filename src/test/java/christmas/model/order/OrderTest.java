package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.service.MenuReader;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OrderTest {
    private static Stream<Arguments> orderPriceProvider() {
        return Stream.of(
                Arguments.of("타파스-1", "5500"),
                Arguments.of("샴페인-2", "50000"),
                Arguments.of("초코케이크-5", "75000"),
                Arguments.of("티본스테이크-10", "550000")
        );
    }

    @DisplayName("각 주문의 가격 확인")
    @MethodSource("orderPriceProvider")
    @ParameterizedTest
    void calculatePrice(String list, Integer answer) {
        Order order = MenuReader.readSingleOrder(list);
        assertThat(order.calculatePrice())
                .isEqualTo(answer);
    }
}
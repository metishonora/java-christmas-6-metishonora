package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.service.MenuReader;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EntireOrderTest {
    private static Stream<Arguments> entireOrderPriceProvider() {
        return Stream.of(
                Arguments.of("타파스-1", "5500"),
                Arguments.of("타파스-1,제로콜라-1", "8500")
        );
    }

    @DisplayName("전체 주문의 가격 확인")
    @MethodSource("entireOrderPriceProvider")
    @ParameterizedTest
    void calculatePrice(String list, Integer answer) {
        EntireOrder orders = MenuReader.readOrders(list);
        assertThat(orders.calculateEntirePrice())
                .isEqualTo(answer);
    }
}
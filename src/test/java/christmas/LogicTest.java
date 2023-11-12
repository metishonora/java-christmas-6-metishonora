package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.menu.Appetizer;
import christmas.model.menu.Dessert;
import christmas.model.menu.Drink;
import christmas.model.menu.Maindish;
import christmas.model.menu.Menu;
import christmas.model.order.Order;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class LogicTest {
    private static final String DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String MENU_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private static Stream<Arguments> menuSubclassProvider() {
        return Stream.of(
                Arguments.of("양송이수프", Appetizer.class),
                Arguments.of("바비큐립", Maindish.class),
                Arguments.of("초코케이크", Dessert.class),
                Arguments.of("샴페인", Drink.class)
        );
    }

    private static Stream<Arguments> normalOrderProvider() {
        return Stream.of(
                Arguments.of("양송이수프-1", Appetizer.class, 1),
                Arguments.of("바비큐립-2", Maindish.class, 2),
                Arguments.of("초코케이크-3", Dessert.class, 3),
                Arguments.of("제로콜라-4", Drink.class, 4)
        );
    }

    @DisplayName("빈 방문 날짜")
    @ValueSource(strings = {"", " "})
    @ParameterizedTest
    void emptyDate(String input) {
        assertThatThrownBy(() -> Logic.readDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DATE_EXCEPTION);
    }

    @DisplayName("정수가 아닌 방문 날짜")
    @ValueSource(strings = {"abc", "0.1", "987654321987"})
    @ParameterizedTest
    void notIntegerDate(String input) {
        assertThatThrownBy(() -> Logic.readDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DATE_EXCEPTION);
    }

    @DisplayName("범위를 벗어나는 방문 날짜")
    @ValueSource(strings = {"0", "32"})
    @ParameterizedTest
    void tooSmallOrLargeDate(String input) {
        assertThatThrownBy(() -> Logic.readDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DATE_EXCEPTION);
    }

    @DisplayName("정상 날짜")
    @ValueSource(strings = {"1", "10", "31"})
    @ParameterizedTest
    void normalDate(String input) {
        assertThat(Logic.readDate(input))
                .isEqualTo(Integer.parseInt(input));
    }

    @DisplayName("메뉴판에 없는 메뉴")
    @ValueSource(strings = {"라면", "김치찌개"})
    @ParameterizedTest
    void notExistingMenu(String input) {
        assertThatThrownBy(() -> Logic.readMenuName(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MENU_EXCEPTION);
    }

    @DisplayName("정상 메뉴")
    @MethodSource("menuSubclassProvider")
    @ParameterizedTest
    void normalMenu(String input, Class<? extends Menu> expectedClass) {
        Menu menu = Logic.readMenuName(input);
        assertThat(expectedClass).isEqualTo(menu.getClass());
    }

    @DisplayName("입력 형식이 다른 주문")
    @ValueSource(strings = {"양송이수프-1-2", "초코케이크 1", "-1", "5-바비큐립", "제로콜라--10", "제로콜라-"})
    @ParameterizedTest
    void illegalFormatOrder(String input) {
        assertThatThrownBy(() -> Logic.readSingleOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MENU_EXCEPTION);
    }

    @DisplayName("개수가 틀린 주문")
    @ValueSource(strings = {"제로콜라-0.1", "제로콜라-0, 제로콜라-a, 제로콜라--3, 제로콜라-987654321987"})
    @ParameterizedTest
    void illegalCountOrder(String input) {
        assertThatThrownBy(() -> Logic.readSingleOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MENU_EXCEPTION);
    }

    @DisplayName("정상 단일 주문")
    @MethodSource("normalOrderProvider")
    @ParameterizedTest
    void normalSingleOrder(String input, Class<? extends Menu> expectedClass, Integer expectedCount) {
        Order order = Logic.readSingleOrder(input);
        assertThat(expectedClass).isEqualTo(order.getMenu().getClass());
        assertThat(expectedCount).isEqualTo(order.getCount());
    }

    @DisplayName("중복 주문")
    @ValueSource(strings = {"양송이수프-1,양송이수프-2", "초코케이크-1,초코케이크-1", "바비큐립-1,양송이수프-2,바비큐립-3"})
    @ParameterizedTest
    void duplicateOrder(String input) {
        assertThatThrownBy(() -> Logic.readOrders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MENU_EXCEPTION);
    }

    @DisplayName("20개 초과 주문")
    @ValueSource(strings = {"양송이수프-20,제로콜라-1", "양송이수프-10,제로콜라-10,바비큐립-1"})
    @ParameterizedTest
    void tooManyOrder(String input) {
        assertThatThrownBy(() -> Logic.readOrders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MENU_EXCEPTION);
    }

    @DisplayName("음료만 주문")
    @ValueSource(strings = {"제로콜라-1,샴페인-1", "레드와인-10"})
    @ParameterizedTest
    void onlyDrinkOrder(String input) {
        assertThatThrownBy(() -> Logic.readOrders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MENU_EXCEPTION);
    }
}
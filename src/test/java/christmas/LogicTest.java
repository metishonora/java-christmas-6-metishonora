package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.menu.Appetizer;
import christmas.model.menu.Dessert;
import christmas.model.menu.Drink;
import christmas.model.menu.Maindish;
import christmas.model.menu.Menu;
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
        assertThatThrownBy(() -> Logic.readSingleMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MENU_EXCEPTION);
    }

    @DisplayName("정상 메뉴")
    @MethodSource("menuSubclassProvider")
    @ParameterizedTest
    void normalMenu(String input, Class<? extends Menu> expectedClass) {
        Menu menu = Logic.readSingleMenu(input);
        assertThat(expectedClass).isEqualTo(menu.getClass());
    }
}
package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayReaderTest {
    private static final String DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    @DisplayName("빈 방문 날짜")
    @ValueSource(strings = {"", " "})
    @ParameterizedTest
    void emptyDate(String input) {
        assertThatThrownBy(() -> DayReader.readDay(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DATE_EXCEPTION);
    }

    @DisplayName("정수가 아닌 방문 날짜")
    @ValueSource(strings = {"abc", "0.1", "987654321987"})
    @ParameterizedTest
    void notIntegerDate(String input) {
        assertThatThrownBy(() -> DayReader.readDay(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DATE_EXCEPTION);
    }

    @DisplayName("범위를 벗어나는 방문 날짜")
    @ValueSource(strings = {"0", "32"})
    @ParameterizedTest
    void tooSmallOrLargeDate(String input) {
        assertThatThrownBy(() -> DayReader.readDay(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DATE_EXCEPTION);
    }

    @DisplayName("정상 날짜")
    @ValueSource(strings = {"1", "10", "31"})
    @ParameterizedTest
    void normalDate(String input) {
        assertThat(DayReader.readDay(input))
                .isEqualTo(Integer.parseInt(input));
    }
}
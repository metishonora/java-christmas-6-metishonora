package christmas.model.day;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.service.DayReader;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class December2023Test {
    private static Stream<Arguments> testDayAfterArgProvider() {
        return Stream.of(
                Arguments.of("1", "2", "true"),
                Arguments.of("15", "10", "false"),
                Arguments.of("29", "31", "true")
        );
    }

    @DisplayName("특별한 날 확인")
    @ValueSource(strings = {"3", "10", "17", "24", "25", "31"})
    @ParameterizedTest
    void testSpecialDay(String line) {
        Day day = DayReader.readDay(line);
        assertThat(day.isSpecialDay()).isEqualTo(true);
    }

    @DisplayName("주말 확인")
    @ValueSource(strings = {"1", "2", "8", "9", "29", "30"})
    @ParameterizedTest
    void testWeekend(String line) {
        Day day = DayReader.readDay(line);
        assertThat(day.isWeekend()).isEqualTo(true);
    }


    @DisplayName("주중 확인")
    @ValueSource(strings = {"3", "7", "11", "12", "20", "21", "25", "31"})
    @ParameterizedTest
    void testWeekday(String line) {
        Day day = DayReader.readDay(line);
        assertThat(day.isWeekend()).isEqualTo(false);
    }

    @DisplayName("특정 날 이후인지 확인")
    @MethodSource("testDayAfterArgProvider")
    @ParameterizedTest
    void testDayAfter(String line, Integer anotherDay, Boolean answer) {
        Day day = DayReader.readDay(line);
        assertThat(day.isDayAfter(new December2023(anotherDay))).isEqualTo(answer);
    }
}
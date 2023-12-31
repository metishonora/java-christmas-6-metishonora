package christmas.service;

import christmas.model.day.Day;
import christmas.model.day.December2023;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DayReader {
    private static final String DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public static Day readDay(String line) {
        try {
            return new December2023(Optional.ofNullable(line)
                    .map(Integer::parseInt)
                    .filter(i -> i >= December2023.START_OF_MONTH && i <= December2023.END_OF_MONTH)
                    .orElseThrow());
        } catch (NumberFormatException | NoSuchElementException e) {
            throw new IllegalArgumentException(DATE_EXCEPTION);
        }
    }
}

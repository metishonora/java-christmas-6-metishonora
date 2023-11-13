package christmas.service;

import christmas.model.day.Day;
import christmas.model.day.December2023;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DayReader {
    private static final String DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final Integer START_OF_MONTH = 1;
    private static final Integer END_OF_MONTH = 31;

    // TODO: 시작일, 종료일을 December2023에 위임
    // TODO: December2023 클래스 맵핑을 config에 위임
    public static Day readDay(String line) {
        try {
            return new December2023(Optional.ofNullable(line)
                    .map(Integer::parseInt)
                    .filter(i -> i >= START_OF_MONTH && i <= END_OF_MONTH)
                    .orElseThrow());
        } catch (NumberFormatException | NoSuchElementException e) {
            throw new IllegalArgumentException(DATE_EXCEPTION);
        }
    }
}

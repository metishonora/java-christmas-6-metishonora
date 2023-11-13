package christmas.service;

import java.util.NoSuchElementException;
import java.util.Optional;

public class DateReader {
    private static final String DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final Integer START_OF_MONTH = 1;
    private static final Integer END_OF_MONTH = 31;

    public static Integer readDate(String line) {
        try {
            return Optional.ofNullable(line)
                    .map(Integer::parseInt)
                    .filter(i -> i >= START_OF_MONTH && i <= END_OF_MONTH)
                    .orElseThrow();
        } catch (NumberFormatException | NoSuchElementException e) {
            throw new IllegalArgumentException(DATE_EXCEPTION);
        }
    }
}

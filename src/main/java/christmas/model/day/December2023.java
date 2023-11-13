package christmas.model.day;

import java.util.List;

public class December2023 implements Day {
    private static final Integer MONTH = 12;
    private static final List<Integer> WEEKEND = List.of(
            2, 3, 9, 10, 16, 17, 23, 24, 30, 31
    );
    private static final List<Integer> STARRED = List.of(
            3, 10, 17, 24, 25, 31
    );
    private final Integer day;

    public December2023(Integer day) {
        this.day = day;
    }

    // TODO: 일~목이면 평일 취급하기
    @Override
    public boolean isWeekend() {
        return WEEKEND.contains(day);
    }

    @Override
    public boolean isDayAfter(Integer another) {
        return day.compareTo(another) < 0;
    }

    // TODO: 별표 표시된 날 구현하기
    @Override
    public boolean isSpecialDay() {
        return STARRED.contains(day);
    }

    @Override
    public Integer getDay() {
        return day;
    }
}

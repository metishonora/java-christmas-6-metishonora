package christmas.model.day;

import java.util.List;

public record December2023(Integer day) implements Day {
    public static final Integer START_OF_MONTH = 1;
    public static final Integer END_OF_MONTH = 31;
    private static final Integer MONTH = 12;
    private static final List<Integer> WEEKEND = List.of(
            1, 2, 8, 9, 15, 16, 22, 23, 29, 30
    );
    private static final List<Integer> STARRED = List.of(
            3, 10, 17, 24, 25, 31
    );

    @Override
    public boolean isWeekend() {
        return WEEKEND.contains(day);
    }

    @Override
    public boolean isDayAfter(Day another) {
        return day.compareTo(another.day()) < 0;
    }

    @Override
    public boolean isSpecialDay() {
        return STARRED.contains(day);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o.getClass() != December2023.class) {
            return false;
        }
        December2023 obj = (December2023) o;
        return this.day.equals(obj.day);
    }
}

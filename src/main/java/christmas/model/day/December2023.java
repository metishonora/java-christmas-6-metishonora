package christmas.model.day;

import java.util.List;
import java.util.Objects;

public class December2023 implements Day {
    private static final Integer MONTH = 12;
    private static final List<Integer> WEEKEND = List.of(
            1, 2, 8, 9, 15, 16, 22, 23, 29, 30
    );
    private static final List<Integer> STARRED = List.of(
            3, 10, 17, 24, 25, 31
    );
    private final Integer day;

    public December2023(Integer day) {
        this.day = day;
    }

    @Override
    public boolean isWeekend() {
        return WEEKEND.contains(day);
    }

    @Override
    public boolean isDayAfter(Day another) {
        return day.compareTo(another.getDay()) < 0;
    }

    @Override
    public boolean isSpecialDay() {
        return STARRED.contains(day);
    }

    @Override
    public Integer getDay() {
        return day;
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

    @Override
    public int hashCode() {
        return Objects.hash(day);
    }
}

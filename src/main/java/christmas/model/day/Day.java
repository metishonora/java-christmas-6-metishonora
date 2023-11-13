package christmas.model.day;

public interface Day {
    boolean isWeekend();

    boolean isDayAfter(Day another);

    boolean isSpecialDay();

    Integer getDay();
}

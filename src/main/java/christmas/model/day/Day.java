package christmas.model.day;

public interface Day {
    boolean isWeekend();

    boolean isDayAfter(Integer another);

    boolean isSpecialDay();

    Integer getDay();
}

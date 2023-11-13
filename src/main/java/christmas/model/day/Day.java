package christmas.model.day;

public interface Day {
    boolean isWeekday();

    boolean isDayAfter(Integer another);

    boolean isSpecialDay();

    Integer getDay();
}

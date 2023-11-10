package christmas;

public class Logic {
    private static final String dateException = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public static int readDate(String line) {
        int date;
        try {
            date = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(dateException);
        }

        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(dateException);
        }
        return date;
    }
}

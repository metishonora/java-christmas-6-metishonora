package christmas;

public class Logic {
    public static int readDate(String line) {
        int date = Integer.parseInt(line);
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException();
        }
        return date;
    }
}

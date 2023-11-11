package christmas.model.menu;

public interface Menu {
    String getName();

    Integer getPrice();

    boolean contains(String line);
}

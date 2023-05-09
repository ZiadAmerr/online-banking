import java.time.LocalDateTime;

public class Item {
    private int item_id;
    private String name;
    private double price;
    private LocalDateTime date_time;
    private Account account;

    public int get_item_id() {
        return 0;
    }

    public String get_name() {
        return "";
    }

    public double get_price() {
        return 0.0;
    }

    public LocalDateTime get_date_time() {
        return null;
    }

    public Account get_account() {
        return null;
    }
}
package lesson25;

public class Order extends IdEntity{
    private long id;

    public Order(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }
}

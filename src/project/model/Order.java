package project.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order extends BaseModel implements Comparable<Order> {

    private Long id;
    private User user;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private Double moneyPaid;

    public Order(User user, Room room, Date dateFrom, Date dateTo, Double moneyPaid) {
        this.user = user;
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.moneyPaid = moneyPaid;
    }

    public Order(Long id, User user, Room room, Date dateFrom, Date dateTo, Double moneyPaid) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.moneyPaid = moneyPaid;
    }

    @Override
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Room getRoom() {
        return room;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy kk:00");

        return id + ", " +
                user.getId() + ", " +
                room.getId() + ", " +
                simpleDateFormat.format(dateFrom) + ", " +
                simpleDateFormat.format(dateTo) + ", " +
                moneyPaid;
    }

    @Override
    public int compareTo(Order order) {
        return this.dateFrom.before(order.getDateFrom()) ? 1 : -1;
    }
}

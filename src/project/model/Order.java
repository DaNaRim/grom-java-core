package project.model;

import project.DAO.DaoUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order extends BaseModel {

    private Long id;
    private final User user;
    private final Room room;
    private final Date dateFrom;
    private final Date dateTo;
    private final Double moneyPaid;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DaoUtil.DATE_FORMAT);

        return id + ", " +
                user.getId() + ", " +
                room.getId() + ", " +
                simpleDateFormat.format(dateFrom) + ", " +
                simpleDateFormat.format(dateTo) + ", " +
                moneyPaid;
    }

    @Override
    public int compareTo(BaseModel o) {
        Order order = (Order) o;
        return this.dateFrom.before(order.getDateFrom()) ? 1 : -1;
    }

}

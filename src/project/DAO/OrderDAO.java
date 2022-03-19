package project.DAO;

import project.exception.InternalServerException;
import project.exception.NotFoundException;
import project.model.Order;
import project.model.Room;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDAO extends DAO<Order> {

    private static final UserDAO userDAO = new UserDAO();
    private static final RoomDAO roomDAO = new RoomDAO();

    public OrderDAO() {
        super(DaoUtil.ORDER_DAO_PATH);
    }

    public Order findOrderByRoomAndUser(long roomId, long userId) throws InternalServerException, NotFoundException {
        for (Order order : getAll()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId) return order;
        }
        throw new NotFoundException("findOrderByRoomAndUser failed: Missing order");
    }

    public Room updateRoomDateAvailFrom(Long id) throws InternalServerException, NotFoundException {
        Room room = roomDAO.findById(id);

        Date dateAvailableFrom = room.getDateAvailableFrom();
        for (Order order : getAll()) {

            if (order.getDateTo().after(dateAvailableFrom)
                    && order.getDateFrom().before(dateAvailableFrom)) {
                room.setDateAvailableFrom(order.getDateTo());
                break;
            }
        }
        return roomDAO.update(room);
    }

    @Override
    protected Order map(String line) throws InternalServerException {
        try {
            String[] fields = line.split(", ");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DaoUtil.DATE_FORMAT);
            return new Order(
                    Long.parseLong(fields[0]),
                    userDAO.findById(Long.parseLong(fields[1])),
                    roomDAO.findById(Long.parseLong(fields[2])),
                    simpleDateFormat.parse(fields[3]),
                    simpleDateFormat.parse(fields[4]),
                    Double.parseDouble(fields[5]));
        } catch (Exception e) {
            //unreachable if all is okay
            throw new InternalServerException("OrderDAO map failed: " + e.getMessage());
        }
    }

}

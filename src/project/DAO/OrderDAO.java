package project.DAO;

import project.exception.BadRequestException;
import project.exception.InternalServerException;
import project.model.Order;
import project.model.Room;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDAO extends DAO<Order> {

    private static final UserDAO userDAO = new UserDAO();
    private static final RoomDAO roomDAO = new RoomDAO();

    public OrderDAO() {
        super("E:/Project/OrderDb.txt");
    }

    //TODO: remove bad request exceptions
    //TODO: add notFoundException

    @Override
    public Order map(String line) {
        try {
            String[] fields = line.split(", ");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy kk:00");
            return new Order(
                    Long.parseLong(fields[0]),
                    userDAO.findById(Long.parseLong(fields[1])),
                    roomDAO.findById(Long.parseLong(fields[2])),
                    simpleDateFormat.parse(fields[3]),
                    simpleDateFormat.parse(fields[4]),
                    Double.parseDouble(fields[5]));
        } catch (Exception e) {
            //unreachable if all is okay
            System.err.println("map failed: " + e.getMessage());
        }
        return null;
    }

    public Order findOrderByRoomAndUser(long roomId, long userId) throws InternalServerException, BadRequestException {
        for (Order order : getObjectsFromDAO()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId) return order;
        }
        throw new BadRequestException("findOrderByRoomAndUser failed: Missing order");
    }

    public void checkRoomForBusy(long roomId, Date dateFrom, Date dateTo)
            throws InternalServerException, BadRequestException {

        Date dateAvailableFrom = updateRoomDateAvailFrom(roomId).getDateAvailableFrom();

        if (dateAvailableFrom.after(dateFrom)) {
            throw new BadRequestException("checkRoomForBusy failed: the room is busy until " + dateAvailableFrom);
        }

        for (Order order : getObjectsFromDAO()) {
            Date busyTimeFrom = order.getDateFrom();
            Date busyTimeTo = order.getDateTo();

            boolean isOrderActual = order.getDateTo().after(new Date());
            boolean isDatesCrossOrderRange = !(busyTimeTo.before(dateFrom) || busyTimeFrom.after(dateTo));

            if (isOrderActual && isDatesCrossOrderRange) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy kk:00");

                throw new BadRequestException(String.format(
                        "checkRoomForBusy failed: the room is busy from %s to %s",
                        sdf.format(busyTimeFrom), sdf.format(busyTimeTo)));
            }
        }
    }

    public Order createOrder(long roomId, long userId, Date dateFrom, Date dateTo)
            throws InternalServerException, BadRequestException {
        return new Order(
                userDAO.findById(userId),
                roomDAO.findById(roomId),
                dateFrom,
                dateTo,
                roomDAO.findById(roomId).getPrice());
    }

    private Room updateRoomDateAvailFrom(Long id) throws InternalServerException, BadRequestException {
        Room room = roomDAO.findById(id);

        Date dateAvailableFrom = room.getDateAvailableFrom();
        for (Order order : getObjectsFromDAO()) {

            if (order.getDateTo().after(dateAvailableFrom)
                    && order.getDateFrom().before(dateAvailableFrom)) {
                room.setDateAvailableFrom(order.getDateTo());
            }
        }
        return roomDAO.updateObjectInDAO(room);
    }

}

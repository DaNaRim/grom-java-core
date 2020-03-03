package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.Order;
import Project.model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDAO extends DAOTools<Order> {
    private static UserDAO userDAO = new UserDAO();
    private static RoomDAO roomDAO = new RoomDAO();

    public OrderDAO() {
        super(FileLocations.getOrderFileLocation());
    }

    @Override
    public Order map(String line) throws BrokenFileException {
        try {
            String[] fields = line.split(", ");
            if (fields.length > 6)
                throw new BrokenFileException("to many elements");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy kk:00");
            return new Order(
                    Long.parseLong(fields[0]),
                    userDAO.findById(Long.parseLong(fields[1])),
                    roomDAO.findById(Long.parseLong(fields[2])),
                    simpleDateFormat.parse(fields[3]),
                    simpleDateFormat.parse(fields[4]),
                    Double.parseDouble(fields[5]));
        } catch (InternalServerException | ParseException | NumberFormatException | BadRequestException e) {
            throw new BrokenFileException(e.getMessage());
        }
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

        if (dateAvailableFrom.after(dateFrom))
            throw new BadRequestException("checkRoomForBusy failed: the room is busy until " + dateAvailableFrom);

        Date busyTimeRoomFrom;
        Date busyTimeRoomTo;
        for (Order order : getObjectsFromDAO()) {

            busyTimeRoomFrom = order.getDateFrom();
            busyTimeRoomTo = order.getDateTo();

            if (order.getDateTo().after(new Date()) &&
                    !(busyTimeRoomTo.before(dateFrom) || busyTimeRoomFrom.after(dateTo))) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy kk:00");

                throw new BadRequestException("checkRoomForBusy failed: the room is busy from " +
                        simpleDateFormat.format(busyTimeRoomFrom) + " to " + simpleDateFormat.format(busyTimeRoomTo));
            }
        }
    }

    public void isBooked(long roomId, long userId) throws InternalServerException, BadRequestException {
        for (Order order : getObjectsFromDAO()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId)
                throw new BadRequestException("isBooked failed: user: " + userId + " already booked room: "
                        + roomId + " in order: " + order.getId());
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

        Date busyTimeRoomTo;
        Date RoomDateAvailableFrom = room.getDateAvailableFrom();
        for (Order order : getObjectsFromDAO()) {
            if ((busyTimeRoomTo = order.getDateTo()).after(RoomDateAvailableFrom) &&
                    order.getDateFrom().before(RoomDateAvailableFrom))
                room.setDateAvailableFrom(busyTimeRoomTo);
        }
        return roomDAO.updateObjectInDAO(room);
    }
}
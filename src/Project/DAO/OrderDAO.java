package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDAO extends DAOTools<Order> {
    private static UserDAO userDAO = new UserDAO();
    private static RoomDAO roomDAO = new RoomDAO();

    public OrderDAO() {
        super(FileLocations.getOrderFileLocation());
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws InternalServerException, BadRequestException {
        addToFile(createOrder(roomId, userId, dateFrom, dateTo));
    }

    public void cancelReservation(long roomId, long userId) throws InternalServerException, BadRequestException {
        deleteFromFile(findOrderByRoomAndUser(roomId, userId).getId());
        roomDAO.findById(roomId).setDateAvailableFrom(new Date());
    }

    @Override
    public Order map(String line) throws BrokenFileException {
        try {
            String[] fields = line.split(", ");
            if (fields.length > 6)
                throw new BrokenFileException("broken line: to many elements");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy kk:00");
            return new Order(
                    Long.parseLong(fields[0]),
                    userDAO.findById(Long.parseLong(fields[1])),
                    roomDAO.findById(Long.parseLong(fields[2])),
                    simpleDateFormat.parse(fields[3]),
                    simpleDateFormat.parse(fields[4]),
                    Double.parseDouble(fields[5]));
        } catch (InternalServerException | ParseException | NumberFormatException | BadRequestException e) {
            throw new BrokenFileException("map failed: broken line");
        }
    }

    private Order createOrder(long roomId, long userId, Date dateFrom, Date dateTo)
            throws InternalServerException, BadRequestException {
        return new Order(
                userDAO.findById(userId),
                roomDAO.findById(roomId),
                dateFrom,
                dateTo,
                roomDAO.findById(roomId).getPrice());
    }

    private Order findOrderByRoomAndUser(long roomId, long userId) throws InternalServerException {
        for (Order order : getFromFile()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId) return order;
        }
        throw new InternalServerException("findOrderByRoomAndUser failed: Missing order");
    }
}
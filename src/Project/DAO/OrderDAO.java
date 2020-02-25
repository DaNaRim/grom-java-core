package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.model.Order;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

public class OrderDAO extends MainDAO<Order> {
    private UserDAO userDAO = new UserDAO();
    private RoomDAO roomDAO = new RoomDAO();

    public OrderDAO() {
        super(FileLocations.getOrderFileLocation());
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws BadRequestException, IOException, InternalServerException, NoAccessException {
        isBooked(roomId, userId);
        addToFile(createOrder(roomId, userId, dateFrom, dateTo));
        roomDAO.findById(roomId).setDateAvailableFrom(dateTo);
    }

    public void cancelReservation(long roomId, long userId)
            throws InternalServerException, IOException, NoAccessException {
        deleteFromFile(findOrderByRoomAndUser(roomId, userId).getId());
        roomDAO.findById(roomId).setDateAvailableFrom(new Date());
    }

    @Override
    public LinkedList<Order> getFromFile() throws BrokenFileException, IOException, NoAccessException {
        return super.getFromFile();
    }

    @Override
    public Order addToFile(Order order) throws IOException, BrokenFileException, NoAccessException {
        return super.addToFile(order);
    }

    @Override
    public void deleteFromFile(Long id) throws IOException, BrokenFileException, NoAccessException {
        super.deleteFromFile(id);
    }

    @Override
    public Order map(String line)
            throws IOException, InternalServerException, ParseException, NumberFormatException, NoAccessException {
        String[] fields = line.split(", ");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return new Order(
                Long.parseLong(fields[1]),
                userDAO.findById(Long.parseLong(fields[2])),
                roomDAO.findById(Long.parseLong(fields[3])),
                simpleDateFormat.parse(fields[4]),
                simpleDateFormat.parse(fields[5]),
                Double.parseDouble(fields[6]));
    }

    private Order createOrder(long roomId, long userId, Date dateFrom, Date dateTo)
            throws IOException, InternalServerException, NoAccessException {
        return new Order(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                userDAO.findById(userId),
                roomDAO.findById(roomId),
                dateFrom,
                dateTo,
                roomDAO.findById(roomId).getPrice());
    }

    private Order findOrderByRoomAndUser(long roomId, long userId)
            throws InternalServerException, IOException, NoAccessException {
        for (Order order : getFromFile()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId) return order;
        }
        throw new InternalServerException("findOrderByRoomAndUser failed: Missing order");
    }

    private void isBooked(long roomId, long userId)
            throws IOException, BrokenFileException, BadRequestException, NoAccessException {
        for (Order order : getFromFile()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId)
                throw new BadRequestException("isBooked failed: you already booked room: " + roomId +
                        " in order: " + order.getId());
        }
    }
}
package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.Order;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

public class OrderDAO extends MainDAO<Order> {
    private UserDAO userDAO = new UserDAO();
    private RoomDAO roomDAO = new RoomDAO();

    public OrderDAO() {
        super("testPath");
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws BadRequestException, IOException, InternalServerException {

        for (Order order : getFromFile()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId)
                throw new BadRequestException("You already booked room: " + roomId + " in order: " + order.getId());
        }

        addToFile(new Order(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                userDAO.findUserById(userId),
                roomDAO.findRoomById(roomId),
                dateFrom,
                dateTo,
                roomDAO.findRoomById(roomId).getPrice()));

        roomDAO.findRoomById(roomId).setDateAvailableFrom(dateTo);
    }

    public void cancelReservation(long roomId, long userId) throws InternalServerException, IOException {
        deleteFromFile(findOrderByRoomAndUser(roomId, userId).getId());
    }

    public Order findOrderByRoomAndUser(long roomId, long userId) throws InternalServerException, IOException {
        for (Order order : getFromFile()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId) return order;
        }
        throw new InternalServerException("Missing order");
    }

    @Override
    public LinkedList<Order> getFromFile() throws BrokenFileException, IOException {
        return super.getFromFile();
    }

    @Override
    public Order addToFile(Order order) throws IOException, BrokenFileException {
        return super.addToFile(order);
    }

    @Override
    public void deleteFromFile(Long id) throws IOException, BrokenFileException {
        super.deleteFromFile(id);
    }

    @Override
    public Order map(String line) throws Exception {
        String[] fields = line.split(",");

        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        return new Order(Long.parseLong(fields[1]),
                userDAO.findUserById(Long.parseLong(fields[2])),
                roomDAO.findRoomById(Long.parseLong(fields[3])),
                format.parse(fields[4]),
                format.parse(fields[5]),
                Double.parseDouble(fields[6]));
    }
}
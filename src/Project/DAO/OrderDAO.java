package Project.DAO;

import Project.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

public class OrderDAO extends MainDAO<Order> {
    private String path = "testPath";
    private UserDAO userDAO = new UserDAO();
    private RoomDAO roomDAO = new RoomDAO();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws Exception { //TODO Exception
        addToFile(new Order(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                userDAO.findUserById(userId),
                roomDAO.findRoomById(roomId),
                dateFrom,
                dateTo,
                roomDAO.findRoomById(roomId).getPrice()));
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        deleteFromFile(findOrderByRoomAndUser(roomId, userId).getId());
    }

    Order findOrderByRoomAndUser(long roomId, long userId) throws Exception { //TODO Exception
        for (Order order : getFromFile()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId) return order;
        }
        throw new Exception("Missing order with room: " + roomId + " and user: " + userId);
    }

    @Override
    LinkedList<Order> getFromFile() {
        return super.getFromFile();
    }

    @Override
    Order addToFile(Order order) {
        return super.addToFile(order);
    }

    @Override
    void deleteFromFile(Long id) {
        super.deleteFromFile(id);
    }

    @Override
    Order map(String line) throws Exception { //TODO Exception
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
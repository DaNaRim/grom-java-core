package Project.DAO;

import Project.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class OrderDAO extends MainDAO<Order> {
    private String path = "testPath";
    private UserDAO userDAO = new UserDAO();
    private RoomDAO roomDAO = new RoomDAO();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {
        //TODO bookRoom
    }

    public void cancelReservation(long roomId, long userId) {
        //TODO cancelReservation
    }

    @Override
    LinkedList<Order> getFromFile() {
        return super.getFromFile();
    }

    @Override
    void addToFile(Order order) {
        super.addToFile(order);
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
package Project.service;

import Project.DAO.OrderDAO;

import java.util.Date;

public class OrderService {
    OrderDAO orderDAO = new OrderDAO();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {
        //TODO check business logic

        orderDAO.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId) {
        //TODO check business logic

        orderDAO.cancelReservation(roomId, userId);
    }
}
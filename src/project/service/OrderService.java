package project.service;

import project.DAO.OrderDAO;
import project.DAO.RoomDAO;
import project.DAO.UserDAO;
import project.exception.BadRequestException;
import project.exception.InternalServerException;
import project.exception.NoAccessException;
import project.exception.NotLogInException;
import project.model.Order;

import java.util.Date;

public class OrderService {
    private static OrderDAO orderDAO = new OrderDAO();
    private static RoomDAO roomDAO = new RoomDAO();
    private static UserDAO userDAO = new UserDAO();
    private static UserService userService = new UserService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws InternalServerException, NoAccessException, BadRequestException, NotLogInException {
        validateRoomAndUser(roomId, userId);
        userService.checkUserForOperation(userId);
        validateOrder(roomId, dateFrom, dateTo);

        Order order = orderDAO.createOrder(roomId, userId, dateFrom, dateTo);
        orderDAO.addObjectToDAO(order);
    }

    public void cancelReservation(long roomId, long userId)
            throws InternalServerException, NoAccessException, BadRequestException, NotLogInException {
        validateRoomAndUser(roomId, userId);
        userService.checkUserForOperation(userId);
        validateCancellation(roomId, userId);

        Order order = orderDAO.findOrderByRoomAndUser(roomId, userId);
        orderDAO.deleteObjectFromDAO(order);
    }

    private void validateCancellation(long roomId, long userId)
            throws InternalServerException, BadRequestException {
        Date orderDateFrom = orderDAO.findOrderByRoomAndUser(roomId, userId).getDateFrom();

        if (orderDateFrom.before(new Date())) {
            throw new BadRequestException("validateCancellation failed: possible cancellation has expired");
        }
    }

    private void validateOrder(long roomId, Date dateFrom, Date dateTo)
            throws InternalServerException, BadRequestException {
        if (dateFrom == null || dateTo == null) {
            throw new BadRequestException("validateOrder failed: not all fields are filled correctly");
        }
        if (dateTo.before(dateFrom) || dateTo.equals(dateFrom) || dateFrom.before(new Date())) {
            throw new BadRequestException("validateOrder failed: date filled is incorrect");
        }
        orderDAO.checkRoomForBusy(roomId, dateFrom, dateTo);
    }

    private void validateRoomAndUser(long roomId, long userId) throws InternalServerException, BadRequestException {
        roomDAO.findById(roomId);
        userDAO.findById(userId);
    }
}
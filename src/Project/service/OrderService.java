package Project.service;

import Project.DAO.OrderDAO;
import Project.DAO.RoomDAO;
import Project.DAO.UserDAO;
import Project.exception.*;
import Project.model.Order;

import java.io.IOException;
import java.util.Date;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private RoomDAO roomDAO = new RoomDAO();
    private UserDAO userDAO = new UserDAO();
    private UserService userService = new UserService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws NotLogInException, IOException, InternalServerException, BadRequestException, NoAccessException {
        userService.checkLogin();

        checkOrder(roomId, userId, dateFrom, dateTo);
        isBooked(roomId, userId);
        checkRoomForBusy(roomId, dateFrom);

        orderDAO.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId)
            throws NotLogInException, IOException, InternalServerException, BadRequestException, NoAccessException {
        userService.checkLogin();
        checkRoomAndUser(roomId, userId);
        checkPossibleCancellation(roomId);
        orderDAO.cancelReservation(roomId, userId);
    }

    private void checkRoomForBusy(long roomId, Date dateFrom)
            throws BadRequestException, IOException, InternalServerException, NoAccessException {
        if (!roomDAO.findById(roomId).getDateAvailableFrom().equals(dateFrom) ||
                roomDAO.findById(roomId).getDateAvailableFrom().before(dateFrom))
            throw new BadRequestException("checkRoomForBusy failed: the room is busy");
    }

    private void checkPossibleCancellation(long roomId)
            throws IOException, InternalServerException, BadRequestException, NoAccessException {
        if (roomDAO.findById(roomId).getDateAvailableFrom().before(new Date()))
            throw new BadRequestException("checkPossibleCancellation failed: possible cancellation has expired");
    }

    private void isBooked(long roomId, long userId)
            throws IOException, BrokenFileException, BadRequestException, NoAccessException {
        for (Order order : orderDAO.getFromFile()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId)
                throw new BadRequestException("isBooked failed: you already booked room: " + roomId +
                        " in order: " + order.getId());
        }
    }

    private void checkOrder(long roomId, long userId, Date dateFrom, Date dateTo)
            throws BadRequestException, InternalServerException, IOException, NoAccessException {
        checkRoomAndUser(roomId, userId);
        if (dateFrom == null || dateTo == null)
            throw new BadRequestException("checkOrder failed: not all fields are filled");

        if (dateTo.before(dateFrom)) throw new BadRequestException("checkOrder failed: date is incorrect");
    }

    private void checkRoomAndUser(long roomId, long userId)
            throws InternalServerException, IOException, NoAccessException {
        roomDAO.findById(roomId);
        userDAO.findById(userId);
    }
}
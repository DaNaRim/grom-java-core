package Project.service;

import Project.DAO.OrderDAO;
import Project.DAO.RoomDAO;
import Project.DAO.UserDAO;
import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NotLogInException;
import Project.model.Order;

import java.util.Date;

public class OrderService {
    private static OrderDAO orderDAO = new OrderDAO();
    private static RoomDAO roomDAO = new RoomDAO();
    private static UserDAO userDAO = new UserDAO();
    private static UserService userService = new UserService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws InternalServerException, NotLogInException, BadRequestException {
        userService.checkLogin();

        checkOrder(roomId, userId, dateFrom, dateTo);
        isBooked(roomId, userId);
        checkRoomForBusy(roomId, dateFrom, dateTo);

        orderDAO.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId)
            throws InternalServerException, NotLogInException, BadRequestException {
        userService.checkLogin();

        checkRoomAndUser(roomId, userId);
        checkPossibleCancellation(roomId);

        orderDAO.cancelReservation(roomId, userId);
    }

    private void checkRoomForBusy(long roomId, Date dateFrom, Date dateTo)
            throws InternalServerException, BadRequestException {
        Date dateAvailableFrom = roomDAO.findById(roomId).getDateAvailableFrom();
        if (dateAvailableFrom.after(dateFrom))
            throw new BadRequestException("checkRoomForBusy failed: the room is busy until " + dateAvailableFrom);

        Date busyTimeRoomFrom;
        Date busyTimeRoomTo;
        for (Order order : orderDAO.getFromFile()) {
            busyTimeRoomFrom = order.getDateFrom();
            busyTimeRoomTo = order.getDateTo();
            if (order.getDateTo().after(new Date()) &&
                    !(busyTimeRoomTo.before(dateFrom) || busyTimeRoomFrom.after(dateTo))) {
                throw new BadRequestException("checkRoomForBusy failed: the room is busy from " +
                        busyTimeRoomFrom + " to " + busyTimeRoomTo);
            }
        }
    }

    private void checkPossibleCancellation(long roomId) throws InternalServerException, BadRequestException {
        if (roomDAO.findById(roomId).getDateAvailableFrom().before(new Date()))
            throw new BadRequestException("checkPossibleCancellation failed: possible cancellation has expired");
    }

    private void isBooked(long roomId, long userId) throws InternalServerException, BadRequestException {
        for (Order order : orderDAO.getFromFile()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId)
                throw new BadRequestException("isBooked failed: user: " + userId + " already booked room: "
                        + roomId + " in order: " + order.getId());
        }
    }

    private void checkOrder(long roomId, long userId, Date dateFrom, Date dateTo)
            throws InternalServerException, BadRequestException {
        checkRoomAndUser(roomId, userId);
        if (dateFrom == null || dateTo == null)
            throw new BadRequestException("checkOrder failed: not all fields are filled correctly");

        if (dateTo.before(dateFrom)) throw new BadRequestException("checkOrder failed: date is incorrect");
    }

    private void checkRoomAndUser(long roomId, long userId) throws InternalServerException, BadRequestException {
        roomDAO.findById(roomId);
        userDAO.findById(userId);
    }
}
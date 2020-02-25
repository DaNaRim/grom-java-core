package Project.service;

import Project.DAO.OrderDAO;
import Project.DAO.RoomDAO;
import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;

import java.io.IOException;
import java.util.Date;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private RoomDAO roomDAO = new RoomDAO();
    private UserService userService = new UserService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws NotLogInException, IOException, InternalServerException, BadRequestException, NoAccessException {
        userService.checkLogin();
        checkRoomForBusy(roomId, dateFrom);
        orderDAO.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId)
            throws NotLogInException, IOException, InternalServerException, BadRequestException, NoAccessException {
        userService.checkLogin();
        checkPossibleCancellation(roomId);
        orderDAO.cancelReservation(roomId, userId);
    }

    private void checkRoomForBusy(long roomId, Date dateFrom)
            throws BadRequestException, IOException, InternalServerException, NoAccessException {
        if (roomDAO.findById(roomId).getDateAvailableFrom().after(dateFrom))
            throw new BadRequestException("checkRoomForBusy failed: the room is busy");
    }

    private void checkPossibleCancellation(long roomId)
            throws IOException, InternalServerException, BadRequestException, NoAccessException {
        if (roomDAO.findById(roomId).getDateAvailableFrom().before(new Date()))
            throw new BadRequestException("checkPossibleCancellation failed: possible cancellation has expired");
    }
}
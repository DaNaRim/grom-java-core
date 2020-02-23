package Project.service;

import Project.DAO.OrderDAO;
import Project.DAO.RoomDAO;
import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NotLogInException;

import java.io.IOException;
import java.util.Date;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private RoomDAO roomDAO = new RoomDAO();
    private UserService userService = new UserService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws NotLogInException, IOException, InternalServerException, BadRequestException {

        userService.checkLogin();

        Date dateAvailableFrom = roomDAO.findRoomById(roomId).getDateAvailableFrom();
        if (dateAvailableFrom.after(dateFrom))
            throw new BadRequestException("The room is busy before time: " + dateAvailableFrom);

        orderDAO.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId)
            throws NotLogInException, IOException, InternalServerException, BadRequestException {

        userService.checkLogin();

        if (roomDAO.findRoomById(roomId).getDateAvailableFrom().before(new Date()))
            throw new BadRequestException("Possible cancellation has expired");

        orderDAO.cancelReservation(roomId, userId);
    }
}
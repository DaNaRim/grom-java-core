package Project.service;

import Project.DAO.OrderDAO;
import Project.DAO.RoomDAO;
import Project.exception.BadRequestException;
import Project.exception.NotRegisteredException;

import java.util.Date;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private RoomDAO roomDAO = new RoomDAO();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws Exception {
        if (UserService.getLoginUser() == null)
            throw new NotRegisteredException("You are not log in");

        if (roomDAO.findRoomById(roomId).getDateAvailableFrom().after(dateFrom))
            throw new BadRequestException("The room is busy at this time");

        orderDAO.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId) throws Exception{
        if (UserService.getLoginUser() == null)
            throw new NotRegisteredException("You are not log in");

        if (roomDAO.findRoomById(roomId).getDateAvailableFrom().before(new Date()))
            throw new BadRequestException("Possible cancellation has expired");

        orderDAO.cancelReservation(roomId, userId);
    }
}
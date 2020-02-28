package Project.service;

import Project.DAO.OrderDAO;
import Project.DAO.RoomDAO;
import Project.DAO.UserDAO;
import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.model.Order;
import Project.model.Room;

import java.util.Date;

public class OrderService {
    private static OrderDAO orderDAO = new OrderDAO();
    private static RoomDAO roomDAO = new RoomDAO();
    private static UserDAO userDAO = new UserDAO();
    private static UserService userService = new UserService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws InternalServerException, NoAccessException, BadRequestException {
        checkOrder(roomId, userId, dateFrom, dateTo);

        userService.checkUserForOperation(userId);

        isBooked(roomId, userId);
        checkRoomForBusy(roomId, dateFrom, dateTo);

        orderDAO.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId)
            throws InternalServerException, NoAccessException, BadRequestException {
        checkRoomAndUser(roomId, userId);

        userService.checkUserForOperation(userId);

        checkPossibleCancellation(roomId, userId);
        orderDAO.cancelReservation(roomId, userId);
    }

    private void checkRoomForBusy(long roomId, Date dateFrom, Date dateTo)
            throws InternalServerException, BadRequestException {
        Date dateAvailableFrom = updateRoomDateAvailFrom(roomId).getDateAvailableFrom();

        if (dateAvailableFrom.after(dateFrom))
            throw new BadRequestException("checkRoomForBusy failed: the room is busy until " + dateAvailableFrom);

        Date busyTimeRoomFrom;
        Date busyTimeRoomTo;
        for (Order order : orderDAO.getObjectsFromDAO()) {
            busyTimeRoomFrom = order.getDateFrom();
            busyTimeRoomTo = order.getDateTo();
            if (order.getDateTo().after(new Date()) &&
                    !(busyTimeRoomTo.before(dateFrom) || busyTimeRoomFrom.after(dateTo))) {
                throw new BadRequestException("checkRoomForBusy failed: the room is busy from " +
                        busyTimeRoomFrom + " to " + busyTimeRoomTo);
            }
        }
    }

    private void checkPossibleCancellation(long roomId, long userId)
            throws InternalServerException, BadRequestException {
        Date orderDateFrom = orderDAO.findOrderByRoomAndUser(roomId, userId).getDateFrom();

        if (orderDateFrom.before(new Date()))
            throw new BadRequestException("checkPossibleCancellation failed: possible cancellation has expired");
    }

    private void isBooked(long roomId, long userId) throws InternalServerException, BadRequestException {
        for (Order order : orderDAO.getObjectsFromDAO()) {
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

        if (dateTo.before(dateFrom) || dateTo.equals(dateFrom))
            throw new BadRequestException("checkOrder failed: date is incorrect");
    }

    private void checkRoomAndUser(long roomId, long userId) throws InternalServerException, BadRequestException {
        roomDAO.findById(roomId);
        userDAO.findById(userId);
    }

    private Room updateRoomDateAvailFrom(Long id) throws InternalServerException, BadRequestException {
        Room room = roomDAO.findById(id);

        Date busyTimeRoomTo;
        Date RoomDateAvailableFrom = room.getDateAvailableFrom();
        for (Order order : orderDAO.getObjectsFromDAO()) {
            if ((busyTimeRoomTo = order.getDateTo()).after(RoomDateAvailableFrom) &&
                    order.getDateFrom().before(RoomDateAvailableFrom))
                room.setDateAvailableFrom(busyTimeRoomTo);
        }
        roomDAO.updateObjectInDAO(id, room);
        return room;
    }
}
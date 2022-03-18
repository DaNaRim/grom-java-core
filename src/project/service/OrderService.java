package project.service;

import project.DAO.DaoUtil;
import project.DAO.OrderDAO;
import project.DAO.RoomDAO;
import project.DAO.UserDAO;
import project.exception.*;
import project.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderService {

    private static final UserService userService = new UserService();
    private static final OrderDAO orderDAO = new OrderDAO();
    private static final RoomDAO roomDAO = new RoomDAO();
    private static final UserDAO userDAO = new UserDAO();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws InternalServerException, NoAccessException, BadRequestException, NotLogInException,
            NotFoundException {

        validateRoomAndUser(roomId, userId);
        userService.checkUserForOperation(userId);
        validateBookRoom(roomId, dateFrom, dateTo);

        orderDAO.save(new Order(
                userDAO.findById(userId),
                roomDAO.findById(roomId),
                dateFrom,
                dateTo,
                roomDAO.findById(roomId).getPrice()));
    }

    public void cancelReservation(long roomId, long userId)
            throws InternalServerException, NoAccessException, BadRequestException, NotLogInException,
            NotFoundException {

        validateRoomAndUser(roomId, userId);
        userService.checkUserForOperation(userId);
        validateCancellation(roomId, userId);

        Order order = orderDAO.findOrderByRoomAndUser(roomId, userId);
        orderDAO.delete(order);
    }

    private void validateRoomAndUser(long roomId, long userId) throws InternalServerException, BadRequestException {
        if (roomDAO.isExists(roomId)) {
            throw new BadRequestException("validateRoomAndUser failed: missing room with id " + roomId);
        }
        if (userDAO.isExists(userId)) {
            throw new BadRequestException("validateRoomAndUser failed: missing user with id " + userId);
        }
    }

    private void validateBookRoom(long roomId, Date dateFrom, Date dateTo)
            throws InternalServerException, BadRequestException, NotFoundException {
        if (dateFrom == null || dateTo == null) {
            throw new BadRequestException("validateOrder failed: dates are not filled");
        }
        if (dateTo.before(dateFrom) || dateTo.equals(dateFrom) || dateFrom.before(new Date())) {
            throw new BadRequestException("validateOrder failed: dates filled incorrect");
        }
        checkRoomForBusy(roomId, dateFrom, dateTo);
    }

    private void checkRoomForBusy(long roomId, Date dateFrom, Date dateTo)
            throws InternalServerException, BadRequestException, NotFoundException {

        Date dateAvailableFrom = orderDAO.updateRoomDateAvailFrom(roomId).getDateAvailableFrom();

        if (dateAvailableFrom.after(dateFrom)) {
            throw new BadRequestException("checkRoomForBusy failed: the room is busy until " + dateAvailableFrom);
        }

        for (Order order : orderDAO.getAll()) {
            Date busyTimeFrom = order.getDateFrom();
            Date busyTimeTo = order.getDateTo();

            boolean isOrderActual = order.getDateTo().after(new Date());
            boolean isDatesCrossOrderRange = !(busyTimeTo.before(dateFrom) || busyTimeFrom.after(dateTo));

            if (isOrderActual && isDatesCrossOrderRange) {
                SimpleDateFormat sdf = new SimpleDateFormat(DaoUtil.DATE_FORMAT);

                throw new BadRequestException(String.format(
                        "checkRoomForBusy failed: the room is busy from %s to %s",
                        sdf.format(busyTimeFrom), sdf.format(busyTimeTo)));
            }
        }
    }

    private void validateCancellation(long roomId, long userId)
            throws InternalServerException, BadRequestException, NotFoundException {
        Date orderDateFrom = orderDAO.findOrderByRoomAndUser(roomId, userId).getDateFrom();

        if (orderDateFrom.before(new Date())) {
            throw new BadRequestException("validateCancellation failed: possible cancellation has expired");
        }
    }

}

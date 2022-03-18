package project.DAO;

import project.exception.InternalServerException;
import project.exception.NotFoundException;
import project.model.Filter;
import project.model.Room;

import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class RoomDAO extends DAO<Room> {

    private static final HotelDAO hotelDAO = new HotelDAO();

    public RoomDAO() {
        super(DaoUtil.ROOM_DAO_PATH);
    }

    public LinkedList<Room> findRooms(Filter filter) throws InternalServerException, NotFoundException {
        LinkedList<Room> rooms = findRoomsByFilter(filter);

        if (rooms.isEmpty()) {
            throw new NotFoundException("checkResultSize failed: there is no room with this filter parameters");
        }
        return rooms;
    }

    @Override
    public Room map(String line) {
        try {
            String[] fields = line.split(", ");

            return new Room(
                    Long.parseLong(fields[0]),
                    Integer.parseInt(fields[1]),
                    Double.parseDouble(fields[2]),
                    Boolean.parseBoolean(fields[3]),
                    Boolean.parseBoolean(fields[4]),
                    new SimpleDateFormat(DaoUtil.DATE_FORMAT).parse(fields[5]),
                    hotelDAO.findById(Long.parseLong(fields[6])));
        } catch (Exception e) {
            //unreachable if all is okay
            System.err.println("map failed: " + e.getMessage());
        }
        return null;
    }

    public boolean hasRooms(long hotelId) throws InternalServerException {
        for (Room room : getObjectsFromDAO()) {
            if (room.getHotel().getId() == hotelId) return true;
        }
        return false;
    }

    private LinkedList<Room> findRoomsByFilter(Filter filter) throws InternalServerException {
        LinkedList<Room> rooms = new LinkedList<>();
        for (Room room : getObjectsFromDAO()) {
            if (checkRoomByFilter(room, filter)) rooms.add(room);
        }
        return rooms;
    }

    private boolean checkRoomByFilter(Room room, Filter filter) {
        return (filter.getNumberOfGuests() == 0 || filter.getNumberOfGuests().equals(room.getNumberOfGuests()))
                && (filter.getPrice() == 0.0 || filter.getPrice().equals(room.getPrice()))
                && (filter.getBreakfastIncluded() == null
                        || filter.getBreakfastIncluded() == room.getBreakfastIncluded())
                && (filter.getPetsAllowed() == null || filter.getPetsAllowed() == room.getPetsAllowed())
                && (filter.getDateAvailableFrom() == null
                        || filter.getDateAvailableFrom().after(room.getDateAvailableFrom()))
                && (filter.getCountry() == null || filter.getCountry().equals(room.getHotel().getCountry()))
                && (filter.getCity() == null || filter.getCity().equals(room.getHotel().getCity()));
    }

}

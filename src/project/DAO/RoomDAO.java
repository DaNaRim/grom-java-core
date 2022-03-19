package project.DAO;

import project.exception.InternalServerException;
import project.exception.NotFoundException;
import project.model.Filter;
import project.model.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RoomDAO extends DAO<Room> {

    private static final HotelDAO hotelDAO = new HotelDAO();

    public RoomDAO() {
        super(DaoUtil.ROOM_DAO_PATH);
    }

    public ArrayList<Room> findRooms(Filter filter) throws InternalServerException, NotFoundException {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : getAll()) {
            if (checkRoom(room, filter)) rooms.add(room);
        }
        if (rooms.isEmpty()) {
            throw new NotFoundException("checkResultSize failed: there is no room with this filter parameters");
        }
        return rooms;
    }

    public boolean hasRooms(long hotelId) throws InternalServerException {
        for (Room room : getAll()) {
            if (room.getHotel().getId() == hotelId) return true;
        }
        return false;
    }

    @Override
    protected Room map(String line) throws InternalServerException {
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
            throw new InternalServerException("RoomDAO map failed: " + e.getMessage());
        }
    }

    private boolean checkRoom(Room room, Filter filter) {
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

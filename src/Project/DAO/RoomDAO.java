package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.Filter;
import Project.model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class RoomDAO extends DAOTools<Room> {
    private static HotelDAO hotelDAO = new HotelDAO();

    public RoomDAO() {
        super(FileLocations.getRoomFileLocation());
    }

    public LinkedList<Room> findRooms(Filter filter) throws InternalServerException, BadRequestException {
        LinkedList<Room> rooms = findRoomsByFilter(filter);
        checkResultSize(rooms);
        return rooms;
    }

    public Room addRoom(Room room) throws InternalServerException {
        return addObjectToDAO(room);
    }

    public void deleteRoom(long roomId) throws InternalServerException {
        deleteObjectFromDAO(roomId);
    }

    @Override
    public Room map(String line) throws BrokenFileException {
        try {
            String[] fields = line.split(", ");
            if (fields.length > 7)
                throw new BrokenFileException("to many elements");

            return new Room(
                    Long.parseLong(fields[0]),
                    Integer.parseInt(fields[1]),
                    Double.parseDouble(fields[2]),
                    Boolean.parseBoolean(fields[3]),
                    Boolean.parseBoolean(fields[4]),
                    new SimpleDateFormat("dd.MM.yyyy kk:00").parse(fields[5]),
                    hotelDAO.findById(Long.parseLong(fields[6])));
        } catch (ParseException | NumberFormatException | InternalServerException | BadRequestException e) {
            throw new BrokenFileException(e.getMessage());
        }
    }

    private void checkResultSize(LinkedList<Room> rooms) throws BadRequestException {
        if (rooms.size() == 0)
            throw new BadRequestException("checkResultSize failed: there is no room with this filter parameters");
    }

    private LinkedList<Room> findRoomsByFilter(Filter filter) throws InternalServerException {
        LinkedList<Room> rooms = new LinkedList<>();
        for (Room room : getObjectsFromDAO()) {
            if (checkRoomByFilter(room, filter)) rooms.add(room);
        }
        return rooms;
    }

    private boolean checkRoomByFilter(Room room, Filter filter) {
        return ((filter.getNumberOfGuests() == 0 || filter.getNumberOfGuests().equals(room.getNumberOfGuests())) &&
                (filter.getPrice() == 0.0 || filter.getPrice().equals(room.getPrice())) &&
                (filter.getBreakfastIncluded() == null ||
                        filter.getBreakfastIncluded() == room.getBreakfastIncluded()) &&
                (filter.getPetsAllowed() == null || filter.getPetsAllowed() == room.getPetsAllowed()) &&
                (filter.getDateAvailableFrom() == null ||
                        filter.getDateAvailableFrom().after(room.getDateAvailableFrom())) &&
                (filter.getCountry() == null || filter.getCountry().equals(room.getHotel().getCountry())) &&
                (filter.getCity() == null || filter.getCity().equals(room.getHotel().getCity())));
    }
}
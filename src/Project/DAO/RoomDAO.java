package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.Filter;
import Project.model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

public class RoomDAO extends DAOTools<Room> {
    private static HotelDAO hotelDAO = new HotelDAO();

    public RoomDAO() {
        super(FileLocations.getRoomFileLocation());
    }

    public ArrayList<Room> findRooms(Filter filter) throws InternalServerException, BadRequestException {
        ArrayList<Room> rooms = findRoomsByFilter(filter);
        checkResultSize(rooms);
        return rooms;
    }

    public Room addRoom(Room room) throws InternalServerException {
        room.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        return addToFile(room);
    }

    public void deleteRoom(long roomId) throws InternalServerException {
        findById(roomId);
        deleteFromFile(roomId);
    }

    @Override
    public Room map(String line) throws BrokenFileException {
        try {
            String[] fields = line.split(", ");
            if (fields.length > 7)
                throw new BrokenFileException("broken line");

            return new Room(
                    Long.parseLong(fields[0]),
                    Integer.parseInt(fields[1]),
                    Double.parseDouble(fields[2]),
                    Boolean.parseBoolean(fields[3]),
                    Boolean.parseBoolean(fields[4]),
                    new SimpleDateFormat("dd.MM.yyyy").parse(fields[5]),
                    hotelDAO.findById(Long.parseLong(fields[6])));
        } catch (ParseException | NumberFormatException | InternalServerException e) {
            throw new BrokenFileException("map failed: broken line");
        }
    }

    private void checkResultSize(ArrayList<Room> rooms) throws BadRequestException {
        if (rooms.size() == 0)
            throw new BadRequestException("checkResultSize failed: there is no room with this filter parameters");
    }

    private ArrayList<Room> findRoomsByFilter(Filter filter) throws InternalServerException {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : getFromFile()) {
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
                        filter.getDateAvailableFrom().equals(room.getDateAvailableFrom()) ||
                        filter.getDateAvailableFrom().after(room.getDateAvailableFrom())) &&
                (filter.getCountry() == null || filter.getCountry().equals(room.getHotel().getCountry())) &&
                (filter.getCity() == null || filter.getCity().equals(room.getHotel().getCity())));
    }
}
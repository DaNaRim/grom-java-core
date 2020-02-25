package Project.DAO;

import Project.exception.*;
import Project.model.Filter;
import Project.model.Room;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

public class RoomDAO extends DAOTools<Room> {
    private HotelDAO hotelDAO = new HotelDAO();

    public RoomDAO() {
        super(FileLocations.getRoomFileLocation());
    }

    public ArrayList<Room> findRooms(Filter filter)
            throws IOException, BrokenFileException, BadRequestException, NoAccessException {
        ArrayList<Room> rooms = findRoomsByFilter(filter);
        checkResultSize(rooms);
        return rooms;
    }

    public Room addRoom(Room room) throws IOException, BrokenFileException, NoAccessException {
        room.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        return addToFile(room);
    }

    public void deleteRoom(long roomId) throws IOException, InternalServerException, NoAccessException {
        findById(roomId);
        deleteFromFile(roomId);
    }

    @Override
    public Room findById(long id) throws IOException, InternalServerException, NoAccessException {
        return super.findById(id);
    }

    @Override
    public LinkedList<Room> getFromFile() throws IOException, BrokenFileException, NoAccessException {
        return super.getFromFile();
    }

    @Override
    public Room addToFile(Room room) throws IOException, BrokenFileException, NoAccessException {
        return super.addToFile(room);
    }

    @Override
    public void deleteFromFile(Long id) throws IOException, BrokenFileException, NoAccessException {
        super.deleteFromFile(id);
    }

    @Override
    public Room map(String line) throws IOException, InternalServerException, ParseException, NumberFormatException, NoAccessException {
        String[] fields = line.split(", ");
        if (fields.length > 7) throw new BrokenFileException("map failed: broken line");

        return new Room(
                Long.parseLong(fields[0]),
                Integer.parseInt(fields[1]),
                Double.parseDouble(fields[2]),
                Boolean.parseBoolean(fields[3]),
                Boolean.parseBoolean(fields[4]),
                new SimpleDateFormat("dd.MM.yyyy").parse(fields[5]),
                hotelDAO.findById(Long.parseLong(fields[6])));
    }

    private void checkResultSize(ArrayList<Room> rooms) throws BadRequestException {
        if (rooms.size() == 0)
            throw new BadRequestException("checkResultSize failed: there is no room with this filter parameters");
    }

    private ArrayList<Room> findRoomsByFilter(Filter filter)
            throws IOException, BrokenFileException, NoAccessException {
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
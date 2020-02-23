package Project.DAO;

import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.Filter;
import Project.model.Room;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class RoomDAO extends MainDAO<Room> {
    private String path = "testPath";
    private HotelDAO hotelDAO = new HotelDAO();

    public List findRooms(Filter filter) {
        //TODO findRooms

        return null;
    }

    public Room addRoom(Room room) throws IOException, BrokenFileException {
        return addToFile(new Room(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                room.getNumberOfGuests(),
                room.getPrice(),
                room.getBreakfastIncluded(),
                room.getPetsAllowed(),
                room.getDateAvailableFrom(),
                room.getHotel()));
    }

    public void deleteRoom(long roomId) throws IOException, BrokenFileException {
        deleteFromFile(roomId);
    }

    public Room findRoomById(long id) throws InternalServerException, IOException {
        for (Room room : getFromFile()) {
            if (room.getId() == id) return room;
        }
        throw new InternalServerException("Missing room with id: " + id);
    }

    @Override
    public LinkedList<Room> getFromFile() throws IOException, BrokenFileException {
        return super.getFromFile();
    }

    @Override
    public Room addToFile(Room room) throws IOException, BrokenFileException {
        return super.addToFile(room);
    }

    @Override
    public void deleteFromFile(Long id) throws IOException, BrokenFileException {
        super.deleteFromFile(id);
    }

    @Override
    public Room map(String line) throws Exception {
        String[] fields = line.split(",");

        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        return new Room(Long.parseLong(fields[1]),
                Integer.parseInt(fields[2]),
                Double.parseDouble(fields[3]),
                Boolean.parseBoolean(fields[4]),
                Boolean.parseBoolean(fields[5]),
                format.parse(fields[6]),
                hotelDAO.findHotelById(Long.parseLong(fields[7])));
    }
}
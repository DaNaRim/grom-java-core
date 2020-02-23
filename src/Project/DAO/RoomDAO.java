package Project.DAO;

import Project.model.Filter;
import Project.model.Room;

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

    public Room addRoom(Room room) {
        return addToFile(new Room(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                room.getNumberOfGuests(),
                room.getPrice(),
                room.getBreakfastIncluded(),
                room.getPetsAllowed(),
                room.getDateAvailableFrom(),
                room.getHotel()));
    }

    public void deleteRoom(long roomId) {
        deleteFromFile(roomId);
    }

    public Room findRoomById(long id) throws Exception { //TODO Exception
        for (Room room : getFromFile()) {
            if (room.getId() == id) return room;
        }
        throw new Exception("Missing room with id: " + id);
    }

    @Override
    LinkedList<Room> getFromFile() {
        return super.getFromFile();
    }

    @Override
    Room addToFile(Room room) {
        return super.addToFile(room);
    }

    @Override
    void deleteFromFile(Long id) {
        super.deleteFromFile(id);
    }

    @Override
    Room map(String line) throws Exception { //TODO Exception
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
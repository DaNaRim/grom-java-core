package Project.DAO;

import Project.model.Filter;
import Project.model.Hotel;
import Project.model.Room;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class RoomDAO extends MainDAO<Room> {
    private String path = "testPath";
    private HotelDAO hotelDAO = new HotelDAO();

    public List findRooms(Filter filter) {
        //TODO findRooms

        return null;
    }

    public Room addRoom(Room room) {
        //TODO addRoom

        return null;
    }

    public Room deleteRoom(long roomId) {
        //TODO deleteRoom

        return null;
    }

    public Room findRoomById(long id) throws Exception { //TODO Exception
        for (Room room : getFromFile()) {
            if (room.getId() == id) return room;
        }
        throw new Exception("Missing hotel with id: " + id);
    }

    @Override
    LinkedList<Room> getFromFile() {
        return super.getFromFile();
    }

    @Override
    void addToFile(Room room) {
        super.addToFile(room);
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
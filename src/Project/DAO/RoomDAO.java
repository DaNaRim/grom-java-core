package Project.DAO;

import Project.model.Filter;
import Project.model.Hotel;
import Project.model.Room;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class RoomDAO extends MainDAO<Room> {
    private String path = "testPath";

    public List findRooms(Filter filter) {
        //TODO findRooms

        return null;
    }

    public static Room findRoomById(long id) {
        //TODO findRoomById

        return null;
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
    Room map(String line) throws Exception {
        try {
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
                    HotelDAO.findHotelById(Long.parseLong(fields[7])));
        } catch (Exception e) {
            throw new Exception("broken line");
        }
    }
}
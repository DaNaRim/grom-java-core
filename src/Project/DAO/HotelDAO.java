package Project.DAO;

import Project.model.Hotel;

import java.util.LinkedList;

public class HotelDAO extends MainDAO<Hotel> {
    private String path = "testPath";

    public Hotel findHotelByName(String name) {
        //TODO findHotelByName

        return null;
    }

    public Hotel findHotelByCity(String city) {
        //TODO findHotelByCity

        return null;
    }

    public static Hotel findHotelById(long id) {
        //TODO findHotelById

        return null;
    }

    public Hotel addHotel(Hotel hotel) {
        //TODO addHotel

        return null;
    }

    public Hotel deleteHotel(long hotelId) {
        //TODO deleteHotel

        return null;
    }

    @Override
    LinkedList<Hotel> getFromFile() {
        return super.getFromFile();
    }

    @Override
    void addToFile(Hotel hotel) {
        super.addToFile(hotel);
    }

    @Override
    Hotel map(String line) throws Exception {
        String[] fields = line.split(",");

        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        return new Hotel(Long.parseLong(fields[1]), fields[2], fields[3], fields[4], fields[5]);
    }
}
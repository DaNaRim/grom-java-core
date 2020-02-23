package Project.DAO;

import Project.model.Hotel;

import java.util.LinkedList;
import java.util.UUID;

public class HotelDAO extends MainDAO<Hotel> {
    private String path = "testPath";

    public Hotel findHotelByName(String name) throws Exception { //TODO Exception
        for (Hotel hotel : getFromFile()) {
            if (hotel.getName().equals(name)) return hotel;
        }
        throw new Exception("There is no hotel with the name: " + name);
    }

    public Hotel findHotelByCity(String city) throws Exception { //TODO Exception
        for (Hotel hotel : getFromFile()) {
            if (hotel.getCity().equals(city)) return hotel;
        }
        throw new Exception("Missing hotel in city: " + city);
    }

    public Hotel findHotelById(long id) throws Exception { //TODO Exception
        for (Hotel hotel : getFromFile()) {
            if (hotel.getId() == id) return hotel;
        }
        throw new Exception("Missing hotel with id: " + id);
    }

    public Hotel addHotel(Hotel hotel) {
        return addToFile(new Hotel(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                hotel.getName(),
                hotel.getCountry(),
                hotel.getCity(),
                hotel.getStreet()));
    }

    public void deleteHotel(long hotelId) {
        deleteFromFile(hotelId);
    }

    @Override
    LinkedList<Hotel> getFromFile() {
        return super.getFromFile();
    }

    @Override
    Hotel addToFile(Hotel hotel) {
        return super.addToFile(hotel);
    }

    @Override
    void deleteFromFile(Long id) {
        super.deleteFromFile(id);
    }

    @Override
    Hotel map(String line) throws Exception { //TODO Exception
        String[] fields = line.split(",");

        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        return new Hotel(Long.parseLong(fields[1]), fields[2], fields[3], fields[4], fields[5]);
    }
}
package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.Hotel;

import java.util.LinkedList;
import java.util.UUID;

public class HotelDAO extends DAOTools<Hotel> {

    public HotelDAO() {
        super(FileLocations.getHotelFileLocation());
    }

    public LinkedList<Hotel> findHotelByName(String name) throws InternalServerException, BadRequestException {
        LinkedList<Hotel> hotels = new LinkedList<>();

        for (Hotel hotel : getFromFile()) {
            if (hotel.getName().equals(name)) hotels.add(hotel);
        }
        checkSize(hotels);
        return hotels;
    }

    public LinkedList<Hotel> findHotelByCity(String city) throws InternalServerException, BadRequestException {
        LinkedList<Hotel> hotels = new LinkedList<>();

        for (Hotel hotel : getFromFile()) {
            if (hotel.getCity().equals(city)) hotels.add(hotel);
        }
        checkSize(hotels);
        return hotels;
    }

    public Hotel addHotel(Hotel hotel) throws InternalServerException {
        hotel.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        return addToFile(hotel);
    }

    public void deleteHotel(long hotelId) throws InternalServerException {
        for (Hotel hotel : getFromFile()) {
            if (hotel.getId().equals(hotelId))
                deleteFromFile(hotel.getId());
        }
    }

    @Override
    public Hotel map(String line) throws BrokenFileException {
        try {
            String[] fields = line.split(", ");
            if (fields.length > 5)
                throw new BrokenFileException("broken line: to many elements");

            return new Hotel(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], fields[4]);
        } catch (NumberFormatException | BrokenFileException e) {
            throw new BrokenFileException("map failed: broken line");
        }
    }

    private void checkSize(LinkedList<Hotel> hotels) throws BadRequestException {
        if (hotels.size() == 0)
            throw new BadRequestException("findHotelByName failed: there is no hotels with parameters");
    }
}
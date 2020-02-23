package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.Hotel;

import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;

public class HotelDAO extends MainDAO<Hotel> {

    public HotelDAO() {
        super(FileLocations.getHotelFileLocation());
    }

    public Hotel findHotelByName(String name) throws BadRequestException, IOException, BrokenFileException {
        for (Hotel hotel : getFromFile()) {
            if (hotel.getName().equals(name)) return hotel;
        }
        throw new BadRequestException("There is no hotel with the name: " + name);
    }

    public Hotel findHotelByCity(String city) throws BadRequestException, IOException, BrokenFileException {
        for (Hotel hotel : getFromFile()) {
            if (hotel.getCity().equals(city)) return hotel;
        }
        throw new BadRequestException("Missing hotel in city: " + city);
    }

    public Hotel findHotelById(long id) throws InternalServerException, IOException {
        for (Hotel hotel : getFromFile()) {
            if (hotel.getId() == id) return hotel;
        }
        throw new InternalServerException("Missing hotel with id: " + id);
    }

    public Hotel addHotel(Hotel hotel) throws IOException, BrokenFileException, BadRequestException {
        for (Hotel hotel1 : getFromFile()) {
            if (hotel1.equals(hotel))
                throw new BadRequestException("The hotel is already exist: " + hotel1.getId());
        }

        return addToFile(new Hotel(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                hotel.getName(),
                hotel.getCountry(),
                hotel.getCity(),
                hotel.getStreet()));
    }

    public void deleteHotel(long hotelId) throws IOException, BrokenFileException, BadRequestException {
        for (Hotel hotel : getFromFile()) {
            if (hotel.getId().equals(hotelId)) deleteFromFile(hotel.getId());
        }
        throw new BadRequestException("There is no hotel with this id: " + hotelId);
    }

    @Override
    public LinkedList<Hotel> getFromFile() throws BrokenFileException, IOException {
        return super.getFromFile();
    }

    @Override
    public Hotel addToFile(Hotel hotel) throws IOException, BrokenFileException {
        return super.addToFile(hotel);
    }

    @Override
    public void deleteFromFile(Long id) throws IOException, BrokenFileException {
        super.deleteFromFile(id);
    }

    @Override
    public Hotel map(String line) throws NumberFormatException {
        String[] fields = line.split(",");

        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        return new Hotel(Long.parseLong(fields[1]), fields[2], fields[3], fields[4], fields[5]);
    }
}
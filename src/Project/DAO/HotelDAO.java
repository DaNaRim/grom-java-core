package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.model.Hotel;

import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;

public class HotelDAO extends MainDAO<Hotel> {

    public HotelDAO() {
        super(FileLocations.getHotelFileLocation());
    }

    public Hotel findHotelByName(String name)
            throws BadRequestException, IOException, BrokenFileException, NoAccessException {
        for (Hotel hotel : getFromFile()) {
            if (hotel.getName().equals(name)) return hotel;
        }
        throw new BadRequestException("findHotelByName failed: there is no hotel with the name: " + name);
    }

    public Hotel findHotelByCity(String city)
            throws BadRequestException, IOException, BrokenFileException, NoAccessException {
        for (Hotel hotel : getFromFile()) {
            if (hotel.getCity().equals(city)) return hotel;
        }
        throw new BadRequestException("findHotelByCity failed: there is no hotel in city: " + city);
    }

    public Hotel addHotel(Hotel hotel) throws IOException, BrokenFileException, BadRequestException, NoAccessException {
        isExist(hotel);
        hotel.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        return addToFile(hotel);
    }

    public void deleteHotel(long hotelId)
            throws IOException, BrokenFileException, BadRequestException, NoAccessException {
        for (Hotel hotel : getFromFile()) {
            if (hotel.getId().equals(hotelId)) {
                deleteFromFile(hotel.getId());
                return;
            }
        }
        throw new BadRequestException("deleteHotel failed: there is no hotel with this id: " + hotelId);
    }

    @Override
    public Hotel findById(long id) throws IOException, InternalServerException, NoAccessException {
        return super.findById(id);
    }

    @Override
    public LinkedList<Hotel> getFromFile() throws BrokenFileException, IOException, NoAccessException {
        return super.getFromFile();
    }

    @Override
    public Hotel addToFile(Hotel hotel) throws IOException, BrokenFileException, NoAccessException {
        return super.addToFile(hotel);
    }

    @Override
    public void deleteFromFile(Long id) throws IOException, BrokenFileException, NoAccessException {
        super.deleteFromFile(id);
    }

    @Override
    public Hotel map(String line) throws NumberFormatException, BrokenFileException {
        String[] fields = line.split(", ");
        if (fields.length > 5) throw new BrokenFileException("map failed: broken line");
        return new Hotel(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], fields[4]);
    }

    private void isExist(Hotel hotel) throws IOException, BrokenFileException, BadRequestException, NoAccessException {
        for (Hotel hotel1 : getFromFile()) {
            if (hotel1.equals(hotel))
                throw new BadRequestException("isExist failed: the hotel is already exist: " + hotel1.getId());
        }
    }
}
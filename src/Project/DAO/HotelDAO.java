package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.Hotel;

import java.util.LinkedList;

public class HotelDAO extends DAOTools<Hotel> {

    public HotelDAO() {
        super(FileLocations.getHotelFileLocation());
    }

    public LinkedList<Hotel> findHotelByName(String name) throws InternalServerException, BadRequestException {
        LinkedList<Hotel> resultHotels = new LinkedList<>();

        for (Hotel hotel : getObjectsFromDAO()) {
            if (hotel.getName().equals(name)) resultHotels.add(hotel);
        }
        checkSize(resultHotels);
        return resultHotels;
    }

    public LinkedList<Hotel> findHotelByCity(String city) throws InternalServerException, BadRequestException {
        LinkedList<Hotel> resultHotels = new LinkedList<>();

        for (Hotel hotel : getObjectsFromDAO()) {
            if (hotel.getCity().equals(city)) resultHotels.add(hotel);
        }
        checkSize(resultHotels);
        return resultHotels;
    }

    @Override
    public Hotel map(String line) throws BrokenFileException {
        try {
            String[] fields = line.split(", ");
            if (fields.length > 5)
                throw new BrokenFileException("to many elements");

            return new Hotel(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], fields[4]);
        } catch (NumberFormatException | BrokenFileException e) {
            throw new BrokenFileException(e.getMessage());
        }
    }

    public void doesTheHotelExist(Hotel hotel) throws InternalServerException, BadRequestException {
        for (Hotel hotel1 : getObjectsFromDAO()) {
            if (hotel1.equals(hotel))
                throw new BadRequestException("isExist failed: the hotel is already exist: " + hotel1.getId());
        }
    }

    private void checkSize(LinkedList<Hotel> hotels) throws BadRequestException {
        if (hotels.size() == 0)
            throw new BadRequestException("checkSize failed: there is no hotels with this parameters");
    }
}
package project.DAO;

import project.exception.InternalServerException;
import project.exception.NotFoundException;
import project.model.Hotel;

import java.util.LinkedList;

public class HotelDAO extends DAO<Hotel> {

    public HotelDAO() {
        super(DaoUtil.HOTEL_DAO_PATH);
    }

    //TODO: remove bad request exceptions
    //TODO: add notFoundException

    public LinkedList<Hotel> findHotelByName(String name) throws InternalServerException, NotFoundException {
        LinkedList<Hotel> resultHotels = new LinkedList<>();

        for (Hotel hotel : getObjectsFromDAO()) {
            if (hotel.getName().equals(name)) resultHotels.add(hotel);
        }

        if (resultHotels.isEmpty()) {
            throw new NotFoundException("findHotelByName failed: there is no hotels with this parameters");
        }
        return resultHotels;
    }

    public LinkedList<Hotel> findHotelByCity(String city) throws InternalServerException, NotFoundException {
        LinkedList<Hotel> resultHotels = new LinkedList<>();

        for (Hotel hotel : getObjectsFromDAO()) {
            if (hotel.getCity().equals(city)) resultHotels.add(hotel);
        }

        if (resultHotels.isEmpty()) {
            throw new NotFoundException("findHotelByCity failed: there is no hotels with this parameters");
        }
        return resultHotels;
    }

    public boolean isHotelExist(Hotel hotel) throws InternalServerException {
        for (Hotel hotel1 : getObjectsFromDAO()) {
            if (hotel1.equals(hotel)) return true;
        }
        return false;
    }

    @Override
    protected Hotel map(String line) {
        String[] fields = line.split(", ");

        return new Hotel(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], fields[4]);
    }

}

package project.DAO;

import project.exception.InternalServerException;
import project.exception.NotFoundException;
import project.model.Hotel;

import java.util.ArrayList;

public class HotelDAO extends DAO<Hotel> {

    public HotelDAO() {
        super(DaoUtil.HOTEL_DAO_PATH);
    }

    public ArrayList<Hotel> findHotelByName(String name) throws InternalServerException, NotFoundException {
        ArrayList<Hotel> resultHotels = new ArrayList<>();

        for (Hotel hotel : getAll()) {
            if (hotel.getName().equals(name)) resultHotels.add(hotel);
        }

        if (resultHotels.isEmpty()) {
            throw new NotFoundException("findHotelByName failed: there is no hotels with this name");
        }
        return resultHotels;
    }

    public ArrayList<Hotel> findHotelByCity(String city) throws InternalServerException, NotFoundException {
        ArrayList<Hotel> resultHotels = new ArrayList<>();

        for (Hotel hotel : getAll()) {
            if (hotel.getCity().equals(city)) resultHotels.add(hotel);
        }

        if (resultHotels.isEmpty()) {
            throw new NotFoundException("findHotelByCity failed: there is no hotels in this city");
        }
        return resultHotels;
    }

    public boolean isHotelExist(Hotel hotel) throws InternalServerException {
        for (Hotel hotel1 : getAll()) {
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

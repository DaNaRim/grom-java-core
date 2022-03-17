package project.model;

import project.DAO.DaoUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Room extends BaseModel implements Comparable<Room> {

    private Long id;
    private Integer numberOfGuests;
    private Double price;
    private Boolean breakfastIncluded;
    private Boolean petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;

    public Room(Integer numberOfGuests,
                Double price,
                Boolean breakfastIncluded,
                Boolean petsAllowed,
                Date dateAvailableFrom,
                Hotel hotel) {
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotel = hotel;
    }

    public Room(Long id,
                Integer numberOfGuests,
                Double price,
                Boolean breakfastIncluded,
                Boolean petsAllowed,
                Date dateAvailableFrom,
                Hotel hotel) {
        this.id = id;
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotel = hotel;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public Boolean getPetsAllowed() {
        return petsAllowed;
    }

    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    @Override
    public String toString() {
        return id + ", " +
                numberOfGuests + ", " +
                price + ", " +
                breakfastIncluded + ", " +
                petsAllowed + ", " +
                new SimpleDateFormat(DaoUtil.DATE_FORMAT).format(dateAvailableFrom) + ", " +
                hotel.getId();
    }

    @Override
    public int compareTo(Room room) {
        return this.dateAvailableFrom.after(room.getDateAvailableFrom()) ? 1 : -1;
    }
}

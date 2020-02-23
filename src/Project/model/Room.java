package Project.model;

import java.util.Date;

public class Room {
    private Long id;
    private Integer numberOfGuests;
    private Double price;
    private Boolean breakfastIncluded;
    private Boolean petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;

    public Room(Long id, Integer numberOfGuests, Double price, Boolean breakfastIncluded, Boolean petsAllowed, Date dateAvailableFrom, Hotel hotel) {
        this.id = id;
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ", " +
                numberOfGuests + ", " +
                price + ", " +
                breakfastIncluded + ", " +
                petsAllowed + ", " +
                dateAvailableFrom + ", " +
                hotel;
    }

    //TODO simplify
}
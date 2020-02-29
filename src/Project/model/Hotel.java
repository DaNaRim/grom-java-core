package Project.model;

import java.util.Objects;

public class Hotel implements MainModel, Comparable<Hotel> {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String street;

    public Hotel(String name, String country, String city, String street) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public Hotel(Long id, String name, String country, String city, String street) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return name.equals(hotel.name) &&
                country.equals(hotel.country) &&
                city.equals(hotel.city) &&
                street.equals(hotel.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, city, street);
    }

    @Override
    public String toString() {
        return id + ", " +
                name + ", " +
                country + ", " +
                city + ", " +
                street;
    }

    @Override
    public int compareTo(Hotel hotel) {
        if (this.country.compareTo(hotel.getCountry()) != 0)
            return this.country.compareTo(hotel.getCountry());

        if (this.city.compareTo(hotel.getCity()) != 0)
            return this.city.compareTo(hotel.getCity());

        if (this.street.compareTo(hotel.getStreet()) != 0)
            return this.street.compareTo(hotel.getStreet());

        return this.name.compareTo(hotel.name);
    }
}
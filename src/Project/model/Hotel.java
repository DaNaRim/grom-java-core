package Project.model;

public class Hotel extends MainModel {
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
        return super.getId();
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
    public String toString() {
        return id + ", " +
                name + ", " +
                country + ", " +
                city + ", " +
                street;
    }

    //TODO simplify
}
package lesson8.ads;

public class CarAd extends Ad{
    int yearOfManufacturing;
    String color;
    String ownerName;
    double weight;
    int horsePower;

    public CarAd(long id, int prise) {
        super(id, prise);
    }

    void confirmAd(){
        // someLogic
    }
}

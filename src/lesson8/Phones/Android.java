package lesson8.Phones;

public class Android extends Phone {

    String androidVersion;
    int screenSize;

    String secretDeviceCode;

    public Android(int price, double weight, String countryProduced, String androidVersion, int screenSize, String secretDeviceCode) {
        //this.price = price;
        //this.weight = weight;
        //this.countryProduced = countryProduced;
        super(price, weight, countryProduced);

        //this - reference to the current class object
        //this - reference to the parent class object


        this.androidVersion = androidVersion;
        this.screenSize = screenSize;
        this.secretDeviceCode = secretDeviceCode;
    }

    void installNewAndroidVersion() {
        System.out.println("installNewAndroidVersion invoked...");
    }
}

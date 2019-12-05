package lesson10.Homework;

import java.util.Date;

public class ElectronicsOrder extends Order {
    int guaranteeMonths;

    public ElectronicsOrder(String itemName, Date dateCreated, String shipFromCity, String shipToCity, int basePrice, Customer customerOwned, int guaranteeMonths) {
        super(itemName, dateCreated, shipFromCity, shipToCity, basePrice, customerOwned);
        this.guaranteeMonths = guaranteeMonths;
    }

    @Override
    void validateOrder() {
        String[] orderFromCities = {"Киев", "Одесса", "Днепр", "Харьков"};
        String[] orderToCities = {"Киев", "Одесса", "Днепр", "Харьков"};

        for (String sOFC : orderFromCities) {
            if (getShipFromCity() != sOFC) {
                //logic
            } else {
                //logic
            }
        }

        for (String sOTC : orderToCities) {
            if (getShipToCity() != sOTC) {
                //logic
            } else {
                //logic
            }
        }

        if (getBasePrice() < 100) {
            //logic
        }
        if (getCustomerOwned().getGender() == "Женский") {
            //logic
        }
    }

    @Override
    void calculatePrice() {
        int priceOfTheProduct;
        int shippingCost;
        int discount;
        if ((getShipToCity() == "Киев") || getShipToCity() == "Одесса")
            setTotalPrice(getBasePrice() + getBasePrice() * 0.1);
        else
            setTotalPrice(getBasePrice() + getBasePrice() * 0.15);

        if (getBasePrice() > 1000)
            setTotalPrice(getTotalPrice() * 0.95);

    }
}

package lesson10.Homework;

import java.util.Date;

public class FurnitureOrder extends Order {
    String furnitureCode;

    public FurnitureOrder(String itemName, Date dateCreated, String shipFromCity, String shipToCity, int basePrice, Customer customerOwned, String furnitureCode) {
        super(itemName, dateCreated, shipFromCity, shipToCity, basePrice, customerOwned);
        this.furnitureCode = furnitureCode;
    }

    @Override
    void validateOrder() {
        String[] orderFromCities = {"Киев", "Львов"};

        for (String sOFC : orderFromCities) {
            if (getShipFromCity() == sOFC && getBasePrice() >= 500 && getCustomerOwned().getName() != "Тест")
                setDateConfirmed(new Date());
        }
    }

    @Override
    void calculatePrice() {
        if (getBasePrice() < 5000) {
            setTotalPrice(getBasePrice() + getBasePrice() * 0.05);
        } else {
            setTotalPrice(getBasePrice() + getBasePrice() * 0.02);
        }
    }
}

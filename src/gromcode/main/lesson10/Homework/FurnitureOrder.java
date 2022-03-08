package gromcode.main.lesson10.Homework;

import java.util.Date;

public class FurnitureOrder extends Order {

    private String furnitureCode;

    public FurnitureOrder(String itemName,
                          Date dateCreated,
                          String shipFromCity,
                          String shipToCity,
                          int basePrice,
                          Customer customerOwned,
                          String furnitureCode) {
        super(itemName, dateCreated, shipFromCity, shipToCity, basePrice, customerOwned);
        this.furnitureCode = furnitureCode;
    }

    @Override
    public void validateOrder() {
        String[] orderFromCities = {"Киев", "Львов"};

        for (String ofc : orderFromCities) {
            if (getShipFromCity() == ofc
                    && getBasePrice() >= 500
                    && getCustomerOwned().getName() != "Тест") {
                setDateConfirmed(new Date());
                System.out.println("ValidateOrder id done");
                return;
            }
        }
        System.out.println("ValidateOrder is wrong");
    }

    @Override
    public void calculatePrice() {
        if (getBasePrice() < 5000) {
            setTotalPrice(getBasePrice() + getBasePrice() * 0.05);
            System.out.println("calculatePrice id done with shipping cost 5%");
        } else {
            setTotalPrice(getBasePrice() + getBasePrice() * 0.02);
            System.out.println("calculatePrice id done with shipping cost 2%");
        }
    }
}

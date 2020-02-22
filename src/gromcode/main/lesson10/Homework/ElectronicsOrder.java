package gromcode.main.lesson10.Homework;

import java.util.Date;

public class ElectronicsOrder extends Order {
    private int guaranteeMonths;

    public ElectronicsOrder(String itemName, Date dateCreated, String shipFromCity, String shipToCity, int basePrice, Customer customerOwned, int guaranteeMonths) {
        super(itemName, dateCreated, shipFromCity, shipToCity, basePrice, customerOwned);
        this.guaranteeMonths = guaranteeMonths;
    }

    @Override
    public void validateOrder() {
        String[] orderFromCities = {"Киев", "Одесса", "Днепр", "Харьков"};
        String[] orderToCities = {"Киев", "Одесса", "Днепр", "Харьков"};

        boolean checkOperation = true;
        for (String sOFC : orderFromCities) {
            if (getShipFromCity() == sOFC) {
                for (String sOTC : orderToCities) {
                    if (getShipToCity() == sOTC && getBasePrice() >= 100 && getCustomerOwned().getGender() == "Женский") {
                        setDateConfirmed(new Date());
                        checkOperation = false;
                        System.out.println("ValidateOrder is done");
                    }
                }
                if (checkOperation) System.out.println("ValidateOrder is wrong");
            }
        }
    }

    @Override
    public void calculatePrice() {
        if ((getShipToCity() == "Киев") || getShipToCity() == "Одесса") {
            setTotalPrice(getBasePrice() + getBasePrice() * 0.1);
            System.out.print("calculatePrice id done with shipping cost 10%");
        } else {
            setTotalPrice(getBasePrice() + getBasePrice() * 0.15);
            System.out.print("calculatePrice id done with shipping cost 15%");
        }

        if (getBasePrice() > 1000) {
            setTotalPrice(getTotalPrice() * 0.95);
            System.out.println(" and discount 95%");
        } else System.out.println();
    }
}

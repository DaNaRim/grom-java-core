package main.lesson12;

import java.util.Arrays;

public class Demo_L12_H {

    public static void main(String[] args) {
        System.out.println(Currency.USD);
        System.out.println(Arrays.toString(Currency.values()));

        String usd = "USD";

        Currency usdConverted = Currency.valueOf(usd);
        System.out.println(usdConverted);
    }
}

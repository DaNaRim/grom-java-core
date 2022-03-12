package main.lesson26.homework;

import java.util.ArrayList;

public class ArrayListTest {

    public ArrayList useList() {
        ArrayList<Order> testArrayList = new ArrayList<>();
        ArrayList<Order> testArrayList2 = new ArrayList<>();

        Order order1 = new Order(4444, 555, "EUR", "Phone", "asklh");
        Order order2 = new Order(123, 51235, "EUR", "IPhone", "asklh");
        testArrayList2.add(order2);

        testArrayList.add(order1);

        testArrayList.add(0, order2);

        testArrayList.remove(0);
        testArrayList.remove(order1);

        testArrayList.addAll(testArrayList2);

        testArrayList.subList(0, 1);

        testArrayList.set(0, order2);

        testArrayList.contains(order1);

        testArrayList.toArray();

        testArrayList.clear();

        for (int i = 0; i < 5; i++) {
            testArrayList.add(order1);
        }

        return testArrayList;
    }
}

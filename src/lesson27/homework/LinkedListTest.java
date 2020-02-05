package lesson27.homework;

import java.util.LinkedList;

public class LinkedListTest {

    public LinkedList useList() {
        LinkedList<Order> testLinkedList = new LinkedList<>();
        LinkedList<Order> testLinkedList2 = new LinkedList<>();

        Order order1 = new Order(4444, 555, "EUR", "Phone", "asklh");
        Order order2 = new Order(123, 51235, "EUR", "IPhone", "asklh");
        testLinkedList2.add(order2);

        testLinkedList.add(order1);

        testLinkedList.add(0, order2);

        testLinkedList.remove(0);

        testLinkedList.remove(order1);

        testLinkedList.addAll(testLinkedList2);

        testLinkedList.subList(0, 1);

        testLinkedList.set(0, order2);

        testLinkedList.contains(order1);

        testLinkedList.toArray();

        testLinkedList.clear();

        for (int i = 0; i < 5; i++) {
            testLinkedList.add(order1);
        }

        return testLinkedList;
    }
}

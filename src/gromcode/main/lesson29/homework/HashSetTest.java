package gromcode.main.lesson29.homework;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetTest {

    public Set<Order> useHashSet() {
        Set<Order> testSet = new HashSet<>();
        Set<Order> testSet2 = new HashSet<>();

        Order order1 = new Order(123444, 444, "USD", "Hammer", "aseg23wgw");
        Order order2 = new Order(123555, 555, "USD", "Hammer", "sdbh33322");

        testSet.add(order1);

        testSet.remove(order1);

        testSet.add(order1);
        testSet.add(order2);
        testSet2.add(order1);
        testSet.retainAll(testSet2);

        testSet.toArray();

        testSet.size();

        Iterator<Order> fileIterator = testSet.iterator();

        fileIterator.next();

        for (int i = 0; i < 5; i++) {
            Order superOrder = new Order(i * 123, i * 100, "USD", "Hammer" + i, "sdbh33322" + i * 5);
            testSet.add(superOrder);
        }
        return testSet;
    }
}


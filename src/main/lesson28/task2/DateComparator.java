package main.lesson28.task2;

import java.util.Comparator;

public class DateComparator implements Comparator<Capability> {

    @Override
    public int compare(Capability capability, Capability t1) {
        if (!capability.getDateCreated().equals(t1.getDateCreated())) {
            return capability.getDateCreated().compareTo(t1.getDateCreated());
        }
        return 0;
    }
}

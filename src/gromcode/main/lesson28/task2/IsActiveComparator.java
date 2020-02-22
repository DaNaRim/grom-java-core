package gromcode.main.lesson28.task2;

import java.util.Comparator;

public class IsActiveComparator implements Comparator<Capability> {

    @Override
    public int compare(Capability o1, Capability o2) {
        if (o1.isActive() && !o2.isActive())
            return -1;
        else if (o1.isActive() && o2.isActive() || !o1.isActive() && !o2.isActive())
            return 0;
        return 1;
    }
}
package main.lesson28.task2;

import java.util.Comparator;

public class FullComparator implements Comparator<Capability> {
    @Override
    public int compare(Capability capability, Capability t1) {
        if (compareValues(capability.getChannelName(), t1.getChannelName()) != 0) {
            return compareValues(capability.getChannelName(), t1.getChannelName());
        }
        if (compareValues(capability.getFingerPrint(), t1.getFingerPrint()) != 0) {
            return compareValues(capability.getFingerPrint(), t1.getFingerPrint());
        }
        return compareValues(capability.getDateCreated(), t1.getDateCreated());
    }

    private <T extends Comparable> int compareValues(T capability, T t1) {
        if (capability == null)
            return t1 == null ? 0 : 1;
        if (t1 == null)
            return -1;
        return capability.compareTo(t1);
    }
}
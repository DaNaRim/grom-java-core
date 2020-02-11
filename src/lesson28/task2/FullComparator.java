package lesson28.task2;

import java.util.Comparator;

public class FullComparator implements Comparator<Capability> {
    @Override
    public int compare(Capability capability, Capability t1) {

        if (capability.getChanelName() == null) return 1;
        if (t1.getChanelName() == null) return -1;

        if (!capability.getChanelName().equals(t1.getChanelName()))
            return capability.getChanelName().compareTo(t1.getChanelName());

        if (capability.getFingerPrint() == null) return 1;
        if (t1.getFingerPrint() == null) return -1;

        if (!capability.getFingerPrint().equals(t1.getFingerPrint()))
            return capability.getFingerPrint().compareTo(t1.getFingerPrint());

        if (capability.getDateCreated() == null) return 1;
        if (t1.getDateCreated() == null) return -1;

        if (!t1.getDateCreated().equals(capability.getDateCreated()))
            return t1.getDateCreated().compareTo(capability.getDateCreated());
        return 0;
    }
}
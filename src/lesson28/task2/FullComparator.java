package lesson28.task2;

import java.util.Comparator;

public class FullComparator implements Comparator<Capability> {

    @Override
    public int compare(Capability capability, Capability t1) {
        if (!capability.getChanelName().equals(t1.getChanelName()))
            return capability.getChanelName().compareTo(t1.getChanelName());

        if (!capability.getFingerPrint().equals(t1.getFingerPrint()))
            return capability.getFingerPrint().compareTo(t1.getFingerPrint());

        if (!capability.getDateCreated().equals(t1.getDateCreated()))
            return capability.getDateCreated().compareTo(t1.getDateCreated());

        return 0;
    }
}
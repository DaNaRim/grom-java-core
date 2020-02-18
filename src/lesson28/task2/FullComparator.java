package lesson28.task2;

import java.util.Comparator;

public class FullComparator implements Comparator<Capability> {
    @Override
    public int compare(Capability capability, Capability t1) {

        int arg = checkNull(capability.getChanelName(), t1.getChanelName());
        if (arg != 0) return arg;

        if (!capability.getChanelName().equals(t1.getChanelName()))
            return capability.getChanelName().compareTo(t1.getChanelName());
        
        arg = checkNull(capability.getFingerPrint(), t1.getFingerPrint());
        if (arg != 0) return arg;

        if (!capability.getFingerPrint().equals(t1.getFingerPrint()))
            return capability.getFingerPrint().compareTo(t1.getFingerPrint());

        arg = checkNull(capability.getDateCreated(), t1.getDateCreated());
        if (arg != 0) return arg;

        if (!t1.getDateCreated().equals(capability.getDateCreated()))
            return t1.getDateCreated().compareTo(capability.getDateCreated());
        return 0;
    }

    private <T, Y> int checkNull(T t, Y y) {
        if (t == null) return 1;
        if (y == null) return -1;
        return 0;
    }

    private <T, Y> boolean checkEquals(T t, Y y) {
        if (t.equals(y)) return false;
        return true;
    }


//    private int checkNull(Capability capability, Capability t1) {
//        if (capability.getChanelName() == null
//                || capability.getFingerPrint() == null
//                || capability.getDateCreated() == null)
//            return 1;
//        if (t1.getChanelName() == null
//                || t1.getFingerPrint() == null
//                || t1.getDateCreated() == null)
//            return -1;
//        return 0;
//    }
}

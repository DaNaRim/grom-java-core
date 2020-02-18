package lesson28;

import lesson28.task2.Capability;
import lesson28.task2.IsActiveComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class DemoComparator {
    public static void main(String[] args) {
        Capability capability2 = new Capability(1005, "test", "rrr", false, new Date());
        Capability capability4 = new Capability(900, "test", "rrr", false, new Date());
        Capability capability3 = new Capability(900, "test", "rrr", true, new Date());
        Capability capability1 = new Capability(1001, "test", "rrr", true, new Date());

        ArrayList<Capability> capabilities = new ArrayList<>();
        capabilities.add(capability1);
        capabilities.add(capability2);
        capabilities.add(capability3);
        capabilities.add(capability4);

        System.out.println(capabilities);

        capabilities.sort(new IsActiveComparator());

        System.out.println(capabilities);


        Comparator<Capability> comparator = new Comparator<Capability>() {
            @Override
            public int compare(Capability capability, Capability t1) {

                // если chanelName не равно - сравниваю по нему
                //если равно - перехожу к fingerprint
                //если fingerprint не равно - сравниваю по нему
                //если равно перехожу к dateCreated
                //если dateCreated не равно - сравниваю по нему
                //если равно - объекты равны

                if (!capability.getChanelName().equals(t1.getChanelName()))
                    return capability.getChanelName().compareTo(t1.getChanelName());

                if (!capability.getFingerPrint().equals(t1.getFingerPrint()))
                    return capability.getFingerPrint().compareTo(t1.getFingerPrint());

                if (!capability.getDateCreated().equals(t1.getDateCreated()))
                    return capability.getDateCreated().compareTo(t1.getDateCreated());
                return 0;
            }
        };
    }
}
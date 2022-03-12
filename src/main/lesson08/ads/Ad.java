package main.lesson08.ads;

import java.util.Date;

public class Ad extends BaseEntity {

    int prise;
    Date dateCreated;

    public Ad(long id, int prise) {
        super(id);
        this.prise = prise;
        this.dateCreated = new Date();
    }

    void publishAd() {
        //someLogic
    }
}

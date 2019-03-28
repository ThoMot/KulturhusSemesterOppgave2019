package org.openjfx.modul;

import java.util.Date;

public class Ticket {
    //data field
    int seatnumber; //evnt String
    String lokalnavn;
    Date date;
    double price;
    String phonenumber;

    //constructor
    public Ticket(int seatnumber, Date date, double price, String phonenumber, String lokalnavn) {
        //sjekke om setenummeret er tatt
        this.lokalnavn=lokalnavn;
        this.seatnumber=seatnumber;
        this.date=date;
        this.phonenumber=phonenumber;
        this.price=price;
    }

    //methods
    //delete
    //edit
    //get
    //set

}

package org.group38.model;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    ArrayList<Ticket> tickets;
    //Kontaktperson kontaktperson;
    String eventName;
    String type;//velge fra rullegardin
    String participants;
    //Facility facility;
    //String program?;
    Date date;
    double ticketPrice;
    int maxTickets;

    //constructor
    public Event(String eventName, String type, int maxTickets, String participants, Date date, double ticketPrice){
        tickets=new ArrayList<>(maxTickets);
        this.eventName=eventName;
        this.type=type;
        this.participants=participants;
        this.date=date;
        this.ticketPrice=ticketPrice;
        this.maxTickets=maxTickets;
    }
    //delete
    //edit
    //set
    //get 



}

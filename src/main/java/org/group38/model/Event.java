package org.group38.model;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    ArrayList<Ticket> tickets; //kjøpte og ikke kjøpte billetter
    //Kontaktperson kontaktperson;
    String eventName;
    //Type, hvordan skal vi definere denne?
    String participants;
    //Facility lokale;
    //program;
    Date date;
    double ticketPrice;
    int maxTickets;

    //constructor
    public Event(String eventName, int maxTickets, String participants, Date date, double ticketPrice){
        tickets=new ArrayList<>(maxTickets);
        this.eventName=eventName;
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

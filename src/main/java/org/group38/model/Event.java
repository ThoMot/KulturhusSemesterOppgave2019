package org.group38.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringJoiner;

public class Event {
    Ticket[][] tickets;
    //Kontaktperson kontaktperson;
    String eventName;
    String type;//velge fra rullegardin
    String participants;
    Facility facility;
    //String program?;
    Date date;
    double ticketPrice;
    int maxTickets;
    int rows;
    int columns;

    //constructor
    public Event(String eventName, String type, int maxTickets, String participants, Date date, double ticketPrice) {
        this.columns = facility.columns;
        this.rows = facility.rows;
        tickets = new Ticket[columns][rows];
        this.eventName = eventName;
        this.type = type;
        this.participants = participants;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.maxTickets = maxTickets;
    }

    public void buyTicket(int seatRow, int seatNumber, String phoneNumber) {
        if (tickets[seatRow][seatNumber].equals(null)) {
            tickets[seatRow][seatNumber] = new Ticket(seatRow, seatNumber,
                    this.date, this.ticketPrice, phoneNumber, this.facility.getFacilityName());
        } else {
            //feilmelding setet er tatt
        }
    }

    public String freeSeats() {
        StringJoiner s= new StringJoiner(", ");
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                if (tickets[i][j].equals(null)) {
                    s.add("setenummer:"+i+", rad:"+j+")");
                }
            }
        }
        return s.toString();
    }
}
    //delete
    //edit
    //set
    //get

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

    public String buyTicket(int seatRow, int seatNumber, String phoneNumber) {
        if (tickets[seatNumber][seatRow].equals(null)) {
            tickets[seatNumber][seatRow] = new Ticket(seatRow, seatNumber,
                    this.date, this.ticketPrice, phoneNumber, this.facility.getFacilityName());
            return "Billett er reservert på plass: "+seatNumber+","+seatRow;
        } else {
            return "Setet er opptatt";
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

    //må endre denne dersom det er flere billetter per telefonnummer
    //The ticket is removed from the matrix and there is no more references to the object. Therefore it is removed
    //the next time the garbage collector runs.
    public String deleteTicket(String phoneNumber) {
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                if (tickets[i][j].getPhonenumber().equals(phoneNumber)) {
                    tickets[i][j]=null;
                   return "Billetten er slettet";
                }
            }
        }
        return "Billetten eksisterer ikke";
    }
}
    //delete
    //edit
    //set
    //get

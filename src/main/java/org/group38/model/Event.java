package org.group38.model;

import java.util.ArrayList;
import java.util.Calendar;
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
    Calendar date;
    double ticketPrice;
    int maxTickets;
    int rows;
    int columns;

    //constructor
    public Event(Facility facility, String eventName, int maxTickets, String participants, Calendar date, double ticketPrice) {
        this.facility=facility;
        this.columns = facility.getColumns();
        this.rows = facility.getRows();
        tickets = new Ticket[columns][rows];
        this.eventName = eventName;
        this.type = facility.getType();
        this.participants = participants;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.maxTickets = maxTickets;
    }

    //Checks if the seat choosen is taken, and returns an errormessage if so, otherwise it creates a new ticket
    public String buyTicket(int seatRow, int seatNumber, String phoneNumber) {
        if(seatNumber>columns||seatNumber<0) return "Plassen du valgte er utenfor registeret, velg et setenummer mellom 0 og "+columns;
        if(seatRow>rows|| seatRow<0) return "Plassen du valgte er utenfor registeret, velg et radnummer mellom 0 og "+rows;
        if (tickets[seatNumber][seatRow]==(null)) {
            tickets[seatNumber][seatRow] = new Ticket(seatRow, seatNumber,
                    this.date, this.ticketPrice, phoneNumber, this.facility.getFacilityName());
            return "Billett er reservert på plass: "+seatNumber+","+seatRow;
        } else {
            return "Setet er opptatt";
        }
    }

    //Checks if there is any free seats in the matrix, and returns a String of available seats
    public String freeSeats() {
        StringJoiner s= new StringJoiner("\n ");
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                if (tickets[i][j]==null) {
                    s.add("Rad:"+i+", Setenummer:"+j);
                }
            }
        }
        return s.toString();
    }

    //må endre denne dersom det er flere billetter per telefonnummer
    //The ticket is removed from the matrix and there is no more references to the object. Therefore it is removed
    //the next time the garbage collector runs.
    public String deleteTicket(String phoneNumber) {
        int antallSlettet=0;
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                if (tickets[i][j]!=null) {
                    if (tickets[i][j].getPhonenumber().equals(phoneNumber)) {
                        tickets[i][j] = null;
                        antallSlettet++;
                    }
                }
            }
        }
        if(antallSlettet==0)return "Billetten eksisterer ikke";
        else return antallSlettet+" billetter er slettet på "+phoneNumber;
    }

    public Ticket[][] getTickets() {
        return tickets;
    }
}

    //edit må også endre alle billetter
    //set
    //get

//trenger slette billett på noe annet enn telefonnummer
//trenger en endre arrangement metode/settere som også endrer på billetten
//trenger metode for å endre contactPerson
//trenger metode for å endre lokalet
//slette arrangementer etter at datoen har vært?
//lage en metode som sjekker om et arrangement er fullt, ved hjelp av freeseats?
//endre utskrift av ledige seter til et passende format for visualisering
//lage tostring metode som skriver ut informasjon om arrangementet
//finn billett burde legge inn i en arraylist, som viser hvilke seter som er reservert på denne
//finnbillett med setenummer


package org.group38.model.Event;

import org.group38.model.ContactPerson.ContactPerson;
import org.group38.model.Facility;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Event {
    private ContactPerson contactPerson;
    private Facility facility;
    private ArrayList performers;
    private Ticket[][] tickets;
    private EventInfo eventInfo;


    private double ticketPrice;

    private int rows;
    private int columns;

    //constructor
    public Event(ContactPerson contactPerson, Facility facility, ArrayList performers, double ticketPrice, EventInfo eventInfo) {
        this.facility=facility;
        this.columns = facility.getColumns();
        this.rows = facility.getRows();
        tickets = new Ticket[rows][columns];
        this.performers = performers;
        this.ticketPrice = ticketPrice;
        this.contactPerson = contactPerson;
        this.eventInfo = eventInfo;
    }

    //Checks if the seat choosen is taken, and returns an errormessage if so, otherwise it creates a new ticket
    public String buyTicket(int seatRow, int seatNumber, String phoneNumber) {
        if(seatNumber>columns||seatNumber<0) return "Plassen du valgte er utenfor registeret, velg et setenummer mellom 0 og "+columns;
        if(seatRow>rows|| seatRow<0) return "Plassen du valgte er utenfor registeret, velg et radnummer mellom 0 og "+rows;
        if (tickets[seatRow][seatNumber]==(null)) {
            tickets[seatRow][seatNumber] = new Ticket(seatRow, seatNumber,
                    eventInfo.getDate(), this.ticketPrice, phoneNumber, this.facility.getFacilityName(), eventInfo.getEventName());
            return "Billett er reservert på plass: "+seatNumber+","+seatRow;
        } else {
            return "Setet er opptatt";
        }
    }

    //Checks if there is any free seats in the matrix, and returns a String of available seats
    public String freeSeats() {
        StringJoiner s= new StringJoiner("\n ");
        for (int i = 0; i < tickets.length; i++) {
            s.add("\n");
            for (int j = 0; j < tickets[i].length; j++) {
                if (tickets[i][j]==null) {
                    s.add("Rad:"+i+", Setenummer:"+j);
                }
            }
        }
        return s.toString();
    }

    //deletes all tickets on one phonenumber, by removing them from the matrix, removing all references
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

    //finds a ticket based on the phonenumber
    public Ticket findTicket(String phoneNumber){
        for(int i=0;i<tickets.length;i++){
            for(int j=0;j<tickets[i].length;j++){
                if(tickets[i][j]!=null){
                    if(tickets[i][j].equals(phoneNumber));
                    return tickets[i][j];
                }
            }
        }
        return null;
    }
}

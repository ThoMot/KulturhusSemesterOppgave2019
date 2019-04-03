//trenger slette billett på noe annet enn telefonnummer
//trenger en endre arrangement metode/settere som også endrer på billetten
//trenger metode for å endre kontaktperson
//trenger metode for å endre lokalet

package org.group38.model;

import java.util.Calendar;
import java.util.StringJoiner;

public class Event {
    private Ticket[][] tickets;
    private Kontaktperson kontaktperson;
    private String eventName;
    private String type;
    private String participants;
    private Facility facility;
    private String program;
    private Calendar date;
    private double ticketPrice;
    private int maxTickets;
    private int rows;
    private int columns;

    //constructor
    public Event(Kontaktperson kontaktperson, Facility facility, String eventName, int maxTickets, String participants, Calendar date, double ticketPrice) {
        this.facility=facility;
        this.columns = facility.getColumns();
        this.rows = facility.getRows();
        tickets = new Ticket[rows][columns];
        this.eventName = eventName;
        this.type = facility.getFacilityType();
        this.participants = participants;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.maxTickets = maxTickets;
        this.kontaktperson=kontaktperson;
    }

    //Checks if the seat choosen is taken, and returns an errormessage if so, otherwise it creates a new ticket
    public String buyTicket(int seatRow, int seatNumber, String phoneNumber) {
        if(seatNumber>columns||seatNumber<0) return "Plassen du valgte er utenfor registeret, velg et setenummer mellom 0 og "+columns;
        if(seatRow>rows|| seatRow<0) return "Plassen du valgte er utenfor registeret, velg et radnummer mellom 0 og "+rows;
        if (tickets[seatRow][seatNumber]==(null)) {
            tickets[seatRow][seatNumber] = new Ticket(seatRow, seatNumber,
                    this.date, this.ticketPrice, phoneNumber, this.facility.getFacilityName(), this.eventName);
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

    //The ticket is removed from the matrix and there is no more references to the object. Therefore it is removed
    //the next time the garbage collector runs.
    //deletes all tickets on one phonenumber
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

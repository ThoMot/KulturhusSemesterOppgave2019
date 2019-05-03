package org.group38.kulturhus.model.Event;

import org.group38.frameworks.Exeptions.SeatTakenException;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.facility.Facility;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class EventNumberedSeating extends Event implements Serializable {
    private ArrayList<Ticket> tickets;
    private int columns;
    private int rows;

    private EventNumberedSeating(){
        super();
    }

    //constructor
    public EventNumberedSeating(ContactPerson contactPerson, Facility facility, double ticketPrice, EventInfo eventInfo) {
        super(contactPerson, facility, eventInfo, ticketPrice);
        this.columns = facility.getColumns();
        this.rows = facility.getRows();
        tickets = new ArrayList<>();
    }
/**buyTicket method that adds a new ticket to the ArrayList of tickets
*this method also checks if the seat is already taken or if the event is full*/
    public void buyTicket(int seatRow, int seatNumber, String phoneNumber) throws SeatTakenException {
        if (tickets.size() < (rows * columns)) {
            if(seatRow<=0||seatNumber<=0||seatRow>=rows||seatNumber>=columns) throw new IndexOutOfBoundsException("Billetten du prøver å kjøpe er utenfor registeret");
            for (Ticket ticket : tickets) {

                if (ticket.getRow() == seatRow && ticket.getSeat() == seatNumber) {
                    throw new SeatTakenException("Setet er allerede opptatt");
                }
            }
            tickets.add(new Ticket(seatNumber, seatRow, phoneNumber, getEventInfo().getDate(), getEventInfo().getTime(), getEventId(), getTicketPrice(),getFacility().getFacilityName()));
        }
        else {
            throw new IndexOutOfBoundsException("Arrangementet er fullt");
        }
    }

    /**method that returns a string of all seats*/
    public String allSeats(){
        StringJoiner s= new StringJoiner("\t");
        for (Ticket ticket: tickets) {
            s.add("("+ticket.getRow()+","+ticket.getSeat()+")");
        }
        return s.toString();
    }

    /** the findTicket method is used for finding a ticket
     *based on the seat*/
    public Ticket findTicket(int rows, int columns){
        for(Ticket ticket:tickets){
            if(ticket.getSeat()==columns&&ticket.getRow()==rows) return ticket;
        }
        return null;
    }
    /** the availableSeats method returns is used to create a string
     * of all possible seats and add "opptatt" if the seat is taken
     * creating an overview of all seats for the user*/
    public String availableSeats(){
        StringJoiner sj = new StringJoiner(" \t");
        for(int i=1; i<rows+1;i++){
            sj.add("\n");
            for(int j=1;j<columns+1;j++){
                if(findTicket(i, j)==null){
                    sj.add("("+i+","+j+")");
                }
                else sj.add("opptatt");
            }
        }
        return sj.toString();
    }

    /**this toStrng method returns a string with lastname,
     * firstname, date, time and what type of event it is*/
    @Override
    public String toString() {
        return "Eventnavn: "+getEventInfo().getEventName()+"\n" +
                "Lokale: "+getFacility()+"\n" +
                "Dato og tid: "+getEventInfo().getDate()+" "+getEventInfo().getTime()+"\n" +
                "Type arrangement: setereservering";
    }
    /**these setter method is used for updating the event and also updating the
     *tickets that are already bought*/
    public void setDate(LocalDate date){
        super.getEventInfo().setDate(date);
        for(Ticket ticket: tickets){
            ticket.setDate(date);
        }
    }
    public void setTime(LocalTime time){
        super.getEventInfo().setTime(time);
        for(Ticket tickets: tickets){
            tickets.setTime(time);
        }
    }

    public void setTicketPrice(double price){
        super.setTicketPrice(price);
        for(Ticket tickets: tickets){
            tickets.setPrice(price);
        }
    }
    /** remaining setter and getter methods*/

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

}

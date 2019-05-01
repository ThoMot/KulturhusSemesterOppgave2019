package org.group38.kulturhus.model.Event;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.facility.Facility;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class EventFreeSeating extends Event implements Serializable {
    private ArrayList<Ticket> tickets;
    private int maxSeats;

    public EventFreeSeating(ContactPerson contactPerson, Facility facility, double ticketPrice, EventInfo eventInfo) {
        super(contactPerson, facility, eventInfo, ticketPrice);
        this.maxSeats=facility.getMaxAntSeats();
        tickets = new ArrayList();
    }


    /**First the method checks if the event is full with the maxSeats variable
    *and if it's not full, adds a ticket to the ArrayList of tickets.*/
    public void buyTicket(String phoneNumber){
        if(tickets.size()<maxSeats){
            tickets.add(new Ticket(getTicketPrice(), phoneNumber, getEventInfo().getDate(), getEventInfo().getTime(), getEventId(),getFacility().getFacilityName()));
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Arrangementet er fullt");
        }
    }
   /**these setter methods(setDate, setTicketPrice and setTime) makes sure to update both the eventInfo
   and also all the already bought tickets*/

    public void setDate(LocalDate date){
        super.getEventInfo().setDate(date);
        for(Ticket ticket : tickets){
            if(ticket!=null){
                ticket.setDate(date);
            }
        }
    }
    public void setTime(LocalTime time){
        super.getEventInfo().setTime(time);
        for(Ticket ticket : tickets){
            if(ticket!=null){
                ticket.setTime(time);
            }
        }
    }

    public void setTicketPrice(double price){
        super.setTicketPrice(price);
        for(Ticket ticket : tickets){
            if(ticket!=null){
                ticket.setPrice(price);
            }
        }
    }
/**this method returns a string containing the number of available tickets*/
    public String allSeats(){
        int numberofFreeSeats = 0;
        for(Ticket ticket: tickets){
            if(ticket==null){
                numberofFreeSeats++;
            }
        }
        return String.valueOf(numberofFreeSeats);
    }
    /**This tostring method returns the eventname, the facility, date and time and what kind of event it is*/
    @Override
    public String toString() {
        return "Eventnavn: " + getEventInfo().getEventName() + "\n" +
                "Lokale: " + getFacility() + "\n" +
                "Dato og tid: " + getEventInfo().getDate() + " " + getEventInfo().getTime() + "\n" +
                "Type arrangement: ingen setereservering";
    }

    /** getter and settermethods*/
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }


}

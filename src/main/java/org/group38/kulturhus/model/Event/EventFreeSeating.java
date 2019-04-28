package org.group38.kulturhus.model.Event;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.facility.Facility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class EventFreeSeating extends Event {
    private ArrayList<Ticket> tickets;
    private int maxSeats;

    public EventFreeSeating(ContactPerson contactPerson, Facility facility, double ticketPrice, EventInfo eventInfo) {
        super(contactPerson, facility, eventInfo, ticketPrice);
        this.maxSeats=facility.getMaxAntSeats();
        tickets = new ArrayList();
    }


    //denne må returnere en void og ha throws
    public void buyTicket(String phoneNumber){
        if(tickets.size()<maxSeats){
            tickets.add(new Ticket(getTicketPrice(), phoneNumber, getEventInfo().getDate(), getEventInfo().getTime(), getEventId()));
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Arrangementet er fullt");
        }
    }

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

    public String allSeats(){
        int numberofFreeSeats = 0;
        for(Ticket ticket: tickets){
            if(ticket==null){
                numberofFreeSeats++;
            }
        }
        return String.valueOf(numberofFreeSeats);
    }

    public boolean fullt(){
        if (Integer.parseInt(allSeats())==0) return true;
        else return false;
    }
    @Override
    public String toString() {
        return "Eventnavn: " + getEventInfo().getEventName() + "\n" +
                "Lokale: " + getFacility() + "\n" +
                "Dato og tid: " + getEventInfo().getDate() + " " + getEventInfo().getTime() + "\n" +
                "Type arrangement: ingen setereservering";
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public ArrayList<Ticket> boughtTickets(){
        ArrayList<Ticket> bought = new ArrayList();
        for (Ticket ticket: tickets) {
            if (tickets==null) {
            }
            else{
                bought.add(ticket);
            }
        }
        return bought;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public double getTicketPrice() {
        return super.getTicketPrice();
    }

}

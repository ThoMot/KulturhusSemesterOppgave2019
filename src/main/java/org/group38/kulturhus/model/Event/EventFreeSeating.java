package org.group38.kulturhus.model.Event;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.facility.Facility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventFreeSeating extends Event {
    private Ticket[] tickets;
    private int maxSeats;
    private final String type="EventFreeSeating";

    public EventFreeSeating(ContactPerson contactPerson, Facility facility, double ticketPrice, EventInfo eventInfo) {
        super(contactPerson, facility, eventInfo, ticketPrice);
        this.maxSeats=facility.getMaxAntSeats();
        tickets = new Ticket[maxSeats];
    }


    //denne må returnere en void og ha throws
    public String buyTicket(String phoneNumber){
        for(int i=0; i<tickets.length;i++){
            if(tickets[i]==null){
                tickets[i]=new Ticket(getTicketPrice(), phoneNumber, getEventInfo().getDate(), getEventInfo().getTime(), getEventId());
                return "Billett er reservert på telefonnummer: "+phoneNumber;
            }
        }
        return "Dette arrangementet er fullt";
    }

    //void m,ed throws
    public String deleteTicket(String phoneNumber){
        int numberDeleted=0;
        for(int i=0;i<maxSeats;i++){
            if(tickets[i].getPhonenumber()==phoneNumber){
                tickets[i]=null;
                numberDeleted++;
            }
        }
        if(numberDeleted==0) return "Det finnes ingen billetter registrert på dette telefonnummeret";
        else return numberDeleted+" billetter er slettet";
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

    //Checks if there is any free seats in the matrix, and returns a String of available seats
    public String freeSeats() {
        int numberofFreeSeats = 0;
        for (Ticket ticket : tickets) {
            if (tickets == null) {
                numberofFreeSeats++;
            }
        }
        return String.valueOf(numberofFreeSeats);
    }


    public boolean fullt(){
        if (Integer.parseInt(freeSeats())==0) return true;
        else return false;
    }
    public ArrayList<Ticket> FindTickets(String phoneNumber){
        ArrayList<Ticket> list = new ArrayList<>();
        for(Ticket ticket : tickets){
            if(ticket.getPhonenumber().equals(phoneNumber)){
                list.add(ticket);
            }
        }
        return list;
    }
    @Override
    public String toString() {
        return "Eventnavn: " + getEventInfo().getEventName() + "\n" +
                "Lokale: " + getFacility() + "\n" +
                "Dato og tid: " + getEventInfo().getDate() + " " + getEventInfo().getTime() + "\n" +
                "Type arrangement: ingen setereservering";
    }

    public Ticket[] getTickets() {
        return tickets;
    }
    public ArrayList<Ticket> boughtTickets(){
        ArrayList<Ticket> bought = new ArrayList();
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i]==null) {
            }
            else{
                bought.add(tickets[i]);
            }
        }
        return bought;
    }

    public String getType() {
        return type;
    }

    @Override
    public double getTicketPrice() {
        return super.getTicketPrice();
    }

}

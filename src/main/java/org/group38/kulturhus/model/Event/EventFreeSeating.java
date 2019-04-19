package org.group38.kulturhus.model.Event;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Facility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

public class EventFreeSeating extends Event {
    private Ticket[] tickets;
    private int maxSeats;

    public EventFreeSeating(ContactPerson contactPerson, Facility facility, String performers, double ticketPrice, EventInfo eventInfo) {
        super(contactPerson, facility, performers, eventInfo, ticketPrice);
        this.maxSeats=facility.getMaxAntSeats();
        tickets = new Ticket[maxSeats];
    }
    public String BuyTicket(String phoneNumber){
        for(int i=0; i<tickets.length;i++){
            if(tickets[i]==null){
                tickets[i]=new Ticket(getTicketPrice(), phoneNumber, getEventInfo().getDate(), getEventInfo().getTime());
                return "Billett er reservert på telefonnummer: "+phoneNumber;
            }
        }
        return "Dette arrangementet er fullt";
    }

    public String DeleteTicket(String phoneNumber){
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

    //Checks if there is any free seats in the matrix, and returns a String of available seats
    public int FreeSeats() {
        int numberofFreeSeats = 0;
        for (Ticket ticket : tickets) {
            if (tickets == null) {
                numberofFreeSeats++;
            }
        }
        return numberofFreeSeats;
    }

    public boolean fullt(){
        if (FreeSeats()==0) return true;
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
}

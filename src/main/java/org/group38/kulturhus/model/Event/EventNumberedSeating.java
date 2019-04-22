package org.group38.kulturhus.model.Event;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.facility.Facility;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class EventNumberedSeating extends org.group38.kulturhus.model.Event.Event implements Serializable {
    private org.group38.kulturhus.model.Event.Ticket[][] tickets;
    private int columns;
    private int rows;

    //constructor
    public EventNumberedSeating(ContactPerson contactPerson, Facility facility, String performers, double ticketPrice, org.group38.kulturhus.model.Event.EventInfo eventInfo) {
        super(contactPerson, facility, performers, eventInfo, ticketPrice);
        this.columns = facility.getColumns();
        this.rows = facility.getRows();
        tickets = new org.group38.kulturhus.model.Event.Ticket[rows][columns];
    }

    //Checks if the seat choosen is taken, and returns an errormessage if so, otherwise it creates a new ticket
    public void BuyTicket(int seatRow, int seatNumber, String phoneNumber) {
        if(seatNumber>columns||seatNumber<0) throw new IllegalArgumentException( "Plassen du valgte er utenfor registeret, velg et setenummer mellom 0 og "+columns);
        if(seatRow>rows|| seatRow<0) throw new IllegalArgumentException("Plassen du valgte er utenfor registeret, velg et radnummer mellom 0 og "+rows);
        if (tickets[seatRow][seatNumber]==null) {
            tickets[seatRow][seatNumber] = new org.group38.kulturhus.model.Event.Ticket(super.getTicketPrice(), phoneNumber, getEventInfo().getDate(), getEventInfo().getTime());
        }
        else throw new IllegalArgumentException("Setet er opptatt");
    }

    //Checks if there is any free seats in the matrix, and returns a String of available seats
    public String FreeSeats() {
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
    public void DeleteTicket(String phoneNumber) {
        int numberDeleted=0;
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                if (tickets[i][j]!=null) {
                    if (tickets[i][j].getPhonenumber().equals(phoneNumber)) {
                        tickets[i][j] = null;
                        numberDeleted++;
                    }
                }
            }
        }
        if(numberDeleted==0)throw new NoSuchElementException("Billetten(e) eksisterer ikke");
    }

    //Deletes tickets based on the seatrow and seatnumber
    public void DeleteTicket(int seatRow, int seatNumber){
        if(tickets[seatRow][seatNumber]!=null){
            tickets[seatRow][seatNumber]=null;
        }
        else throw new NoSuchElementException("Billetten finnes ikke");
    }

    //finds tickets based on the phonenumber and returns an arraylist of these tickets
    public ArrayList<org.group38.kulturhus.model.Event.Ticket> FindTickets(String phoneNumber){
        ArrayList<org.group38.kulturhus.model.Event.Ticket> list= new ArrayList<>();
        for(int i=0;i<tickets.length;i++){
            for(int j=0;j<tickets[i].length;j++){
                if(tickets[i][j]!=null){
                    if(tickets[i][j].equals(phoneNumber));
                    list.add(tickets[i][j]);
                }
            }
        }
        return list;
    }
    //Returns the ticket based on seatnumber and row
    public Ticket FindTicket(int seatRow, int seatNumber){
        if (tickets[seatRow][seatNumber]!=null){
            return tickets[seatRow][seatNumber];
        }
        else throw new NoSuchElementException("Det finnes ingen billett med gitt plassering");
    }

    //Checking if the Event is full, by going through the matrix searching for any available spots
    public boolean Full() {
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                if (tickets[i][j] == null) return false;
            }
        }
        return true;
    }
    //Edit the date of an Event, also updating all bought tickets and eventinfo
    public void setDate(LocalDate date){
        super.getEventInfo().setDate(date);
        for(org.group38.kulturhus.model.Event.Ticket[] tickets: tickets){
            for( org.group38.kulturhus.model.Event.Ticket ticket: tickets){
                if(ticket!=null){
                    ticket.setDate(date);
                }
            }
        }
    }
    public void setTime(LocalTime time){
        super.getEventInfo().setTime(time);
        for(org.group38.kulturhus.model.Event.Ticket[] tickets: tickets){
            for( org.group38.kulturhus.model.Event.Ticket ticket: tickets){
                if(ticket!=null){
                    ticket.setTime(time);
                }
            }
        }
    }

    //Updates the ticketprice bought in the Event, and also for all bought tickets
    public void setTicketPrice(double price){
        super.setTicketPrice(price);
        for(org.group38.kulturhus.model.Event.Ticket[] tickets: tickets){
            for( org.group38.kulturhus.model.Event.Ticket ticket: tickets){
                if(ticket!=null){
                    ticket.setPrice(price);
                }
            }
        }
    }
    public void EditSeat(int oldRow,int oldSeat, int seatRow, int seatNumber) {
        String phoneNumber = FindTicket(oldRow, oldSeat).getPhonenumber();
        try {
            DeleteTicket(oldRow, oldSeat);
            BuyTicket(seatRow, seatNumber, phoneNumber);
        } catch (NoSuchElementException e) {
            System.out.println(e);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
    public String printTicket(int seatRow, int seatNumber){
        org.group38.kulturhus.model.Event.Ticket t= FindTicket(seatRow, seatNumber);
        return t.toString()+"\nPlassering: ("+seatRow+","+seatNumber+")";
    }
    @Override
    public String toString() {
        return "Eventnavn: "+getEventInfo().getEventName()+"\n" +
                "Lokale: "+getFacility()+"\n" +
                "Dato og tid: "+getEventInfo().getDate()+" "+getEventInfo().getTime()+"\n" +
                "Type arrangement: setereservering";
    }

}

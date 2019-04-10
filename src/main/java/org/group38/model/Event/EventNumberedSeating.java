package org.group38.model.Event;

import org.group38.model.ContactPerson.ContactPerson;
import org.group38.model.Facility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class EventNumberedSeating extends Event {
    private Ticket[][] tickets;
    private int columns;
    private int rows;

    //constructor
    public EventNumberedSeating(ContactPerson contactPerson, Facility facility, ArrayList performers, double ticketPrice, EventInfo eventInfo) {
        super(contactPerson, facility, performers, eventInfo, ticketPrice);
        this.columns = facility.getColumns();
        this.rows = facility.getRows();
        tickets = new Ticket[rows][columns];
    }

    //Checks if the seat choosen is taken, and returns an errormessage if so, otherwise it creates a new ticket
    public void BuyTicket(int seatRow, int seatNumber, String phoneNumber) {
        if(seatNumber>columns||seatNumber<0) throw new IllegalArgumentException( "Plassen du valgte er utenfor registeret, velg et setenummer mellom 0 og "+columns);
        if(seatRow>rows|| seatRow<0) throw new IllegalArgumentException("Plassen du valgte er utenfor registeret, velg et radnummer mellom 0 og "+rows);
        if (tickets[seatRow][seatNumber]==null) {
            tickets[seatRow][seatNumber] = new Ticket(super.getEventInfo().getDate(), super.getTicketPrice(), phoneNumber,
                    super.getFacility().getFacilityName(), super.getEventInfo().getEventName());
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
    public ArrayList<Ticket> FindTickets(String phoneNumber){
        ArrayList<Ticket> list= new ArrayList<>();
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
        else return null;
    }

    //Checking if the event is full, by going through the matrix searching for any available spots
    public boolean Full() {
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                if (tickets[i][j] == null) return false;
            }
        }
        return true;
    }
    //Edit the date of an event, also updating all bought tickets and eventinfo
    public void setDate(Calendar date){
        super.getEventInfo().setDate(date);
        for(Ticket[] tickets: tickets){
            for( Ticket ticket: tickets){
                if(ticket!=null){
                    ticket.setDate(date);
                }
            }
        }
    }

    //Updates the ticketprice bought in the event, and also for all bought tickets
    public void setTicketPrice(double price){
        super.setTicketPrice(price);
        for(Ticket[] tickets: tickets){
            for( Ticket ticket: tickets){
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
        Ticket t= FindTicket(seatRow, seatNumber);
        return t.toString()+"\nPlassering: ("+seatRow+","+seatNumber+")";
    }
}

//er det mer som må kunne redigeres ved et arrangement enn billettpris og dato? skal vi feks kunne endre hvilket lokale det skjer i? da blir det mye som må endres
//Hvordan skal utskrift av ledige seter se ut?
//Trenger vi mer i tostringmetoden?
//lage metoder som samsvarer med at lokalet ikke har sitteplasser også

package org.group38.model.Event;

import org.group38.model.ContactPerson.ContactPerson;
import org.group38.model.Facility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringJoiner;

public class Event {
    private ContactPerson contactPerson; //skal denne også være på eventinfo?
    private Facility facility;
    private ArrayList performers; //skal denne også være på eventinfo?
    private Ticket[][] tickets;
    private EventInfo eventInfo;
    //private Ticket[] tickets2;
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
//    public Event(ContactPerson contactPerson, Facility facility, ArrayList performers, double ticketPrice, EventInfo eventInfo, int maxNumbSeats){
//
//    }

    //Checks if the seat choosen is taken, and returns an errormessage if so, otherwise it creates a new ticket
    public String buyTicket(int seatRow, int seatNumber, String phoneNumber) {
        if(seatNumber>columns||seatNumber<0) return "Plassen du valgte er utenfor registeret, velg et setenummer mellom 0 og "+columns;
        if(seatRow>rows|| seatRow<0) return "Plassen du valgte er utenfor registeret, velg et radnummer mellom 0 og "+rows;
        if (tickets[seatRow][seatNumber]==null) {
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

    //Deletes tickets based on the seatrow and seatnumber
    public boolean deleteTicket(int seatRow, int seatNumber){
        if(tickets[seatRow][seatNumber]!=null){
            tickets[seatRow][seatNumber]=null;
            return true;
        }
        return false;
    }

    //finds tickets based on the phonenumber and returns an arraylist of these tickets
    public ArrayList<Ticket> findTickets(String phoneNumber){
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
    public Ticket findTicket(int seatRow, int seatNumber){
        if (tickets[seatRow][seatNumber]!=null){
            return tickets[seatRow][seatNumber];
        }
        else return null;
    }

    //Checking if the event is full, by going through the matrix searching for any available spots
    public boolean full() {
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                if (tickets[i][j] == null) return false;
            }
        }
        return true;
    }

    //er det interessant å skrive ut noe mer info om et arrangement?
    public String toString(){
        return eventInfo.toString()+"\n"+
                "Billetpris: "+ticketPrice;
    }

    //Edit the date of an event, also updating all bought tickets and eventinfo
    public void setDate(Calendar date){
        eventInfo.setDate(date);
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
        this.ticketPrice=price;
        for(Ticket[] tickets: tickets){
            for( Ticket ticket: tickets){
                if(ticket!=null){
                    ticket.setPrice(price);
                }
            }
        }
    }
    public String editSeat(int oldRow,int oldSeat, int seatRow, int seatNumber){
        String phoneNumber= findTicket(oldRow, oldSeat).getPhonenumber();
        if(deleteTicket(oldRow, oldSeat)){
            return buyTicket(seatRow, seatNumber, phoneNumber);
        }
        else return "Det eksisterer ikke noe sete på det gamle plasseringen";
    }
    public double getTicketPrice(){
        return ticketPrice;
    }
    public EventInfo getEventInfo(){
        return eventInfo;
    }
}

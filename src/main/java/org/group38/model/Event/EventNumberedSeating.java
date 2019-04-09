package org.group38.model.Event;

import org.group38.model.ContactPerson.ContactPerson;
import org.group38.model.Facility;

import java.util.ArrayList;
import java.util.Calendar;
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
    public String BuyTicket(int seatRow, int seatNumber, String phoneNumber) {
        if(seatNumber>columns||seatNumber<0) return "Plassen du valgte er utenfor registeret, velg et setenummer mellom 0 og "+columns;
        if(seatRow>rows|| seatRow<0) return "Plassen du valgte er utenfor registeret, velg et radnummer mellom 0 og "+rows;
        if (tickets[seatRow][seatNumber]==null) {
            tickets[seatRow][seatNumber] = new Ticket(seatRow, seatNumber,
                    super.getEventInfo().getDate(), super.getTicketPrice(), phoneNumber, super.getFacility().getFacilityName(), super.getEventInfo().getEventName());
            return "Billett er reservert på plass: "+seatNumber+","+seatRow;
        } else {
            return "Setet er opptatt";
        }
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
    public String DeleteTicket(String phoneNumber) {
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
    public boolean DeleteTicket(int seatRow, int seatNumber){
        if(tickets[seatRow][seatNumber]!=null){
            tickets[seatRow][seatNumber]=null;
            return true;
        }
        return false;
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
    public String EditSeat(int oldRow,int oldSeat, int seatRow, int seatNumber){
        String phoneNumber= FindTicket(oldRow, oldSeat).getPhonenumber();
        if(DeleteTicket(oldRow, oldSeat)){
            return BuyTicket(seatRow, seatNumber, phoneNumber);
        }
        else return "Det eksisterer ikke noe sete på det gamle plasseringen";
    }
}

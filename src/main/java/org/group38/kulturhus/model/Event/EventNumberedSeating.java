package org.group38.kulturhus.model.Event;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.SaveLoad.CsvBase;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.model.Event.Ticket;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class EventNumberedSeating extends Event implements Serializable, CsvBase {
    private ArrayList<Ticket> tickets;
    private int columns;
    private int rows;

    //constructor
    public EventNumberedSeating(ContactPerson contactPerson, Facility facility, double ticketPrice, EventInfo eventInfo) {
        super(contactPerson, facility, eventInfo, ticketPrice);
        this.columns = facility.getColumns();
        this.rows = facility.getRows();
        tickets = new ArrayList<>();
    }

    public void buyTicket(int seatRow, int seatNumber, String phoneNumber) {
        if (tickets.size() < (rows * columns)) {
            if(seatRow<=0||seatNumber<=0||seatRow>=rows||seatNumber>=columns) throw new IndexOutOfBoundsException("Billetten du prøver å kjøpe er utenfor registeret");
            for (Ticket ticket : tickets) {

                if (ticket.getRow() == seatRow && ticket.getSeat() == seatNumber) {
                    throw new IllegalArgumentException("Setet er allerede opptatt");
                }
            }
            tickets.add(new Ticket(seatNumber, seatRow, phoneNumber, getEventInfo().getDate(), getEventInfo().getTime(), getEventId(), getTicketPrice()));
        }
        else {
            throw new IndexOutOfBoundsException("Arrangementet er fullt");
        }
    }

    public String allSeats(){
        StringJoiner s= new StringJoiner("\t");
        for (Ticket ticket: tickets) {
            s.add("("+ticket.getRow()+","+ticket.getSeat()+")");
        }
        return s.toString();
    }

    public ArrayList<Ticket> boughtTickets(){
        ArrayList<Ticket> bought = new ArrayList();
        for (Ticket ticket : tickets){
            bought.add(ticket);
        }
        return bought;
    }

    //Checking if the Event is full, by going through the matrix searching for any available spots
    public boolean Full() {
        if(tickets.size()==rows*columns) return true;
        else return false;
    }
    //Edit the date of an Event, also updating all bought tickets and eventinfo
    public void setDate(LocalDate date){
        //Sjekke input
        super.getEventInfo().setDate(date);
        for(Ticket ticket: tickets){
            ticket.setDate(date);
        }
    }
    public void setTime(LocalTime time){
        //sjekke for avvik
        super.getEventInfo().setTime(time);
        for(Ticket tickets: tickets){
            tickets.setTime(time);
        }
    }

    //Updates the ticketprice bought in the Event, and also for all bought tickets
    public void setTicketPrice(double price){
        //sjekk input her
        super.setTicketPrice(price);
        for(Ticket tickets: tickets){
            tickets.setPrice(price);
        }
    }

    //brukes for lagring
    public int getColumns() {
        return columns;
    }

    //brukes for lagring
    public int getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "Eventnavn: "+getEventInfo().getEventName()+"\n" +
                "Lokale: "+getFacility()+"\n" +
                "Dato og tid: "+getEventInfo().getDate()+" "+getEventInfo().getTime()+"\n" +
                "Type arrangement: setereservering";
    }
//DENNE ER LAGT INN I DAG
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
    public Ticket findTicket(int rows, int columns){
        for(Ticket ticket:tickets){
            if(ticket.getSeat()==columns&&ticket.getRow()==rows) return ticket;
        }
        return null;
    }
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
    //    @Override
//    public double getTicketPrice() {
//        return super.getTicketPrice();
//    }
}

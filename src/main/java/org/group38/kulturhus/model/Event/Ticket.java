package org.group38.kulturhus.model.Event;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import static org.group38.kulturhus.Utilities.Validate.isValidPhoneNr;

public class Ticket implements Serializable {
    //data fields
    private int seatRow;
    private int seatNumber;
    private String phoneNumber;
    private UUID eventId;
    private double ticketPrice;
    private String facility;
    private LocalDate date;
    private LocalTime time;


    /** Construcors to check if event are creating Ticket based on a numberedSeating event,
     * or a freeSeated event. The empty constructor is used to read and write to/from files*/
    private Ticket(){
    }

    /** Constructor for EventNumberedSeating */
    public Ticket(int seatNumber, int seatRow, String phoneNumber, LocalDate date, LocalTime time, UUID eventId, double ticketPrice, String facility) {
        this.seatNumber = seatNumber;
        this.seatRow = seatRow;
        this.phoneNumber = phoneNumber;
        this.date=date;
        this.time=time;
        this.eventId=eventId;
        this.ticketPrice = ticketPrice;
        this.facility = facility;
    }
    /** Constructor for EventFreeSeating */
    public Ticket(double ticketPrice, String phoneNumber, LocalDate date, LocalTime time, UUID eventId, String facility) {
        this.phoneNumber =phoneNumber;
        this.ticketPrice = ticketPrice;
        this.date=date;
        this.time=time;
        this.eventId=eventId;
        this.facility = facility;
    }

    /** Getter and setter for phoneNumber. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        if(!isValidPhoneNr(phoneNumber)) throw new IllegalArgumentException("Telefonnummeret må bestå av 8 tall");
        this.phoneNumber = phoneNumber;
    }

    /** Getter and setter for ticketPrice*/
    public double getTicketPrice(){
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /** Getter and setter for events date*/
    public void setDate(LocalDate date){
        this.date=date;
    }

    public LocalDate getDate() {
        return date;
    }

    /** Getter and setter for events time*/
    public void setTime(LocalTime time){
        this.time=time;
    }

    public LocalTime getTime() {
        return time;
    }

    /** Getter and setter for seatNumber and seatRow in NumberedSeating event */
    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getSeatRow() {
       return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    /** Gets the uniqe identifyer for the event to connect the event to the ticket */
    public UUID getEventId() { return eventId; }

    /** Gets the facility to connect the ticket to events facility */
    public String getFacility() { return facility; }

    @Override
    public String toString() {
        return "Ticket{" +
                "eventId=" + eventId +
                ", ticketPrice=" + ticketPrice +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", seatRow=" + seatRow +
                ", seatNumber=" + seatNumber +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}

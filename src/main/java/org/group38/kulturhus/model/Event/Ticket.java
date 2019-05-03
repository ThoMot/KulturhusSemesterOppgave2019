package org.group38.kulturhus.model.Event;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import static org.group38.frameworks.Validate.isValidPhoneNr;

public class Ticket implements Serializable {
    //data field
    private UUID eventId;
    private double price;
    private String phonenumber;
    private int row;
    private int seat;
    private String facility;
    LocalDate date;
    LocalTime time;

    /** Constructors to check if event are creating Ticket based on a numberedSeating event,
     * or a freeSeated event. The empty constryctor is used to read and write ti/from files*/
    private Ticket(){
    }

    /** Constructor for EventNumberedSeating */
    public Ticket(int seat, int row, String phoneNumber, LocalDate date, LocalTime time, UUID eventId, double price, String facility) {
        if(!isValidPhoneNr(phoneNumber)) throw new IllegalArgumentException("Telefonnummeret må bestå av 8 tall");
        this.seat = seat;
        this.phonenumber = phoneNumber;
        this.date=date;
        this.row=row;
        this.time=time;
        this.eventId=eventId;
        this.price =price;
        this.facility = facility;
    }

    /** Constructor for EventFreeSeating */
    public Ticket(double price, String phoneNumber, LocalDate date, LocalTime time, UUID eventId, String facility) {
        if(!isValidPhoneNr(phoneNumber)) throw new IllegalArgumentException("Telefonnummeret må bestå av 8 tall");
        this.phonenumber=phoneNumber;
        this.price=price;
        this.date=date;
        this.time=time;
        this.eventId=eventId;
        this.facility = facility;
    }

    /** Getter and setter method for phoneNumber. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phoneNumber) {
        if(!isValidPhoneNr(phoneNumber)) throw new IllegalArgumentException("Telefonnummeret må bestå av 8 tall");
        this.phonenumber = phonenumber;
    }

    /** Getter and setter method for ticket price. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public double getPrice(){
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /** Getter and setter method for tickets date. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public void setDate(LocalDate date){
        this.date=date;
    }

    public LocalDate getDate() {
        return date;
    }

    /** Getter and setter method for tickets time. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time){
        this.time=time;
    }
    
    /** Getter and setter method for EventNumberedSeatings seat row and seat number. */
    public Integer getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Integer getRow() {
       return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    /** Getter for eventId to connect the ticket to an event when writing to files.*/
    public UUID getEventId() { return eventId; }

    /** Getter to collect tickets facility name*/
    public String getFacility() { return facility; }

    @Override
    public String toString() {
        return "Ticket{" +
                "eventId=" + eventId +
                ", price=" + price +
                ", phonenumber='" + phonenumber + '\'' +
                ", row=" + row +
                ", seat=" + seat +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}

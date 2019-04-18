package org.group38.kulturhus.model.Event;

import java.time.LocalDate;
import java.util.Calendar;

public class Ticket {
    //data field
    private double price;
    private String facilityName;
    private LocalDate date;
    private String phonenumber;
    private String eventName;
    private EventNumberedSeating event;
    private int row;
    private int seat;

    //constructor
    public Ticket(EventNumberedSeating event, int seat, int row, String phoneNumber) {
        this.event = event;
        this.seat = seat;
        this.phonenumber = phoneNumber;
        event.BuyTicket(row,seat, phoneNumber);
    }
    public Ticket(LocalDate date, double price, String phonenumber, String facilityName, String eventName) {
        this.facilityName=facilityName;
        this.date=date;
        this.phonenumber=phonenumber;
        this.price=price;
        this.eventName=eventName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
    public double getPrice(){
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public void setPhonenumber(String phonenumber) {
        if(phonenumber.length()!=8){
            throw new IllegalArgumentException("Telefonnummeret inneholder ikke 8 symboler");
        }
        boolean numeric = phonenumber.matches("-?\\d+(\\.\\d+)?");
        if(!numeric){
            throw new IllegalArgumentException("Telefonnummer er på feil format, må inneholde bare tall");
        }
        else this.phonenumber = phonenumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    public String setSeatNumber(int seatNumber) {
//        this.seatNumber = seatNumber;
//        return "Setenummer er endret";
//    }
//
//    public String setSeatRow(int seatRow){
//        this.seatRow=seatRow;
//        return "Seterad er oppdatert";
//    }
//
//    public int getSeatRow() {
//        return seatRow;
//    }
//
//    public int getSeatNumber() {
//        return seatNumber;
//    }


    public String toString(){
        return event.getEventInfo().getDate() + " " + phonenumber + " " + seat + " "+ row + " "+ event.getEventInfo().getEventName() ;
    }

}

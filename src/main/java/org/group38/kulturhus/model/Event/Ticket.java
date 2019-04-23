package org.group38.kulturhus.model.Event;

import org.group38.kulturhus.model.SaveLoad.CsvBase;
import org.group38.kulturhus.model.facility.Facility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class Ticket implements CsvBase {
    //data field
    private AtomicInteger eventId;
    private double price;
    private String phonenumber;
    private int row;
    private int seat;
    LocalDate date;
    LocalTime time;

    //constructor
    public Ticket(int seat, int row, String phoneNumber, LocalDate date, LocalTime time, AtomicInteger eventId) {
        this.seat = seat;
        this.phonenumber = phoneNumber;
        this.date=date;
        this.row=row;
        this.time=time;
        this.eventId=eventId;
    }
    public Ticket(double price, String phonenumber, LocalDate date, LocalTime time, AtomicInteger eventId) {
        this.phonenumber=phonenumber;
        this.price=price;
        this.date=date;
        this.time=time;
        this.eventId=eventId;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
    public double getPrice(){
        return price;
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

    public void setDate(LocalDate date){
        this.date=date;
    }
    public void setTime(LocalTime time){
        this.time=time;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
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
    public Integer getSeat() {
        return seat;
    }

    public Integer getRow() {
       return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "price=" + price +
                ", phonenumber='" + phonenumber + '\'' +
                ", row=" + row +
                ", seat=" + seat +
                ", date=" + date +
                ", time=" + time +
                '}';
    }



    @Override
    public String toCSV() {

        StringBuilder sb = new StringBuilder();
        String delimitter = ",";
        sb.append((getPhonenumber()));
        sb.append(delimitter);
        sb.append(getDate());
        sb.append(delimitter);
        sb.append(getPrice());
        sb.append(delimitter);
        sb.append(getTime());

        return sb.toString();
    }
}

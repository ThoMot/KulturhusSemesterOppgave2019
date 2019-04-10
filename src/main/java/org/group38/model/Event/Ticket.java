package org.group38.model.Event;

import java.util.Calendar;

public class Ticket {
    //data field
    private double price;
    private String facilityName;
    private Calendar date;
    private String phonenumber;
    private String eventName;

    //constructor
    public Ticket(Calendar date, double price, String phonenumber, String facilityName, String eventName) {
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

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Calendar getDate() {
        return date;
    }

    public String setFacilityName(String facilityName) {
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

    public String setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
        return "Setenummer er endret";
    }

    public String setSeatRow(int seatRow){
        this.seatRow=seatRow;
        return "Seterad er oppdatert";
    }

    public int getSeatRow() {
        return seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }


    public String toString(){
        return "Billett til "+eventName+ " i "+facilityName+"\n"+
                date.getTime()+"\n"+
                "Koster "+price+"kr og er registrert på telefonnummer "+phonenumber;
    }
    public Calendar getDate(){
        return date;
    }
}

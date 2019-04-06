package org.group38.model.Event;

import java.util.Calendar;

public class Ticket {
    //data field
    private int seatRow;
    private int seatNumber;
    private double price;
    private String facilityName;
    private Calendar date;
    private String phonenumber;
    private String eventName;

    //constructor
    public Ticket(int seatRow, int seatNumber, Calendar date, double price, String phonenumber, String facilityName, String eventName) {
        this.seatRow=seatRow;
        this.seatNumber=seatNumber;
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

    public String setDate(Calendar date) {
        this.date = date;
        return "Dato er endret";
    }

    public String setFacilityName(String facilityName) {
        this.facilityName = facilityName;
        return "Lokalnavn er oppdatert";
    }

    public String setPhonenumber(String phonenumber) {
        if(phonenumber.length()!=8){
            return "Telefonnummeret inneholder ikke 8 symboler";
        }
        boolean numeric = phonenumber.matches("-?\\d+(\\.\\d+)?");
        if(!numeric){
            return "Telefonnummer er på feil format, må inneholde bare tall";
        }
        this.phonenumber = phonenumber;
        return "Telefonnummer er oppdatert";
    }

    public String setPrice(double price) {
        this.price = price;
        return "Prisen er oppdatert";
    }

    public String setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
        return "Setenummer er endret";
    }

    public String setSeatRow(int seatRow){
        this.seatRow=seatRow;
        return "Seterad er oppdatert";
    }

    public String toString(){
        return "Billett til "+eventName+ " i "+facilityName+"\n"+
                date.getTime()+"\n"+
                "På seterad: "+seatRow+" setenummer: "+seatNumber+"\n"
                +"Koster "+price+"kr og er registrert på telefonnummer "+phonenumber;
    }
    public Calendar getDate(){
        return date;
    }
}

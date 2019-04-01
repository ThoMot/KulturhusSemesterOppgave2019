package org.group38.model;

import java.util.Date;

public class Ticket {
    //data field
    int seatnumber; //evnt String
    String facilityName;
    Date date;
    double price;
    String phonenumber;

    //constructor
    public Ticket(int seatnumber, Date date, double price, String phonenumber, String facilityName) {
        //sjekke om setenummeret er tatt i arraylisten
        this.facilityName=facilityName;
        this.seatnumber=seatnumber;
        this.date=date;
        this.phonenumber=phonenumber;
        this.price=price;
    }
    //delete method, burde denne lages på en annen måte?
    public void delete(){
        this.delete();
    }
    //sjekke om vi trenger flere getmetoder etterhvert
    public String getPhonenumber() {
        return phonenumber;
    }
    public double getPrice(){
        return price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSeatnumber(int seatnumber) {
        this.seatnumber = seatnumber;
    }
    //A method for updateing the tickets. Checking if there is an input, and then updateing the given variable
    //burde vi ha feilmelding her dersom det gis feil input?
    public void editTicket(Date date, String facilityName, String phoneNumber, int seatNumber, double price){
        if(date!=null){
            this.setDate(date);
        }
        if(facilityName!="null"){
            this.setFacilityName(facilityName);
        }
        if(phoneNumber!="null"){
            this.setPhonenumber(phoneNumber);
        }
        if(seatNumber!=0){
            this.setSeatnumber(seatNumber);
        }
        if(price!=0){
            setPrice(price);
        }
    }
}

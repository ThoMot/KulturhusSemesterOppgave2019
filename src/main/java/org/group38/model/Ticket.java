package org.group38.model;

import java.util.Date;

public class Ticket {
    //data field
    int seatRow;
    int seatNumber;
    String facilityName;
    Date date;
    double price;
    String phonenumber;

    //constructor
    public Ticket(int seatRow, int seatNumber, Date date, double price, String phonenumber, String facilityName) {
        this.seatRow=seatRow;
        this.facilityName=facilityName;
        this.seatNumber=seatNumber;
        this.date=date;
        this.phonenumber=phonenumber;
        this.price=price;
    }
    //slette objekter??, holder det å bare slette det fra arraylisten i arrangementer feks
    //sjekke om vi trenger flere getmetoder etterhvert
    public String getPhonenumber() {
        return phonenumber;
    }
    public double getPrice(){
        return price;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    private void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    private void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    private void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
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
            this.setSeatNumber(seatNumber);
        }
        if(price!=0){
            setPrice(price);
        }
    }
}

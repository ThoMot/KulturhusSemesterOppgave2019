package org.group38.model;

import java.util.Calendar;

public class Ticket {
    //data field
    private int seatRow;
    private int seatNumber;
    private String facilityName;
    private Calendar date;
    private double price;
    private String phonenumber;

    //constructor
    public Ticket(int seatRow, int seatNumber, Calendar date, double price, String phonenumber, String facilityName) {
        this.seatRow=seatRow;
        this.seatNumber=seatNumber;
        this.facilityName=facilityName;
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

    //må legge inn feilmelding ved feil input i alle setmetoder
    private void setDate(Calendar date) { this.date = date; }

    private void setFacilityName(String facilityName) { this.facilityName = facilityName; }

    private void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }

    private void setPrice(double price) { this.price = price; }

    private void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }

    private void setSeatRow(int seatRow){ this.seatRow=seatRow; }
    //A method for updateing the tickets. Checking if there is an input, and then updateing the given variable
    //her trengs det ikke å kalle på setmetode, kan bruke direkte tilgang til variabelen, men da må man sjekke
    public String editTicket(Calendar date, String facilityName, String phoneNumber, int seatNumber, int seatRow, double price){
        String s="";
        if(date!=null){
            this.setDate(date);
            s+="Dato er endret\n";
        }
        if(facilityName!="null"){
            this.setFacilityName(facilityName);
            s+="Lokalnavn er endret\n";
        }
        if(phoneNumber!="null"){
            this.setPhonenumber(phoneNumber);
            s+="Telefonnummer er endret\n";
        }
        if(seatNumber!=this.seatNumber){
            this.setSeatNumber(seatNumber);
            s+="Setenummer er endret\n";
        }
        if(seatRow!=this.seatRow){
            this.setSeatRow(seatRow);
            s+="Radnummer er endret\n";
        }
        if(price!=0){
            setPrice(price);
            s+="Prisen er endret";
        }
        return s;
    }
}

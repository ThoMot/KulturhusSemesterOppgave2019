//fjerne alle metoder for billett og reservasjon, men se først om det kan brukes i den andre reservasjonen
//slette arraylist
//lag tostring metode
//lage setmetoder som også sjekker at det blir gitt riktig type input
package org.group38.kulturhus.model;

public class Facility {
    private String facilityName;
    private String facilityType;
    private int maxAntSeats;
    private int rows;
    private int columns;


    // Constructor
    public Facility(){

    }
    public Facility(String facilityName, int maxAntSeats){
        this.facilityName = facilityName;
        this.facilityType = "Forsamlinssal";
        this.maxAntSeats = maxAntSeats;
    }

    public Facility(String facilityName, String facilityType, int numRows, int seatsPerRow){
        this.facilityName = facilityName;
        this.facilityType = facilityType;
        this.rows=numRows;
        this.columns=seatsPerRow;
    }

    public Facility addCinema(){
        return new Facility("Sal 1", "Kino", 50,40);
    }

    public Facility addTheater(){
        return new Facility("Sal 2", "Theater", 4,3);
    }

    public Facility addAssemblyHall(){
        return new Facility("Sal 3", 40);
    }


    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getMaxAntSeats() { return maxAntSeats; }

    @Override
    public String toString() {
        if(maxAntSeats != 0){
            return toForsamlingssal();
        }
        else return facilityName + ", " + facilityType;

    }

    public String toForsamlingssal() {
        return facilityName + ", " + facilityType;
    }



}
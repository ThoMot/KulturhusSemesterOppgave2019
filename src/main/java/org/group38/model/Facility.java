package org.group38.model;

public class Facility {
    String facilityName;
    String type;
    int rows;
    int columns;
    int totalSeats;

    //constructor
    public Facility(String facilityName, String type, int rows, int columns){
        this.columns=columns;
        this.rows=rows;
        this.facilityName=facilityName;
        this.type=type;
        this.totalSeats=columns*rows; //burde vi bruke logikk her, eller er det bedre å legge inn egen variabel?
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public String getType() {
        return type;
    }
}

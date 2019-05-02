//fjerne alle metoder for billett og reservasjon, men se først om det kan brukes i den andre reservasjonen
//slette arraylist
//lag tostring metode
//lage setmetoder som også sjekker at det blir gitt riktig type input
package org.group38.kulturhus.model.facility;


import java.io.Serializable;

import static org.group38.kulturhus.Utilities.Validate.isNotEmptyString;
import static org.group38.kulturhus.Utilities.Validate.isValidNumber;

public class Facility implements Serializable {
    private String facilityName;
    private String facilityType;
    private int maxAntSeats;
    private int rows;
    private int columns;


    // Constructor
    public Facility(){
    }

    public Facility(String facilityName, String facilityType, int maxAntSeats){
        if(!isNotEmptyString(facilityName))throw new NullPointerException();
        if(!isNotEmptyString(facilityType))throw new NullPointerException();
        if(!isValidNumber(maxAntSeats)) throw new IllegalArgumentException();
        this.facilityName = facilityName;
        this.facilityType = facilityType;
        this.maxAntSeats = maxAntSeats;
    }

    public Facility(String facilityName, String facilityType, int numRows, int seatsPerRow){
        if(!isNotEmptyString(facilityName))throw new NullPointerException();
        if(!isNotEmptyString(facilityType))throw new NullPointerException();
        if(!isValidNumber(numRows)) throw new IllegalArgumentException();
        if(!isValidNumber(seatsPerRow)) throw new IllegalArgumentException();
        this.facilityName = facilityName;
        this.facilityType = facilityType;
        this.rows = numRows;
        this.columns = seatsPerRow;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        if(!isNotEmptyString(facilityName))throw new NullPointerException();
        this.facilityName = facilityName;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        if(!isNotEmptyString(facilityType))throw new NullPointerException();
        this.facilityType = facilityType;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {

        if(!isValidNumber(rows)) throw new IllegalArgumentException();this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {

        if(!isValidNumber(columns)) throw new IllegalArgumentException();this.columns = columns;
    }

    public int getMaxAntSeats() { return maxAntSeats; }

    //Denne er ny
//    public void setMaxAntSeats(int maxAntSeats){
//        if(!isValidNumber(maxAntSeats)) throw new IllegalArgumentException();
//        this.maxAntSeats=maxAntSeats;
//    }


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
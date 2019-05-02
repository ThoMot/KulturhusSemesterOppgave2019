package org.group38.kulturhus.model.facility;
import java.io.Serializable;
import static org.group38.kulturhus.Utilities.Validate.isNotEmptyString;
import static org.group38.kulturhus.Utilities.Validate.isValidNumber;

public class Facility implements Serializable {
    // data fields
    private String facilityName;
    private String facilityType;
    private int maxAntSeats;
    private int rows;
    private int columns;

    /** Construcors are overloaded to be able to create events that implements both
     * numberedSeating event og freeSeated event.
     * The empty constructor is used to read and write to/from files*/
    public Facility(){
    }

    /**Constructor for EventFreeSeating*/
    public Facility(String facilityName, String facilityType, int maxAntSeats){
        if(!isNotEmptyString(facilityName))throw new NullPointerException();
        if(!isNotEmptyString(facilityType))throw new NullPointerException();
        if(!isValidNumber(maxAntSeats)) throw new IllegalArgumentException();
        this.facilityName = facilityName;
        this.facilityType = facilityType;
        this.maxAntSeats = maxAntSeats;
    }

    /**Constructor for EventNumberedSeating*/
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

    /** Getter and setter for facilityName. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        if(!isNotEmptyString(facilityName))throw new NullPointerException();
        this.facilityName = facilityName;
    }

    /** Getter and setter for facilityType. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        if(!isNotEmptyString(facilityType))throw new NullPointerException();
        this.facilityType = facilityType;
    }

    /** Getter and setter for rows and columns for EventNumberedSeating To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(!isValidNumber(rows)) throw new IllegalArgumentException();
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        if(!isValidNumber(columns)) throw new IllegalArgumentException();
        this.columns = columns;
    }

    /** Getter and setter for max number seats for EventFreeSeating */
    public int getMaxAntSeats() { return maxAntSeats; }

    //Skal denne slettes?
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
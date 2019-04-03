package org.group38.model;

import java.util.ArrayList;
import java.util.List;

public class Facility {
    private final String facilityName;
    private String facilityType;
    private List<Seat> seats = new ArrayList<>();


    // Constructor

    public Facility(){
    }

    public Facility(String facilityName, String facilityType, int numRows, int seatsPerRow){
        this.facilityName = facilityName;
        this.facilityType = facilityType;


        // Creating seats with rows
        int lastRow = 'A' + (numRows -1);
        for(char row = 'A'; row <= lastRow; row++){
            for(int seatNum = 1; seatNum <= seatsPerRow; seatNum++){
                Seat seat = new Seat(row + String.format("%02d", seatNum));
                seats.add(seat);
            }
        }

    }

    public String getFacilityName() {
        return facilityName;
    }

    public String getFacilityType() {
        return facilityType;
    }

    // Check if seat exists and reserve seat.
    public boolean reserveSeat(String seatNumber){
        Seat requestedSeat = null;
        for(Seat seat : seats){
            if(seat.getSeatNumber().equals(seatNumber)){
                requestedSeat = seat;
                break;
            }
        }

        if(requestedSeat == null){
            System.out.println("There is no seat " + seatNumber);
            return false;
        }

        return requestedSeat.reserve();
    }

    // for testing, skal slettes
    public void getSeats() {
        for(Seat seat : seats) {
            System.out.println(seat.getSeatNumber());
        }
    }

    // Subclass of seats
    public class Seat {
        private final String seatNumber;
        private boolean reserved = false;

        public Seat(String seatNumber){
            this.seatNumber = seatNumber;
        }

        // Reserve ONE seat if it's not reserved.
        public boolean reserve() {
            if(!this.reserved){
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved");
                return true;
            } else{
                return false;
            }
        }

        // Cancel reservation if reservation exists.
        public boolean cancel() {
            if(this.reserved){
                this.reserved = false;
                System.out.println("Reservation of seat " + seatNumber + " cancelled");
                return true;
            } else {
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }
    }
}
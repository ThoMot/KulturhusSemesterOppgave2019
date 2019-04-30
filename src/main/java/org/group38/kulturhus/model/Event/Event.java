package org.group38.kulturhus.model.Event;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.facility.Facility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.group38.kulturhus.model.Kulturhus.getEvents;


public abstract class Event {
    private final AtomicInteger eventId=new AtomicInteger();
    private ContactPerson contactPerson;
    private Facility facility;
    private EventInfo eventInfo;
    private double ticketPrice;


    /*
    In this constructor we specifically check for overLaps between other already created events.
    This constructor creates an event with the given input
     */
    public Event(ContactPerson contactPerson, Facility facility, EventInfo eventInfo, double ticketPrice){
        if(!checkOverlap(eventInfo, facility)) throw new IllegalArgumentException("Du kan ikke lage to arrangementer i samme lokale\n til samme tid");
        this.facility=facility;
        this.ticketPrice = ticketPrice;
        this.contactPerson = contactPerson;
        this.eventInfo = eventInfo;
    }
    /*
    This method checks for already existing events in the given time in the given facility
    and returns true or false
     */
    private boolean checkOverlap(EventInfo eventInfo2,Facility facility2){
        ArrayList<Event> events = getEvents();
        for(Event event: events){
            if(eventInfo2.getTime()==event.getTime()&&eventInfo2.getDate()==event.getDate()&&facility2==event.getFacility()){
                return false;
            }
        }
        return true;
    }
//**toStringMethod that contains the eventInfo, the date, the eventname and the facility.
    public String toString(){
        return eventInfo.toString() + eventInfo.getDate() + "\n\t\t\t" +
                eventInfo.getEventName() + "\n\t\t\t" +
                facility.toString();
    }
//**Getters and setters
    public double getTicketPrice(){
        return ticketPrice;
    }

    public EventInfo getEventInfo(){
        return eventInfo;
    }

    public Facility getFacility() {
        return facility;
    }

    public String getPhoneNr(){ return contactPerson.getPhoneNr();
    }

    public String getEventName(){
        return eventInfo.getEventName();
    }

    public String getProgram(){
        return eventInfo.getProgram();
    }

    public String getPerformer(){
        return eventInfo.getPerformers();
    }

    public LocalDate getDate(){
        return eventInfo.getDate();
    }

    public LocalTime getTime(){
        return eventInfo.getTime();
    }

    public String getFacilityName(){
        return facility.getFacilityName();
    }

    public int getMaxSeats(){
        return facility.getMaxAntSeats();
    }

    public String getType() { return getEventInfo().getType(); }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setFacility(){
        this.facility=facility;
    }


    public AtomicInteger getEventId() {
        return eventId;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public abstract ArrayList getTickets();

    public abstract String allSeats();
}

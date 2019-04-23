//er det mer som må kunne redigeres ved et arrangement enn billettpris og dato? skal vi feks kunne endre hvilket lokale det skjer i? da blir det mye som må endres
//Hvordan skal utskrift av ledige seter se ut?
//Trenger vi mer i tostringmetoden?
//lage metoder som samsvarer med at lokalet ikke har sitteplasser også
//legge inn abstarkte metoder som er felles for subklassene

package org.group38.kulturhus.model.Event;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.facility.Facility;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public abstract class Event {
    private final AtomicInteger eventId=new AtomicInteger();
    private ContactPerson contactPerson; //skal denne også være på eventinfo?
    private Facility facility;
    private EventInfo eventInfo;
    private double ticketPrice;
    private String type;


    //constructor
    public Event(ContactPerson contactPerson, Facility facility, EventInfo eventInfo, double ticketPrice){
        this.facility=facility;
        this.ticketPrice = ticketPrice;
        this.contactPerson = contactPerson;
        this.eventInfo = eventInfo;
    }

    //er det interessant å skrive ut noe mer info om et arrangement?
    public String toString(){
        return eventInfo.toString() + eventInfo.getDate() + "\n\t\t\t" +
                eventInfo.getEventName() + "\n\t\t\t" +
                facility.toString();
    }


    public double getTicketPrice(){
        return ticketPrice;
    }

    public EventInfo getEventInfo(){
        return eventInfo;
    }

    public Facility getFacility() {
        return facility;
    }

    public ContactPerson getContactPerson() { return contactPerson; }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public abstract ArrayList<Ticket> boughtTickets();

    public void setFacility(){
        this.facility=facility;
    }

    public String getType(){
        return type;
    }

    public AtomicInteger getEventId() {
        return eventId;
    }
}

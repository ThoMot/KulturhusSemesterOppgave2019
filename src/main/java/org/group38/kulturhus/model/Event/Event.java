//er det mer som må kunne redigeres ved et arrangement enn billettpris og dato? skal vi feks kunne endre hvilket lokale det skjer i? da blir det mye som må endres
//Hvordan skal utskrift av ledige seter se ut?
//Trenger vi mer i tostringmetoden?
//lage metoder som samsvarer med at lokalet ikke har sitteplasser også

package org.group38.kulturhus.model.Event;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Facility;
import java.util.ArrayList;


public abstract class Event {
    private ContactPerson contactPerson; //skal denne også være på eventinfo?
    private Facility facility;
    //private ArrayList performers; //skal denne også være på eventinfo?
    private String performers; //skal denne også være på eventinfo?
    private EventInfo eventInfo;
    private double ticketPrice;

    //constructor
    public Event(ContactPerson contactPerson, Facility facility, String performers, EventInfo eventInfo, double ticketPrice){
        this.facility=facility;
        this.performers = performers;
        this.ticketPrice = ticketPrice;
        this.contactPerson = contactPerson;
        this.eventInfo = eventInfo;
    }

    //er det interessant å skrive ut noe mer info om et arrangement?
    public String toString(){
        return eventInfo.toString() + eventInfo.getTime() + "\n\t\t\t" +
                eventInfo.getEventName() + "\n\t\t\t" +
                facility.toString();
    }


    public String getPerformers() {
        return performers;
    }

    public void setPerformers(String performers) {
        this.performers = performers;
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

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}

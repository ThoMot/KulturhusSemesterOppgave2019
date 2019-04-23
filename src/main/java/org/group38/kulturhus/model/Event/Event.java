//er det mer som må kunne redigeres ved et arrangement enn billettpris og dato? skal vi feks kunne endre hvilket lokale det skjer i? da blir det mye som må endres
//Hvordan skal utskrift av ledige seter se ut?
//Trenger vi mer i tostringmetoden?
//lage metoder som samsvarer med at lokalet ikke har sitteplasser også

package org.group38.kulturhus.model.Event;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.facility.Facility;

import java.time.LocalDate;
import java.time.LocalTime;


public abstract class Event {
    private ContactPerson contactPerson; //skal denne også være på eventinfo?
    private Facility facility;
    private EventInfo eventInfo;
    private double ticketPrice;

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

    //brukes for lagring
    public String getContactPersonPhone() {
        return getContactPerson().getContactInfo().getPhoneNr();
    }

    //brukes for lagring
    public String getFacilityName(){
        return getFacility().getFacilityName();
    }

    //brukes for lagring
    public String getFacilityType(){
        return getFacility().getFacilityType();
    }

    //brukes for lagring
    public String getPerformer(){
        return getEventInfo().getPerformer();
    }

    //brukes for lagring
    public String getEventName(){
        return getEventInfo().getEventName();
    }

    public String getEventProgram(){
        return getEventInfo().getProgram();
    }

    public LocalDate getDate(){
        return getEventInfo().getDate();
    }

    public LocalTime getLocalTime(){
        return getEventInfo().getTime();
    }

}

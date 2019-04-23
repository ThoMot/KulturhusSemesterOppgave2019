//er det mer som må kunne redigeres ved et arrangement enn billettpris og dato? skal vi feks kunne endre hvilket lokale det skjer i? da blir det mye som må endres
//Hvordan skal utskrift av ledige seter se ut?
//Trenger vi mer i tostringmetoden?
//lage metoder som samsvarer med at lokalet ikke har sitteplasser også
//legge inn abstarkte metoder som er felles for subklassene

package org.group38.kulturhus.model.Event;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.facility.Facility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


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

    public String getPhoneNr(){
        return contactPerson.getContactInfo().getPhoneNr();
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

    public int getRows(){
        return facility.getRows();
    }

    public int getColumns(){
        return facility.getColumns();
    }


    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public abstract ArrayList<Ticket> boughtTickets();

    public void setFacility(){
        this.facility=facility;
    }

}

//er det mer som må kunne redigeres ved et arrangement enn billettpris og dato? skal vi feks kunne endre hvilket lokale det skjer i? da blir det mye som må endres
//Hvordan skal utskrift av ledige seter se ut?
//Trenger vi mer i tostringmetoden?
//lage metoder som samsvarer med at lokalet ikke har sitteplasser også

package org.group38.model.Event;

import org.group38.model.ContactPerson.ContactPerson;
import org.group38.model.Facility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringJoiner;

public class Event {
    ContactPerson contactPerson; //skal denne også være på eventinfo?
    Facility facility;
    ArrayList performers; //skal denne også være på eventinfo?
    EventInfo eventInfo;
    double ticketPrice;

    //Checks if the seat choosen is taken, and returns an errormessage if so, otherwise it creates a new ticket


    //er det interessant å skrive ut noe mer info om et arrangement?
    public String toString(){
        return eventInfo.toString()+"\n"+
                "Billetpris: "+ticketPrice;
    }

    public double getTicketPrice(){
        return ticketPrice;
    }
    public EventInfo getEventInfo(){
        return eventInfo;
    }
}

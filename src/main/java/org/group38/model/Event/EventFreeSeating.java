package org.group38.model.Event;

import org.group38.model.ContactPerson.ContactPerson;
import org.group38.model.Facility;

import java.util.ArrayList;

public class EventFreeSeating extends Event {
    private Ticket[] tickets;
    private int maxSeats;

    public EventFreeSeating(ContactPerson contactPerson, Facility facility, ArrayList performers, double ticketPrice, EventInfo eventInfo) {
        this.facility=facility;
        this.maxSeats=facility.getMaxAntSeats();
        tickets = new Ticket[maxSeats];
        this.performers = performers;
        this.ticketPrice = ticketPrice;
        this.contactPerson = contactPerson;
        this.eventInfo = eventInfo;
    }
}

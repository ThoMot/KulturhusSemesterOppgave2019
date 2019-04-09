package org.group38.model.Event;

import org.group38.model.ContactPerson.ContactPerson;
import org.group38.model.Facility;

import java.util.ArrayList;

public class EventFreeSeating extends Event {
    private Ticket[] tickets;
    private int maxSeats;

    public EventFreeSeating(ContactPerson contactPerson, Facility facility, ArrayList performers, double ticketPrice, EventInfo eventInfo) {
        super(contactPerson, facility, performers, eventInfo, ticketPrice);
        this.maxSeats=facility.getMaxAntSeats();
        tickets = new Ticket[maxSeats];

    }
    //BuyTicket
    //DeleteTicket
    //setDate
    //setTicketPrice
    //FreeSeats
    //Fullt
    //FindTickets


}

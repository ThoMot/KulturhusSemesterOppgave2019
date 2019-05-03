//slette arrangementer etter at datoen har vært?
package org.group38.kulturhus.model;

import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.*;
import org.group38.kulturhus.model.facility.Facility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

public class Kulturhus {
    private static ArrayList<Event> events = new ArrayList<>();
    private static ArrayList<ContactPerson> contactPeople = new ArrayList<>();
    private static ArrayList<Facility> facilities = new ArrayList<>();
    private static ArrayList<Ticket> tickets = new ArrayList<>();

    //kun for testing
    public static void opprett() {
        facilities.add(new Facility("Sal 1", "Kinosal", 10, 20));
        LocalDate d = LocalDate.of(2019, Month.APRIL, 22);
        LocalTime t = LocalTime.of(22, 00);
        contactPeople.add(new ContactPerson("Martina", "Førre", new ContactInfo("martina@gmail.com", "11223344")));
        EventInfo eventInfo = new EventInfo("Max Manus", "film", "Anne", "kino", d, t);
        EventNumberedSeating maxManus = new EventNumberedSeating(contactPeople.get(0), facilities.get(0), 100, eventInfo);

        facilities.add(new Facility("Sal 2", "Teatersal", 30, 12));
        LocalDate d1 = LocalDate.of(2019, Month.MAY, 10);
        LocalTime t1 = LocalTime.of(18, 00);
        contactPeople.add(new ContactPerson("Tor", "Mare", new ContactInfo("mail@gmail.com", "22334455")));
        EventInfo eventInfo1 = new EventInfo("Åpning", "Åpning av kinosalen", "Arild", "kino", d1, t1);
        EventNumberedSeating event2 = new EventNumberedSeating(contactPeople.get(1), facilities.get(1), 100, eventInfo1);


        facilities.add(new Facility("Sal 3", "Forsamlingssal", 34));
        EventInfo eventInfo3 = new EventInfo("Uten seteres", "tester dette", "ingen", facilities.get(2).getFacilityType(), d1, t1);
        EventFreeSeating eventFreeSeating = new EventFreeSeating(contactPeople.get(1), facilities.get(2), 22, eventInfo3);

        event2.buyTicket(8, 2, "90862870");
        event2.buyTicket(2, 3, "90862870");
        event2.buyTicket(3, 4, "90862870");

        maxManus.buyTicket(1, 2, "11223344");
        maxManus.buyTicket(2, 3, "11223344");
        maxManus.buyTicket(3, 4, "56743827");

        //events.add(maxManus);
        //events.add(event2);
        //events.add(eventFreeSeating);
    }
/** getter methods*/
    public static ArrayList<ContactPerson> getContactPeople() {
        return contactPeople;
    }

    public static ArrayList<Event> getEvents() {
        return events;
    }

    public static ArrayList<Facility> getFacilities() {
        return facilities;
    }

    public static ArrayList<Ticket> getTickets() {
        return tickets;
    }


}

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
    private static ArrayList<Event> events;
    private static ArrayList<Ticket> tickets;
    public Kulturhus(){
            ArrayList<Event> events = new ArrayList();
            tickets = new ArrayList<Ticket>();
            this.events=events;

    }


    //kun for testing
    public static void opprett() {
        Facility facility = new Facility("Sal 1", "Kinosal", 10, 20);
        LocalDate d = LocalDate.of(2019, Month.APRIL, 22);
        LocalTime t = LocalTime.of(22,00);
        ContactPerson contactPerson = new ContactPerson("Martina", "Førre", new ContactInfo("martina@gmail.com", "11223344"));
        EventInfo eventInfo = new EventInfo("Max Manus", "film","Ingen", d,t);
        EventNumberedSeating maxManus=new EventNumberedSeating(contactPerson, facility, 100, eventInfo);

        Facility facility1 = new Facility("Sal 2", "Teatersal", 10, 12);
        LocalDate d1 = LocalDate.of(2019, Month.MAY, 10);
        LocalTime t1 = LocalTime.of(18,00);
        ContactPerson contactPerson1 = new ContactPerson("Tor", "Mare", new ContactInfo("mail@gmail.com", "22334455"));
        EventInfo eventInfo1 = new EventInfo("Åpning", "Åpning av kinosalen","Sjefen" ,d1, t1);
        EventNumberedSeating event2 =new EventNumberedSeating(contactPerson1, facility1, 100, eventInfo1);

        events = new ArrayList<>();
        events.add(maxManus);
        events.add(event2);


        event2.BuyTicket(1,2,"11223344");
        event2.BuyTicket(2,3,"11223344");
        event2.BuyTicket(1,4,"56743827");


        Ticket ticket1 = new Ticket(200, "11111111", d1, t1);
        Ticket ticket2 = new Ticket(150, "11111111", d, t);
        tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);






    }

    public static ArrayList<Event> getEvents() {
        return events;
    }

    public static ArrayList<Ticket> getTickets(){
        return tickets;
    }
    public static ArrayList<Facility> getFacility(){
        return getFacility();
    }


}

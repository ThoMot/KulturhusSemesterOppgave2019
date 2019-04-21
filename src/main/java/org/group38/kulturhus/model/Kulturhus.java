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
        Facility facility = new Facility().addCinema();
        LocalDate d = LocalDate.of(2019, Month.APRIL, 22);
        LocalTime t = LocalTime.of(22,00);
        ContactPerson contactPerson = new ContactPerson("Martina", "Førre", new ContactInfo("martina@gmail.com", "11223344"));
        EventInfo eventInfo = new EventInfo("Max Manus", "film", d,t);
        EventNumberedSeating maxManus=new EventNumberedSeating(contactPerson, facility, "Ane Dahl Torp", 100, eventInfo);

        Facility facility1 = new Facility().addTheater();
        LocalDate d1 = LocalDate.of(2019, Month.MAY, 10);
        LocalTime t1 = LocalTime.of(18,00);
        ContactPerson contactPerson1 = new ContactPerson("Tor", "Mare", new ContactInfo("mail@gmail.com", "22334455"));
        EventInfo eventInfo1 = new EventInfo("Åpning", "Åpning av kinosalen", d1, t1);
        EventNumberedSeating event2 =new EventNumberedSeating(contactPerson1, facility1, "Sjefen", 100, eventInfo1);

        events = new ArrayList<>();
        events.add(maxManus);
        events.add(event2);

//        event2.BuyTicket(3, 5, "34563254", d1, t1);
//        event2.BuyTicket(1,4,"56743827", d, t);
        Ticket ticket1 = new Ticket(200, "54353423", d1, t1);
        Ticket ticket2 = new Ticket(150, "45761234", d, t);
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

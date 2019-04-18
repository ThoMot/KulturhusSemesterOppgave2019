//slette arrangementer etter at datoen har vært?
package org.group38.kulturhus.model;

import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Kulturhus {
    private ArrayList<Event> events;
    private ArrayList<Ticket> tickets;
    public Kulturhus(){
            ArrayList<Event> events = new ArrayList();
            tickets = new ArrayList<>();
            this.tickets = tickets;
            this.events=events;
    }


    //kun for testing
    public void opprett() {
//        Facility facility = new Facility("Sal1", "kino", 10, 10);
//        Calendar d = new GregorianCalendar(2019, 10, 10, 22, 00);
//        ContactPerson contactPerson = new ContactPerson("Martina", "Førre", new ContactInfo("martina@gmail.com", "11223344"));
//        EventInfo eventInfo = new EventInfo("Max Manus", "film", d);
//        ArrayList<Performer> performers = new ArrayList<Performer>();
//        EventNumberedSeating maxManus=new EventNumberedSeating(contactPerson, facility, performers, 100, eventInfo);

        Facility facility = new Facility("Sal1", "kino", 10, 10);
        LocalDate d = LocalDate.of(2019, Month.APRIL, 22);
        LocalTime t = LocalTime.of(22,00);
        ContactPerson contactPerson = new ContactPerson("Martina", "Førre", new ContactInfo("martina@gmail.com", "11223344"));
        EventInfo eventInfo = new EventInfo("Max Manus", "film", d,t);
        EventNumberedSeating maxManus=new EventNumberedSeating(contactPerson, facility, "Ane Dahl Torp", 100, eventInfo);

        Facility facility1 = new Facility("Sal1", "Theater", 15, 10);
        LocalDate d1 = LocalDate.of(2019, Month.MAY, 10);
        LocalTime t1 = LocalTime.of(18,00);
        ContactPerson contactPerson1 = new ContactPerson("Tor", "Mare", new ContactInfo("mail@gmail.com", "22334455"));
        EventInfo eventInfo1 = new EventInfo("Åpning", "Åpning av kinosalen", d1, t1);
        EventNumberedSeating event2 =new EventNumberedSeating(contactPerson1, facility1, "Sjefen", 100, eventInfo1);

        events = new ArrayList<>();
        events.add(maxManus);
        events.add(event2);


        maxManus.BuyTicket(1,2,"11223344");

    }

    public ArrayList<Event> getEvents() {
        return events;
    }


}
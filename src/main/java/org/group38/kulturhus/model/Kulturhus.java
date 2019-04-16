//slette arrangementer etter at datoen har vært?
package org.group38.kulturhus.model;

import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.Event.Performer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Kulturhus {
    private ArrayList<Event> events;


    //kun for testing
    Facility facility=new Facility("Sal1","kino", 10,10);
    Calendar d= new GregorianCalendar(2019, 10,10, 22,00);
    ContactPerson contactPerson = new ContactPerson("Martina", "Førre", new ContactInfo("martina@gmail.com","11223344"));
    EventInfo eventInfo = new EventInfo("Max Manus", "film",d);
    ArrayList<Performer> performers= new ArrayList <Performer>();

    EventNumberedSeating maxManus=new EventNumberedSeating(contactPerson, facility, performers, 100, eventInfo);



}

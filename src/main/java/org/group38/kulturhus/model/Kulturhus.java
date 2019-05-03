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


    /** getter methods */
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

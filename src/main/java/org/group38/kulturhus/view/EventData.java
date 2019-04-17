package org.group38.kulturhus.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.group38.kulturhus.model.Event.Event;

import java.nio.file.Path;
import java.io.BufferedReader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventData {

    // Oppretter statisk fil med alle eventene samlet.
    private static final String EVENTS_FILE = "events.xml";

    //Oppretter variabel med verdi på hver Single property.
    private static final String EVENT = "event";
    private static final String EVENT_NAME = "event_name";
    private static final String TICKET_PRICE = "ticket_price";
    private static final String EVENT_PROGRAM = "event_program";
    private static final String EVENT_DATE = "event_date";

    private ObservableList<Event> events;
    private DateTimeFormatter dateFormatter;

    public EventData(){
        events = FXCollections.observableArrayList();
        dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public ObservableList<Event> getEvents(){
        return events;
    }

    //Oppretter et nytt eventobjekt til liste over alle eventene
    public void addEvent(Event item){
        events.add(item);
    }

    //Sletter eventobjektet fra liten med alle eventene
    public void deleteEvent(Event item){
        events.remove(item);
    }

}

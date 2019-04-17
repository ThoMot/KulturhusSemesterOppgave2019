package org.group38.kulturhus.model.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class EventInfo {
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy");

    private String eventName;
    private String program;
    private LocalDate date;
    private LocalTime time;

    public EventInfo(String eventName, String program, LocalDate date) {
        this.eventName = eventName;
        this.program = program;
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }


    public void setDate(LocalDate date) {
        this.date = date;

    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String toString(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d. MMMM yyyy");
        return df.format(date) + " \t" + eventName;
    }
}

package org.group38.kulturhus.model.Event;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventInfo {
    private String eventName;
    private String program;
    private String performers;
    private LocalDate date;
    private LocalTime time;


    public EventInfo(String eventName, String program,String performers, LocalDate date, LocalTime time) {
        this.eventName = eventName;
        this.program = program;
        this.performers = performers;
        this.date = date;
        this.time = time;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName=eventName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program=program;
    }

    public String getPerformers() {
        return performers;
    }

    public void setPerformers(String performers) {
        this.performers = performers;
    }

    public LocalDate getDate() {
        return date;
    }


    public void setDate(LocalDate date) {
        this.date=date;
    }
    public LocalTime getTime() {
        return time;
    }


    public void setTime(LocalTime time) {
        this.time=time;
    }

}

package org.group38.kulturhus.model.Event;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventInfo {
    private String eventName;
    private String program;
    private String performer;
    private LocalDate date;
    private LocalTime time;



    public EventInfo(String eventName, String program, String performer, LocalDate date, LocalTime time) {
        this.eventName = eventName;
        this.program = program;
        this.performer = performer;
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

    public String getPerformer() { return performer; }


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

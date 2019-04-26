package org.group38.kulturhus.model.Event;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.group38.kulturhus.model.Validate.isOnlyLetters;

public class EventInfo {
    private String eventName;
    private String program;
    private String performer;
    private LocalDate date;
    private LocalTime time;
    private String type;



    public EventInfo(String eventName, String program, String performer, String type, LocalDate date, LocalTime time) {
        this.eventName = eventName;
        this.program = program;
        this.performer = performer;
        this.date = date;
        this.time = time;
        this.type = type;
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
        return performer;
    }

    public void setPerformers(String performers) {
        this.performer = performer;
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

    public String getType(){
        return type;
    }

}

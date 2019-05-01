package org.group38.kulturhus.model.Event;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.group38.kulturhus.model.Validate.isNotEmptyString;

public class EventInfo implements Serializable {
    private String eventName;
    private String program;
    private String performer;
    private LocalDate date;
    private LocalTime time;
    private String type;


/** this constructor checks for valid input before creating an object*/
    public EventInfo(String eventName, String program, String performer, String type, LocalDate date, LocalTime time) {
        if(!isNotEmptyString(eventName)) throw new NullPointerException("EventNavn kan ikke være tomt");
        if(!isNotEmptyString(performer)) throw new NullPointerException("Artistfeltet kan ikke være tomt");
        if(!isNotEmptyString(program)) throw new NullPointerException("programFeltet kan ikke være tomt");
        if(!isNotEmptyString(type)) throw new NullPointerException("TypeFeltet kan ikke være tomt");
        this.eventName = eventName;
        this.program = program;
        this.performer = performer;
        this.date = date;
        this.time = time;
        this.type = type;
    }
/**getter and setter methods that also checks for valid input*/

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        if(!isNotEmptyString(eventName)) throw new NullPointerException("EventNavn kan ikke være tomt");
        this.eventName=eventName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        if(!isNotEmptyString(program)) throw new NullPointerException("programFeltet kan ikke være tomt");
        this.program=program;
    }

    public String getPerformers() {
        return performer;
    }

    public void setPerformers(String performers) {
        if(!isNotEmptyString(performer)) throw new NullPointerException("Artistfeltet kan ikke være tomt");
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

    public void setType(){
        if(!isNotEmptyString(type)) throw new NullPointerException("TypeFeltet kan ikke være tomt");
        this.type=type;
    }
}

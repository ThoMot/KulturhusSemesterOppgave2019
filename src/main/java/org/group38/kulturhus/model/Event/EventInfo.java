package org.group38.kulturhus.model.Event;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.group38.frameworks.Validate.isNotEmptyString;

public class EventInfo implements Serializable {
    private String eventName;
    private String program;
    private String performer;
    private LocalDate date;
    private LocalTime time;
    private String type;


    private EventInfo(){
    }

    /** EventInfo constructor checks for valid input before creating a new object*/
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

    /** Getter and setter method for eventName. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        if(!isNotEmptyString(eventName)) throw new NullPointerException("EventNavn kan ikke være tomt");
        this.eventName=eventName;
    }

    /** Getter and setter method for the events program. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        if(!isNotEmptyString(program)) throw new NullPointerException("programFeltet kan ikke være tomt");
        this.program=program;
    }

    /** Getter and setter method for the events performers. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performers) {
        if(!isNotEmptyString(performer)) throw new NullPointerException("Artistfeltet kan ikke være tomt");
        this.performer = performer;
    }

    /** Getter and setter method for the events date. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date=date;
    }

    /** Getter and setter method for the events time. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time=time;
    }

    /** Getter method for the event type. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getType(){
        return type;
    }
//KAN SLETTES??
//    public void setType(){
//        if(!isNotEmptyString(type)) throw new NullPointerException("TypeFeltet kan ikke være tomt");
//        this.type=type;
//    }
}

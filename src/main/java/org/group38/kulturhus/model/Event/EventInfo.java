package org.group38.kulturhus.model.Event;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventInfo {
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d. MMMM");


    private String eventName;
    private String program;
    private ObjectProperty<LocalDate> date;
    private ObjectProperty<LocalTime> time;


    public EventInfo(String eventName, String program, LocalDate date, LocalTime time) {
        this.eventName=eventName;
        this.program=program;
        this.date = new SimpleObjectProperty<>(date);
        this.time=new SimpleObjectProperty<>(time);
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

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public LocalTime getTime() {
        return time.get();
    }

    public ObjectProperty<LocalTime> timeProperty() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time.set(time);
    }

    public String toString(){
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy");
//        return dateFormatter.format(date) + ", kl: " + time;
        return eventName;
    }
}

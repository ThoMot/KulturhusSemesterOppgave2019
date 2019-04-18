package org.group38.kulturhus.model.Event;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class EventInfo {
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d. MMMM");


    private SimpleStringProperty eventName = new SimpleStringProperty("");
    private SimpleStringProperty program = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> date;
    private ObjectProperty<LocalTime> time;

    public EventInfo(String eventName, String program, LocalDate date, LocalTime time) {
        this.eventName.set(eventName);
        this.program.set(program);
        this.date = new SimpleObjectProperty<>(date);
        this.time = new SimpleObjectProperty<>(time);
    }

    public String getEventName() {
        return eventName.get();
    }

    public SimpleStringProperty eventNameProperty() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName.set(eventName);
    }

    public String getProgram() {
        return program.get();
    }

    public SimpleStringProperty programProperty() {
        return program;
    }

    public void setProgram(String program) {
        this.program.set(program);
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
        return date + " \t\t";
    }
}

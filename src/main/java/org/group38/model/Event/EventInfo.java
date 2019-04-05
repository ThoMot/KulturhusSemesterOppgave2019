package org.group38.model.Event;

import java.util.Calendar;

public class EventInfo {
    private String eventName;
    private String program;
    private Calendar date;

    public EventInfo(String eventName, String program, Calendar date) {
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}

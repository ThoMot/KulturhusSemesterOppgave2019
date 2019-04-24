package org.group38.kulturhus.model.SaveLoad;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.Event.Ticket;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveJobj implements SaveDataInterface{
    String filename;

public class SaveJobj implements org.group38.kulturhus.model.SaveLoad.SaveDataInterface {

    @Override
    public void writeObject(CsvBase object) {
        if (object instanceof EventNumberedSeating || object instanceof EventFreeSeating) {
            filename = "events.csv";
        } else if (object instanceof ContactPerson) {
            filename = "contactPerson.csv";
        } else if (object instanceof Ticket) {
            filename = "tickets.csv";
        }
        try {

            FileOutputStream fos = new FileOutputStream("filename");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(object);
        } catch(IOException e) {
            e.printStackTrace(); // This should not happen, so we print debug information here.
        }

    }


}



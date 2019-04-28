package org.group38.kulturhus.model.SaveLoad;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.Event.Ticket;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveJobj{
    String filename;

   /* @Override
    public void writeObject(CsvBase object) {
        if (object instanceof EventNumberedSeating || object instanceof EventFreeSeating) {
            filename = "events.jobj";
        } else if (object instanceof ContactPerson) {
            filename = "contactPerson.jobj";
        } else if (object instanceof Ticket) {
            filename = "tickets.jobj";
        }
        try {

            FileOutputStream fos = new FileOutputStream("filename");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(object);
        } catch(IOException e) {
            e.printStackTrace(); // This should not happen, so we print debug information here.
        }

    } */

    class JObj{

    }

}



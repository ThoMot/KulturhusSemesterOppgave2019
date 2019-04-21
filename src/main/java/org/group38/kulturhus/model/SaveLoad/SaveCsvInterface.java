package org.group38.kulturhus.model.SaveLoad;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.Event.Ticket;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SaveCsvInterface {

    public static boolean writeObject(Object object) {
        FileWriter fileWriter = null;
        List<Object> objects = new ArrayList<>();
        final String divider = ",";
        final String nextline = "\n";
        String filename = null;

        objects.add(object);

        if (object instanceof EventNumberedSeating || object instanceof EventFreeSeating) {
            filename = "events.csv";
        } else if (object instanceof ContactPerson) {
            filename = "ContactPerson.csv";
        } else if (object instanceof Ticket) {
            filename = "tickets.csv";
        }

        try {
            fileWriter = new FileWriter(filename);
            for (Object objectInfo : objects) {
                fileWriter.write(objectInfo.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }






}











        //Random access file - eksempel på canvas
    //en som skriver en og en
    //en som oppdaterer en linje




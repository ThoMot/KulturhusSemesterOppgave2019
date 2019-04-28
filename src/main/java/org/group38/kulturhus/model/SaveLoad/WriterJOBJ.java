package org.group38.kulturhus.model.SaveLoad;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.Event.Ticket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriterJOBJ implements SaveDataInterface {

    String filename;

    @Override
    public void writeObject(CsvBase obj) throws IOException {
        ArrayList<Object> listToWrite = new ArrayList<>();
        listToWrite.add(obj);

        writeObjects(listToWrite);

    }

    @Override
    public <T> void writeObjects(ArrayList<T> objectList) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("file.jobj"));
        out.writeObject(objectList);
    }
}

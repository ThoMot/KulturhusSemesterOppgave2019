package org.group38.frameworks.concurrency;

import javafx.collections.ObservableList;
import org.group38.kulturhus.model.SaveLoad.Save.WriteToCSV;
import org.group38.kulturhus.model.SaveLoad.Save.WriterInterface;
import org.group38.kulturhus.model.SaveLoad.Save.WriterJOBJ;


import java.io.IOException;
import java.util.ArrayList;

public class WriterThread implements Runnable {

    private Object objectToWrite;
    private ArrayList objectsToWrite;
    private String filename;

    protected WriterThread(Object objectToWrite, String filename) {
        this.objectToWrite = objectToWrite;
        this.filename = filename;
    }

    protected <T> WriterThread(ArrayList<T> objectsToWrite, String filename) {
        this.objectsToWrite = objectsToWrite;
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            writeObjects();
        } catch (IOException e) { //TODO Dette burde håndteres bedre, aka sendes til GUIet på en måte.
            e.printStackTrace();
        }
    }

    private void writeObjects() throws IOException {
        WriterInterface writer;
        String ext = filename.substring(filename.lastIndexOf(".")+1);

        switch (ext){
            case "jobj":
                writer = new WriterJOBJ();
                break;
            case "csv":
                writer = new WriteToCSV();
                break;
            default:
                throw new NullPointerException();
        }

        if (objectToWrite == null) {
            writer.writeObjects(objectsToWrite, filename);
        } else {
            writer.writeObject(objectToWrite, filename);
        }
    }
}

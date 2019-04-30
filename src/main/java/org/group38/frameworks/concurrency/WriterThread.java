package org.group38.frameworks.concurrency;

import javafx.collections.ObservableList;
import org.group38.kulturhus.model.SaveLoad.CsvBase;
import org.group38.kulturhus.model.SaveLoad.SaveCsvInterface;
import org.group38.kulturhus.model.SaveLoad.SaveDataInterface;
import org.group38.kulturhus.model.SaveLoad.WriterJOBJ;


import java.io.IOException;
import java.util.ArrayList;

public class WriterThread implements Runnable {

    private CsvBase objectToWrite;
    private ObservableList objectsToWrite;
    private String filename;

    protected WriterThread(CsvBase objectToWrite, String filename) {
        this.objectToWrite = objectToWrite;
        this.filename = filename;
    }

    protected <T> WriterThread(ObservableList<T> objectsToWrite, String filename) {
        this.objectsToWrite = objectsToWrite;
        this.filename = filename;
    }

    @Override
    public void run() {
        System.out.println("Write to file with Thread" + Thread.currentThread().getId());

        try {
            writeObjects();
        } catch (IOException e) { //TODO Dette burde håndteres bedre, aka sendes til GUIet på en måte.
            e.printStackTrace();
        }


    }

    private void writeObjects() throws IOException {
        SaveDataInterface writer;
        String ext = filename.substring(filename.lastIndexOf(".")+1);
        System.out.println(ext);

        switch (ext){
            case "jobj":
                writer = new WriterJOBJ();
                break;
            case "csv":
                writer = new SaveCsvInterface();
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

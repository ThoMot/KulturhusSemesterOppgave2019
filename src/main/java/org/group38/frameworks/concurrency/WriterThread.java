package org.group38.frameworks.concurrency;

import org.group38.kulturhus.model.SaveLoad.CsvBase;
import org.group38.kulturhus.model.SaveLoad.SaveCsvInterface;
import org.group38.kulturhus.model.SaveLoad.SaveDataInterface;
import org.group38.kulturhus.model.SaveLoad.WriterJOBJ;

import java.io.IOException;
import java.util.ArrayList;

public class WriterThread implements Runnable {

    private CsvBase objectToWrite;
    private ArrayList objectsToWrite;

    protected WriterThread(CsvBase objectToWrite) {
        this.objectToWrite = objectToWrite;
    }

    protected <T> WriterThread(ArrayList<T> objectsToWrite) {
        this.objectsToWrite = objectsToWrite;
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
        writer = new WriterJOBJ(); //TODO Finn en måte å sjekke på om filen det skrives til er JOBJ eller CSV.
        //writer = new SaveCsvInterface();

        if (objectToWrite == null) {
            writer.writeObjects(objectsToWrite);
        } else {
            writer.writeObject(objectToWrite);
        }
    }
}

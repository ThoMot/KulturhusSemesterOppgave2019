package org.group38.frameworks.concurrency;

import javafx.collections.ObservableList;
import org.group38.kulturhus.model.Exeptions.ParsingException;
import org.group38.kulturhus.model.SaveLoad.Load.ReadCSV;
import org.group38.kulturhus.model.SaveLoad.Load.ReaderInterface;
import org.group38.kulturhus.model.SaveLoad.Load.ReaderJOBJ;
import org.group38.kulturhus.model.SaveLoad.Save.WriteToCSV;
import org.group38.kulturhus.model.SaveLoad.Save.WriterInterface;
import org.group38.kulturhus.model.SaveLoad.Save.WriterJOBJ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ReaderThread<T> implements Callable<ArrayList<T>> {


    //TODO implementer callable
    //TODO bruker call ikke run. se write thread.

    private String filename;

    protected ReaderThread(String filename) {
        this.filename = filename;
    }


    @Override
    public ArrayList<T> call() {
        System.out.println("Reading from file with Thread" + Thread.currentThread().getId());

        try {
            return readObjects();
        } catch (IOException | ParsingException | ReflectiveOperationException e) { //TODO Dette burde håndteres bedre, aka sendes til GUIet på en måte.
            e.printStackTrace();
        }
        return null;
    }

    private <T> ArrayList<T> readObjects() throws ReflectiveOperationException, ParsingException,IOException, ClassNotFoundException {
        ReaderInterface readerInterface;
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        System.out.println(ext);

        switch (ext) {
            case "jobj":
                readerInterface = new ReaderJOBJ();
                break;
            case "csv":
                readerInterface = new ReadCSV();
                break;
            default:
                //TODO Make new exception here
                throw new NullPointerException();
        }
        return readerInterface.readObjects(filename);
    }
}


package org.group38.frameworks.concurrency;

import org.group38.frameworks.Exceptions.CorruptedFileException;
import org.group38.frameworks.Exceptions.ParsingException;
import org.group38.frameworks.SaveLoad.Load.ReadCSV;
import org.group38.frameworks.SaveLoad.Load.ReaderInterface;
import org.group38.frameworks.SaveLoad.Load.ReaderJOBJ;

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
    public ArrayList<T> call() throws CorruptedFileException {
        try {
            return readObjects();
        } catch (IOException | ParsingException | ReflectiveOperationException  e) { //TODO Dette burde håndteres bedre, aka sendes til GUIet på en måte.
            e.printStackTrace();
        }
        return null;
    }

    private <T> ArrayList<T> readObjects() throws ReflectiveOperationException, ParsingException,IOException, ClassNotFoundException, CorruptedFileException {
        ReaderInterface readerInterface;
        String ext = filename.substring(filename.lastIndexOf(".") + 1);

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


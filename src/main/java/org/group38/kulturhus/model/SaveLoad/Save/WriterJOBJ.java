package org.group38.kulturhus.model.SaveLoad.Save;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriterJOBJ implements WriterInterface {

    String filename;

    @Override
    public void writeObject(Object obj, String filename) throws IOException {
        ObservableList<Object> listToWrite = FXCollections.observableArrayList();
        listToWrite.add(obj);

        writeObjects(listToWrite, filename);

    }

    @Override
    public <T> void writeObjects(ObservableList<T> objectList, String filename) throws IOException {
        ArrayList<T> saveList = new ArrayList<>(objectList);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(saveList);
    }
}

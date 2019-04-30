package org.group38.kulturhus.model.SaveLoad.Save;

import javafx.collections.ObservableList;
import org.group38.kulturhus.model.Event.EventNumberedSeating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface WriterInterface {

    void writeObject(Object object, String filename) throws IOException;

    <T> void writeObjects(ObservableList<T> objectList, String filename) throws IOException;
    }

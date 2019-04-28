package org.group38.kulturhus.model.SaveLoad;

import javafx.collections.ObservableList;
import org.group38.kulturhus.model.Event.EventNumberedSeating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface SaveDataInterface {

    void writeObject(CsvBase object) throws IOException;

    <T> void writeObjects(ObservableList<T> objectList) throws IOException;
    }

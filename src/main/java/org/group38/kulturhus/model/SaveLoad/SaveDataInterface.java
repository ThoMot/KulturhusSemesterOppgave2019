package org.group38.kulturhus.model.SaveLoad;

import org.group38.kulturhus.model.Event.EventNumberedSeating;

import java.io.IOException;
import java.util.List;

public interface SaveDataInterface {
    void writeObject(CsvBase object) throws IOException;

    }

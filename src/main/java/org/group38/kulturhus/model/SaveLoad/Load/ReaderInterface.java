package org.group38.kulturhus.model.SaveLoad.Load;

import java.io.IOException;
import java.util.ArrayList;

public interface ReaderInterface {

        <T> ArrayList<T> readObjects(String path) throws IOException, ClassNotFoundException;
    }



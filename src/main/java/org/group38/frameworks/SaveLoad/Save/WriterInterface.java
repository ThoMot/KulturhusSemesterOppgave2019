package org.group38.frameworks.SaveLoad.Save;

import java.io.IOException;
import java.util.ArrayList;

public interface WriterInterface {

    void writeObject(Object object, String filename) throws IOException;

    <T> void writeObjects(ArrayList<T> objectList, String filename) throws IOException;
    }

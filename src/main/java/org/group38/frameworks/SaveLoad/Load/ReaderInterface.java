package org.group38.frameworks.SaveLoad.Load;

import org.group38.frameworks.Exeptions.ParsingException;

import java.io.IOException;
import java.util.ArrayList;

public interface ReaderInterface {

        <T> ArrayList<T> readObjects(String path) throws IOException, ParsingException, ReflectiveOperationException, ClassNotFoundException;
    }



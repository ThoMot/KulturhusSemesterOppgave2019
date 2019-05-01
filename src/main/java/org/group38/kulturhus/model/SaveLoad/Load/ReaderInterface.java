package org.group38.kulturhus.model.SaveLoad.Load;

import org.group38.kulturhus.model.Exeptions.ParsingException;

import java.io.IOException;
import java.util.ArrayList;

public interface ReaderInterface {

        <T> ArrayList<T> readObjects(String path) throws IOException, ParsingException, ReflectiveOperationException, ClassNotFoundException;
    }



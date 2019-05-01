package org.group38.kulturhus.model.SaveLoad.Load;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReaderJOBJ implements ReaderInterface {

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<Object> readObjects(String path) throws IOException, ClassNotFoundException {
        File file = new File(path);
        if (file.length() == 0) {
            return new ArrayList<>();
        }

        ObjectInputStream is = new ObjectInputStream(new FileInputStream(path));

        ArrayList<Object> input = (ArrayList<Object>) is.readObject();
        is.close();
        return input;
    }
}

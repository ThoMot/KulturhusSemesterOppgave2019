package org.group38.kulturhus.model.SaveLoad;

import org.group38.kulturhus.model.Event.EventNumberedSeating;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveJobj implements SaveDataInterface{

    @Override
    public boolean writeEvent(EventNumberedSeating newSubstituteUser) {
        try {
            FileOutputStream fos = new FileOutputStream("newUser.jobj");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(newSubstituteUser);
            return true;

        } catch(IOException e) {
            e.printStackTrace(); // This should not happen, so we print debug information here.
            return false;
        }
    }
}



package org.group38.kulturhus.model.SaveLoad;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;

public class FileHandler {
    FileChooser fileChooser;
    File file;



    public String saveToFile(Window window){
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Save File");
        FileChooser.ExtensionFilter saveFilter = new FileChooser.ExtensionFilter("Save Files", "*.jobj", "*.csv");
        fileChooser.getExtensionFilters().add(saveFilter);
        file = fileChooser.showOpenDialog(window);

        if (file != null) {
            System.out.println(file.getName());
            return file.getName();
        } else return null;


    }


}

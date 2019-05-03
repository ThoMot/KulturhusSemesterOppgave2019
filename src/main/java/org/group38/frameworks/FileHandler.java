package org.group38.frameworks;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class FileHandler {
    FileChooser fileChooser;
    File file;

    /** chooseFile chooses which file of jobj and csv you want the system to load from */
    public String chooseFile(Window window){
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Files");
        FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("Files", "*.jobj", "*.csv");
        fileChooser.getExtensionFilters().add(fileFilter);
        file = fileChooser.showOpenDialog(window);

        if (file != null) {
            return file.toString();
        } else return null;
    }
}

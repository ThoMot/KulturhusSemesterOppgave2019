package org.group38.kulturhus.model.SaveLoad;

import javafx.beans.value.ObservableNumberValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.group38.frameworks.concurrency.WriterThreadRunner;
import org.group38.kulturhus.model.DefaultFiles;
import org.group38.kulturhus.model.EditedFiles;

import java.io.File;

public class FileHandler {
    FileChooser fileChooser;
    File file;



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

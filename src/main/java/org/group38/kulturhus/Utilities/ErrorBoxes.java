package org.group38.kulturhus.Utilities;

import javafx.scene.control.Alert;

public class ErrorBoxes {
    /** this errorbox is used for displaying different errors to the
     * user based on wrong input, empty fields that needs to be filled out,
     * duplicates, no selected object and no existing objects*/
    public static void errorBox(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setTitle(title);
        alert.show();
    }

}

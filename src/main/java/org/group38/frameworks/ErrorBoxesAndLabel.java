package org.group38.frameworks;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class ErrorBoxesAndLabel {
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

    /** the showLabel makes lables visible by default and makes the scene wait so the program doesn't crash */
    public static void showLabel(Label label){
        label.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
        visiblePause.setOnFinished(click -> label.setVisible(false));
        visiblePause.play();
    }
}

package org.group38.kulturhus.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import static org.group38.kulturhus.model.Kulturhus.getEvents;

public class ShowVenueController implements MainController {

    @FXML
    private TableView tableView;

    @FXML
    private void goToAddTicket(ActionEvent event){
        SceneManager.navigate(SceneName.ADDTICKET);
    }
    @FXML
    private void goToShowTicket(ActionEvent event){
        SceneManager.navigate(SceneName.SHOWTICKET);
    }
    @FXML
    private void goToAddEvent(ActionEvent event){
        SceneManager.navigate(SceneName.ADDEVENT);
    }
    @FXML
    private void goToShowVenue(ActionEvent event){
        SceneManager.navigate(SceneName.SHOWVENUE);
    }
    @FXML
    private void goToShowEvent(ActionEvent event){
        SceneManager.navigate(SceneName.SHOWEVENT);
    }


    @Override
    public void exit() {

    }
    public void initialize() {
//        tableView.getItems().setAll(getEvents());
//        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        tableView.getSelectionModel().selectFirst();

    }
}

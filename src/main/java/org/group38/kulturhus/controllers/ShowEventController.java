package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Kulturhus;

import java.io.IOException;
import java.util.ArrayList;

public class ShowEventController implements MainController{

    @FXML
    private MenuBar menuBar;
    @FXML
    TableView tableView;
    @FXML
    TableColumn<Event, String> nameColumn;
    @FXML
    TableColumn<Event, String> programColumn;
    @FXML
    TableColumn<Event, String> timeColumn;



    @FXML
    private void goToAddTicket(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.ADDTICKET);
    }

    @FXML
    private void goToShowTicket(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.SHOWTICKET);
    }


    @FXML
    private void goToAddEvent(ActionEvent event) throws IOException {
       SceneManager.navigate(SceneName.ADDEVENT);
    }

    @FXML
    private void goToShowVenue(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.SHOWVENUE);
    }
    public void initialize(){
        Kulturhus kulturhus = new Kulturhus();
        kulturhus.opprett(); //kun for å lage et event for å sjekke
        ArrayList<Event> list = new ArrayList<>(kulturhus.getEvents());
        ObservableList<Event> observableList2 = FXCollections.observableList(list);
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getEventName()));
        programColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getProgram()));
        timeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getDate().toString()));

        tableView.setItems(observableList2);
    }

    @Override
    public void exit() {

    }
}


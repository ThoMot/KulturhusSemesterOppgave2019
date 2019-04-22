package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;
import org.group38.kulturhus.model.Event.Event;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.group38.kulturhus.model.Kulturhus.getEvents;
import static org.group38.kulturhus.model.Kulturhus.opprett;

public class ShowEventController implements MainController{
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy");


    private List<Event> events;

    @FXML
    private MenuBar menuBar;
    @FXML
    private TableView<Event> eventsView;

    @FXML
    private Label eventName, eventDate;


    @FXML
    private TableColumn<Event,String> eventDateColumn = new TableColumn<>("Dato");
    @FXML
    private TableColumn<Event, String> eventTimeColumn = new TableColumn<>("Tid");
    @FXML
    private TableColumn<Event, String> eventNameColumn = new TableColumn<>("Arrangement");
    @FXML
    private TableColumn<Event,String> eventFacilityColumn = new TableColumn<>("Sted");



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
        opprett(); //kun for å lage et Event for å sjekke

        ArrayList<Event> list = new ArrayList<>(getEvents());
        ObservableList<Event> observableList2 = FXCollections.observableList(list);
        eventNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getEventName()));
        eventTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getTime().toString()));
        eventDateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getDate().toString()));
        eventFacilityColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFacility().getFacilityName()));

        eventsView.getItems().setAll(observableList2);
    }

    @Override
    public void exit() {

    }
}


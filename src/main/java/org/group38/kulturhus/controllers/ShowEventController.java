package org.group38.kulturhus.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;
import org.group38.kulturhus.model.Event.Event;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private TableColumn<Event,LocalDate> eventDateColumn = new TableColumn<>("Dato");
    @FXML
    private TableColumn<Event, LocalTime> eventTimeColumn = new TableColumn<>("Tid");
    @FXML
    private TableColumn<Event,String> eventNameColumn = new TableColumn<>("Arrangement");
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


        eventDateColumn.setCellValueFactory(data-> data.getValue().getEventInfo().dateProperty());
        eventTimeColumn.setCellValueFactory(data-> data.getValue().getEventInfo().timeProperty());
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<Event,String>("eventInfo"));
        eventFacilityColumn.setCellValueFactory(new PropertyValueFactory<Event,String>("facility"));

        eventsView.getItems().setAll(getEvents());
//        eventsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        eventsView.getSelectionModel().selectFirst();
    }

    @Override
    public void exit() {

    }
}


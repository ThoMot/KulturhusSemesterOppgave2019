package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.Event.Performer;
import org.group38.kulturhus.model.Facility;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Kulturhus;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ShowEventController implements MainController{


    private List<Event> events;

    @FXML
    private MenuBar menuBar;
    @FXML
    private ListView<Event> eventsView;

    @FXML
    private Label eventName, eventDate;

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
        kulturhus.getEvents();



        eventsView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observableValue, Event oldValue, Event newValue) {
                if(newValue != null){
                    Event event = eventsView.getSelectionModel().getSelectedItem();
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("d. MMMM yyyy");
                    //timeColumn.setText(df.format(event.getEventInfo().getDate()));
                    //nameColumn.setText(event.getEventInfo().getEventName());
                }
            }
        });

        eventsView.getItems().setAll(kulturhus.getEvents());
        eventsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        eventsView.getSelectionModel().selectFirst();
    }

    @Override
    public void exit() {

    }
}


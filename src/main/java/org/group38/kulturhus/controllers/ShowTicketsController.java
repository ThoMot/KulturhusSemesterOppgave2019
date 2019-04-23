package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.*;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.model.Kulturhus.*;

public class ShowTicketsController implements MainController {
    private ObservableList<Ticket> observableList;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private Event thisEvent;

    private List<Ticket> tickets;

    @FXML
    private TableView<Ticket> ticketsView;


    @FXML
    private MenuBar menuBar;

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



    @FXML
    private TableColumn<Ticket,String> phoneNumberColumn;

    @FXML
    private TableColumn<Ticket,String> seatRowColumn;

    @FXML
    private TableColumn<Ticket,String> seatNumberColumn;

    @FXML
    private Label eventName, eventDate, eventTime, eventProgram, eventPerformers, eventFacility;


    public void initialize() {
        setThisEvent(getSelectedEvent());
        ObservableList<Ticket> observableList2 = FXCollections.observableList(thisEvent.boughtTickets());

        eventName.setText(thisEvent.getEventInfo().getEventName());
        eventDate.setText(thisEvent.getEventInfo().getDate().format(dateFormatter));
        eventTime.setText(thisEvent.getEventInfo().getTime().format(timeFormatter));
        eventFacility.setText(thisEvent.getFacility().toString());
        eventPerformers.setText(thisEvent.getEventInfo().getPerformers());
        eventProgram.setText(thisEvent.getEventInfo().getProgram());

        phoneNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhonenumber()));
        seatRowColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRow().toString()));
        seatNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSeat().toString()));


        ticketsView.setItems(observableList2);
//        ticketsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        ticketsView.getSelectionModel().selectFirst();
    }

    public void setThisEvent(Event thisEvent) {
        this.thisEvent = thisEvent;
    }

    @Override
    public void exit() {

    }
}
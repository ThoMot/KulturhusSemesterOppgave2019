package org.group38.kulturhus.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.Event.Ticket;
import org.group38.kulturhus.model.Facility;
import org.group38.kulturhus.model.Kulturhus;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

public class ShowTicketsController implements MainController {

    private List<Ticket> tickets;

    @FXML
    private MenuBar menuBar;
    @FXML
    private TableView<Ticket> ticketsView;

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
//
//        //ticketsView.getItems().setAll(kulturhus.getEvents());
//        ticketsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        ticketsView.getSelectionModel().selectFirst();
    }


    @Override
    public void exit() {

    }
}

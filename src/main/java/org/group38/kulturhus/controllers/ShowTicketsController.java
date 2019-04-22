package org.group38.kulturhus.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.group38.kulturhus.model.Event.*;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.group38.kulturhus.model.Kulturhus.*;

public class ShowTicketsController implements MainController {

    private List<Ticket> tickets;

    @FXML
    private TableView<Event> ticketsView;


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
    private
    TableColumn<Event,LocalDate> eventDateColumn;

    @FXML
    private
    TableColumn<Event, LocalTime> eventTimeColumn;

    @FXML
    private
    TableColumn<Event,String> eventNameColumn = new TableColumn<>("Arrangement");

    @FXML
    private
    TableColumn<EventNumberedSeating,String> phoneNumberColumn = new TableColumn<>("Telefonnummer");


    @FXML
    private TableColumn<Ticket, LocalDate> dateColumn ;

    public void initialize(){

        opprett(); //kun for å lage et Event for å sjekke

//        eventDateColumn.setCellValueFactory(data-> data.getValue().getEventInfo().dateProperty());
//        eventTimeColumn.setCellValueFactory(data-> data.getValue().getEventInfo().timeProperty());
//        //Denne er det noe feil med
//        eventNameColumn.setCellValueFactory(new PropertyValueFactory<Event,String>("eventInfo"));
//        //phoneNumberColumn.setCellValueFactory(data->data.getValue().);


        ticketsView.getItems().setAll(getEvents());
//        ticketsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        ticketsView.getSelectionModel().selectFirst();
    }


    @Override
    public void exit() {

    }
}

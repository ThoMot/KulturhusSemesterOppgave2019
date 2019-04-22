package org.group38.kulturhus.controllers;

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

import static org.group38.kulturhus.model.Kulturhus.*;

public class ShowTicketsController implements MainController {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. MMMM yyyy");

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


    @FXML private TableColumn<Event,String> eventNameColumn = new TableColumn<>("Arrangement");

    @FXML
    private
    TableColumn<Ticket,String> phoneNumberColumn = new TableColumn<>("Telefonnummer");

    @FXML
    private Label eventName, eventDate, eventTime, eventProgram, eventPerformers;

    @FXML
    private TableColumn<Event,String> eventDateColumn, eventTimeColumn;

    @FXML
    private TableColumn<Event,String> eventFacilityColumn;


    public void initialize(){

        Facility facility = new Facility("Sal 1", "Kinosal", 10, 20);
        LocalDate d = LocalDate.of(2019, Month.APRIL, 22);
        LocalTime t = LocalTime.of(22,00);
        ContactPerson contactPerson = new ContactPerson("Martina", "Førre", new ContactInfo("martina@gmail.com", "11223344"));
        EventInfo eventInfo = new EventInfo("Max Manus", "film", d,t);
        EventNumberedSeating maxManus=new EventNumberedSeating(contactPerson, facility, "Ane Dahl Torp", 100, eventInfo);

        Facility facility1 = new Facility("Sal 2", "Teatersal", 10, 12);
        LocalDate d1 = LocalDate.of(2019, Month.MAY, 10);
        LocalTime t1 = LocalTime.of(18,00);
        ContactPerson contactPerson1 = new ContactPerson("Tor", "Mare", new ContactInfo("mail@gmail.com", "22334455"));
        EventInfo eventInfo1 = new EventInfo("Åpning", "Åpning av kinosalen", d1, t1);
        EventNumberedSeating event2 =new EventNumberedSeating(contactPerson1, facility1, "Sjefen", 100, eventInfo1);


        maxManus.BuyTicket(1,2,"11223344");
        maxManus.BuyTicket(2,3,"11223344");
        maxManus.BuyTicket(1,4,"56743827");

        ObservableList<Ticket> observableList2 = FXCollections.observableList(maxManus.boughtTickets());

        eventName.setText(maxManus.getEventInfo().getEventName());
 //       eventDate.setText(maxManus.getEventInfo().getDate().toString());

//        eventNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getEventName()));
//        eventTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getTime().toString()));
//        eventDateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getDate().toString()));
       phoneNumberColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getPhonenumber()));



        ticketsView.setItems(observableList2);
//        ticketsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        ticketsView.getSelectionModel().selectFirst();
    }


    @Override
    public void exit() {

    }
}

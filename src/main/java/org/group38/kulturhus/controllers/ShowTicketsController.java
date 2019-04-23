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


    public void initialize(){

        Facility facility = new Facility("Sal 1", "Kinosal", 10, 20);
        LocalDate d = LocalDate.of(2019, Month.APRIL, 22);
        LocalTime t = LocalTime.of(22,00);
        ContactPerson contactPerson = new ContactPerson("Martina", "Førre", new ContactInfo("martina@gmail.com", "11223344"));
        EventInfo eventInfo = new EventInfo("Max Manus", "film","Ingen", d,t);
        EventNumberedSeating maxManus=new EventNumberedSeating(contactPerson, facility, 100, eventInfo);

        Facility facility1 = new Facility("Sal 2", "Teatersal", 10, 12);
        LocalDate d1 = LocalDate.of(2019, Month.MAY, 10);
        LocalTime t1 = LocalTime.of(18,00);
        ContactPerson contactPerson1 = new ContactPerson("Tor", "Mare", new ContactInfo("mail@gmail.com", "22334455"));
        EventInfo eventInfo1 = new EventInfo("Åpning", "Åpning av kinosalen","Sjefen", d1, t1);
        EventNumberedSeating event2 =new EventNumberedSeating(contactPerson1, facility1, 100, eventInfo1);


        maxManus.BuyTicket(1,2,"11223344");
        maxManus.BuyTicket(2,3,"11223344");
        maxManus.BuyTicket(3,4,"56743827");

        ObservableList<Ticket> observableList2 = FXCollections.observableList(maxManus.boughtTickets());

        eventName.setText(maxManus.getEventInfo().getEventName());
        eventDate.setText(maxManus.getEventInfo().getDate().format(dateFormatter));
        eventTime.setText(maxManus.getEventInfo().getTime().format(timeFormatter));
        eventFacility.setText(maxManus.getFacility().toString());
        eventPerformers.setText(maxManus.getEventInfo().getPerformers());
        eventProgram.setText(maxManus.getEventInfo().getProgram());

        phoneNumberColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getPhonenumber()));
        seatRowColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getRow().toString()));
        seatNumberColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getSeat().toString()));


        ticketsView.setItems(observableList2);
//        ticketsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        ticketsView.getSelectionModel().selectFirst();
        System.out.println(getSelectedEvent());
    }


    @Override
    public void exit() {

    }
}

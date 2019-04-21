package org.group38.kulturhus.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.group38.kulturhus.model.Event.*;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.group38.kulturhus.model.Kulturhus.getTickets;
import static org.group38.kulturhus.model.Kulturhus.opprett;

public class ShowTicketsController implements MainController {

    private List<Ticket> tickets;
    DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @FXML
    private ListView<Ticket> ticketsView;


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

    private TicketData data;

    @FXML
    private TableColumn<Ticket, LocalDate> dateColumn ;

    public void initialize(){

        opprett(); //kun for å lage et Event for å sjekke

//        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
//
//        data = new TicketData();
//        //data.loadHolidays();
//        ticketsView.setItems(data.getTickets());
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        dateColumn.setCellFactory(column -> new TableCell<Ticket, LocalDate>() {
//            @Override
//            protected void updateItem(LocalDate date, boolean empty) {
//                super.updateItem(date, empty);
//                if (empty) {
//                    setText("");
//                } else {
//                    setText(formatter.format(date));
//                }
//            }
//        });

//        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
//
        ticketsView.getItems().setAll(getTickets());
        ticketsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ticketsView.getSelectionModel().selectFirst();
    }


    @Override
    public void exit() {

    }
}

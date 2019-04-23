package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import org.group38.kulturhus.model.Event.*;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.model.Kulturhus.*;

public class ShowTicketsController implements MainController {
    private ObservableList<Ticket> observableList;
    private static Ticket selectedTicket;

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
    private TableColumn<ShowTicketsController,Button> deleteTicketColumn;

    @FXML
    private Label eventName, eventDate, eventTime, eventProgram, eventPerformers, eventFacility;


    public void initialize() {
        opprett();
        initCols();
        loadData();
        editableCols();
    }

    private void loadData(){
        observableList = FXCollections.observableList(getSelectedEvent().boughtTickets());
        ticketsView.setItems(observableList);
    }

    private void initCols(){
        eventName.setText(getSelectedEvent().getEventInfo().getEventName());
        eventDate.setText(getSelectedEvent().getEventInfo().getDate().format(dateFormatter));
        eventTime.setText(getSelectedEvent().getEventInfo().getTime().format(timeFormatter));
        eventFacility.setText(getSelectedEvent().getFacility().toString());
        eventPerformers.setText(getSelectedEvent().getEventInfo().getPerformers());
        eventProgram.setText(getSelectedEvent().getEventInfo().getProgram());

        phoneNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhonenumber()));
        seatRowColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRow().toString()));
        seatNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSeat().toString()));
    }

    private void editableCols(){
        ticketsView.setEditable(true);

        phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNumberColumn.setOnEditCommit((TableColumn.CellEditEvent<Ticket, String> t) -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPhonenumber(t.getNewValue()));

        seatRowColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        seatRowColumn.setOnEditCommit((TableColumn.CellEditEvent<Ticket, String> t) -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setRow(Integer.parseInt(t.getNewValue())));

        seatNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        seatNumberColumn.setOnEditCommit((TableColumn.CellEditEvent<Ticket, String> t) -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSeat(Integer.parseInt(t.getNewValue())));

    }


    public void deleteRow(ActionEvent ticket){
        //vil du virkelig slette denne billetten?
        observableList.remove(ticketsView.getSelectionModel().getSelectedItem());
    }




    @Override
    public void exit() {

    }
}
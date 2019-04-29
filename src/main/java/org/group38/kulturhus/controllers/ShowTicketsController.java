package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Popup;
import org.group38.kulturhus.model.Event.*;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.group38.kulturhus.ErrorBoxes.errorNoMarkedEvent;
import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.controllers.ShowEventController.setSelectedEvent;
import static org.group38.kulturhus.model.Kulturhus.*;

public class ShowTicketsController implements MainController {
    private ObservableList<Ticket> observableList;
    private static Ticket selectedTicket;
    private Event thisEvent;

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
        setSelectedEvent(null);
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
        setThisEvent(getSelectedEvent());
        initCols();
        loadData();
        editableCols();

    }
    private void setThisEvent(Event thisEvent){
        this.thisEvent=thisEvent;
    }

    private void loadData(){
        observableList = FXCollections.observableList(thisEvent.boughtTickets());
        ticketsView.setItems(observableList);
    }

    private void initCols(){
        eventName.setText(thisEvent.getEventInfo().getEventName());
        eventDate.setText(thisEvent.getEventInfo().getDate().format(dateFormatter));
        eventTime.setText(thisEvent.getEventInfo().getTime().format(timeFormatter));
        eventFacility.setText(thisEvent.getFacility().toString());
        eventPerformers.setText(thisEvent.getEventInfo().getPerformers());
        eventProgram.setText(thisEvent.getEventInfo().getProgram());

        phoneNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhonenumber()));
        seatRowColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRow().toString()));
        seatNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSeat().toString()));
    }

    private void editableCols(){
        ticketsView.setEditable(true);
            phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());


            try{
                phoneNumberColumn.setOnEditCommit((TableColumn.CellEditEvent<Ticket, String> t) -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPhonenumber(t.getNewValue()));
                phoneNumberColumn.getOnEditCommit();
                System.out.println("billett opprettet");
            } catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.show();
                System.out.println("Feil");
            }

        seatRowColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        seatRowColumn.setOnEditCommit((TableColumn.CellEditEvent<Ticket, String> t) -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setRow(Integer.parseInt(t.getNewValue())));

        seatNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        seatNumberColumn.setOnEditCommit((TableColumn.CellEditEvent<Ticket, String> t) -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSeat(Integer.parseInt(t.getNewValue())));

    }


    public void deleteRow(ActionEvent ticket){
        if(ticketsView.getSelectionModel().getSelectedItem() == null){
            errorNoMarkedEvent();

        } else{
            Alert mb = new Alert(Alert.AlertType.CONFIRMATION);
            mb.setTitle("Bekreft");
            mb.setHeaderText("Du har trykket slett på "+ ticketsView.getSelectionModel().getSelectedItem().getPhonenumber());
            mb.setContentText("Ønsker du virkerlig å slette denne billetten?");
            mb.showAndWait().ifPresent(response -> {
                if(response==ButtonType.OK){
                    observableList.remove(ticketsView.getSelectionModel().getSelectedItem());
                    if(thisEvent instanceof EventNumberedSeating){
                        ((EventNumberedSeating)thisEvent).setTickets(new ArrayList<>(observableList));
                    }
                }
            });
        }

    }

    public void showErrorMessage(){
        Alert mb = new Alert(Alert.AlertType.INFORMATION);
        mb.setHeaderText("Det er ingen rader som er markert");
        mb.setTitle("Feil");
        mb.setContentText("Vennligst marker en rad i tabellen");
        mb.show();
    }

    public void goToCreateTicket(ActionEvent ticket){
        if(ticketsView.getSelectionModel().getSelectedItem()==null){
            errorNoMarkedEvent();
        }
        else{
            setSelectedTicket(ticketsView.getSelectionModel().getSelectedItem());
            SceneManager.navigate(SceneName.ADDTICKET);
        }
    }

    //**setter and getter for the selectedEvent
    public static void setSelectedTicket(Ticket selectedTicket) {
        ShowTicketsController.selectedTicket = selectedTicket;
    }
    public static Ticket getSelectedTicket() {
        return selectedTicket;
    }



    @Override
    public void exit() {

    }
}
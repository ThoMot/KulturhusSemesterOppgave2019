package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.group38.kulturhus.model.Event.*;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.IOException;

import static org.group38.kulturhus.Utilities.ErrorBoxesAndLabel.errorBox;
import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.controllers.ShowEventController.setSelectedEvent;

public class ShowTicketsController implements MainController {
    private ObservableList<Ticket> observableList;
    private Ticket thisTicket = getSelectedTicket();
    private static Ticket selectedTicket;
    private Event thisEvent;

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    private TableView<Ticket> ticketsView;

    @FXML
    private TextField filtering;
    @FXML
    private TextArea eventPerformers, eventProgram;

    @FXML
    private TableColumn<Ticket,String> phoneNumberColumn, seatRowColumn, seatNumberColumn;

    @FXML
    private Label eventName, eventDate, eventTime, eventFacility;

    /** goTo.. methods are for opening different scenes, and setting the selected event if needed in the next scene.
     *it also shows an errormessage in an alert if there is no selected event*/
    @FXML
    private void goToShowEvent(ActionEvent event){
        SceneManager.navigate(SceneName.SHOWEVENT);
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
    public void goToBuyTicket(ActionEvent event) throws IOException {
        setSelectedTicket(thisTicket);
        setSelectedEvent(getSelectedEvent());
        SceneManager.navigate(SceneName.ADDTICKET);
    }

    /** gotoChangeTicket() is run if user selects item and selects add ticket.
     * Opens new window to change a ticket if item is selected */
    @FXML
    public void goToChangeTicket(ActionEvent event){
        if(ticketsView.getSelectionModel().getSelectedItem()==null){
            errorBox("Feil", "Det er ingen billett som er markert", "Vennligst marker en kontaktperson du vil redigere");
        }
        else{
            setSelectedTicket(ticketsView.getSelectionModel().getSelectedItem());
            SceneManager.navigate(SceneName.ADDTICKET);
        }
    }

    /** initialize() runs when scene is opened. This method runs the
     *initCols method and the loadData method. */
    public void initialize() {
        setThisEvent(getSelectedEvent());
        initCols();
        loadData();
        filtering();
    }

    /** setThisEvent() registers the Ticket on the selected Event */
    private void setThisEvent(Event thisEvent){
        this.thisEvent=thisEvent;
    }

    /** loadData() adds all the tickets from the list in Kulturhus into TableView */
    private void loadData(){
        observableList = FXCollections.observableList(thisEvent.getTickets());
        ticketsView.setItems(observableList);
    }

    /** initCols() inputs information gathered in load to place where in scene/fxml-file */
    private void initCols(){
        eventName.setText(thisEvent.getEventInfo().getEventName());
        eventDate.setText(thisEvent.getEventInfo().getDate().format(dateFormatter));
        eventTime.setText(thisEvent.getEventInfo().getTime().format(timeFormatter));
        eventFacility.setText(thisEvent.getFacility().toString());
        eventPerformers.setText(thisEvent.getEventInfo().getPerformer());
        eventProgram.setText(thisEvent.getEventInfo().getProgram());

        phoneNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhoneNumber()));
        seatRowColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSeatRow().toString()));
        seatNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSeatNumber().toString()));
    }

    /** deleteRow() removes item from TicketsList if item from TableView is selected*/
    public void deleteRow(ActionEvent ticket){
        if(ticketsView.getSelectionModel().getSelectedItem() == null){
            errorBox("Feil", "Det er ingen billett som er markert", "Vennligst marker en kontaktperson du vil redigere");

        } else{
            Alert mb = new Alert(Alert.AlertType.CONFIRMATION);
            mb.setTitle("Bekreft");
            mb.setHeaderText("Du har trykket slett på "+ ticketsView.getSelectionModel().getSelectedItem().getPhoneNumber());
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

    /**the filtering method is run when the scene is opened and adds a listener to the TextField filtering,
     * it takes the input from the user and compares to the TableView items and hides objects that does not
     * contain the input from the user*/
    private void filtering(){
        FilteredList<Ticket> filteredList = new FilteredList<>(observableList);
        filtering.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredList.setPredicate(ticket -> {
                if(newValue ==null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(ticket.getPhoneNumber().contains(lowerCaseFilter)){
                    return true;
                }
                else if(ticket.getSeatRow().toString().contains(lowerCaseFilter)){
                    return true;
                }
                else if(ticket.getSeatNumber().toString().contains(lowerCaseFilter)){
                    return true;
                }
                return false;
            });
        });
        SortedList<Ticket> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(ticketsView.comparatorProperty());
        ticketsView.setItems(sortedList);
    }

    /** Getter and setter for the selected ticket from Event*/
    public static void setSelectedTicket(Ticket selectedTicket){
        ShowTicketsController.selectedTicket=selectedTicket;
    }
    public static Ticket getSelectedTicket(){
        return selectedTicket;
    }


    @Override
    public void exit() {
    }
}
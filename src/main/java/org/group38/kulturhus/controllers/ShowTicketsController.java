package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.group38.frameworks.concurrency.ReaderThreadRunner;
import org.group38.frameworks.concurrency.WriterThreadRunner;
import org.group38.kulturhus.model.FilePaths.EditedFiles;
import org.group38.kulturhus.model.Event.*;
import org.group38.frameworks.sceneHandling.SceneManager;
import org.group38.frameworks.sceneHandling.SceneName;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.group38.frameworks.ErrorBoxesAndLabel.errorBox;
import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.controllers.ShowEventController.setSelectedEvent;
import static org.group38.kulturhus.model.Kulturhus.getTickets;

public class ShowTicketsController implements MainController {
    private File ticketFile = new File(EditedFiles.getActiveTicketFile());
    private ObservableList<Ticket> observableList;
    private Ticket thisTicket = getSelectedTicket();
    private static Ticket selectedTicket;
    private Event thisEvent;
    private SceneManager sceneManager = SceneManager.INSTANCE;
    private String fileName;

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy");

    @FXML private TableView<Ticket> ticketsView;
    @FXML private TextField filtering;
    @FXML private TextArea eventPerformers, eventProgram;
    @FXML private TableColumn<Ticket,String> phoneNumberColumn, seatRowColumn, seatNumberColumn;
    @FXML private Label eventName, eventDate, eventTime, eventFacility;

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
        setSelectedTicket(null);
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

    @Override
    public void refresh(){
        thisEvent.getTickets().clear();
        fileName = EditedFiles.getActiveTicketFile();

        try {
            getTickets().addAll(ReaderThreadRunner.startReader(fileName));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if(!getTickets().isEmpty()) {
            for (Ticket ticket : getTickets()) {
                if (ticket.getEventId().equals(thisEvent.getEventId())) {
                    thisEvent.getTickets().add(ticket);
                }
            }
        }
    }

    /** setThisEvent() registers the Ticket on the selected Event */
    private void setThisEvent(Event thisEvent){
        this.thisEvent=thisEvent;
    }

    /** loadData() adds all the tickets from the list in Kulturhus into TableView */
    private void loadData(){

        fileName = EditedFiles.getActiveTicketFile();

        try {
            getTickets().addAll(ReaderThreadRunner.startReader(fileName));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if(!getTickets().isEmpty()) {
            for (Ticket ticket : getTickets()) {
                if (ticket.getEventId().equals(thisEvent.getEventId())) {
                    thisEvent.getTickets().add(ticket);
                }
            }
        }


        observableList = FXCollections.observableList(thisEvent.getTickets());
        ticketsView.setItems(observableList);
    }

    /** initCols() inputs information gathered in load to place where in scene/fxml-file */
    private void initCols(){
        eventName.setText(thisEvent.getEventInfo().getEventName());
        eventDate.setText(thisEvent.getEventInfo().getDate().format(dateFormatter));
        eventTime.setText(thisEvent.getEventInfo().getTime().toString());
        eventFacility.setText(thisEvent.getFacility().toString());
        eventPerformers.setText(thisEvent.getEventInfo().getPerformer());
        eventProgram.setText(thisEvent.getEventInfo().getProgram());

        phoneNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhonenumber()));
        seatRowColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRow().toString()));
        seatNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSeat().toString()));
    }

    /** deleteRow() removes item from TicketsList if item from TableView is selected*/
    public void deleteRow(ActionEvent ticket){
        if(ticketsView.getSelectionModel().getSelectedItem() == null){
            errorBox("Feil", "Det er ingen billett som er markert", "Vennligst marker en kontaktperson du vil redigere");

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

                        ticketFile.delete();
                        try {
                            WriterThreadRunner.WriterThreadRunner(thisEvent.getTickets(), EditedFiles.getActiveTicketFile());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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

                if(ticket.getPhonenumber().contains(lowerCaseFilter)){
                    return true;
                }
                else if(ticket.getRow().toString().contains(lowerCaseFilter)){
                    return true;
                }
                else if(ticket.getSeat().toString().contains(lowerCaseFilter)){
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
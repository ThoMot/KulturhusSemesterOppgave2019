package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;
import org.group38.kulturhus.model.Event.Event;

import static org.group38.kulturhus.ErrorBoxes.errorNoMarkedEvent;
import static org.group38.kulturhus.controllers.ShowTicketsController.setSelectedTicket;
import static org.group38.kulturhus.model.Kulturhus.*;

public class ShowEventController implements MainController{
    //data field
    private ObservableList<Event> observableList;
    private static Event selectedEvent;

    @FXML private TableView<Event> eventsView;
    @FXML private TableColumn<Event,String> eventDateColumn, eventTimeColumn, eventNameColumn, eventFacilityColumn;

    /*
    Methods for opening different scenes, and setting the selected event if needed in the next scene.
    it also shows an errormessage in an alert if there is no selected event
     */
    @FXML
    private void goToAddEvent(ActionEvent event){ SceneManager.navigate(SceneName.ADDEVENT); }
    @FXML
    private void goToShowVenue(ActionEvent event){ SceneManager.navigate(SceneName.SHOWVENUE); }
    public void goToVisInfo(ActionEvent event){
        if(eventsView.getSelectionModel().getSelectedItem()==null){
            errorNoMarkedEvent();
        }
        else {
            setSelectedEvent(eventsView.getSelectionModel().getSelectedItem());
            SceneManager.navigate(SceneName.SHOWTICKET);
        }
    }
    public void goToBuyTicket(ActionEvent event){
        if(eventsView.getSelectionModel().getSelectedItem()==null){
            errorNoMarkedEvent();
        }
        else{
            setSelectedTicket(null);
            setSelectedEvent(eventsView.getSelectionModel().getSelectedItem());
            SceneManager.navigate(SceneName.ADDTICKET);
        }
    }
    public void goToCreateEvent(ActionEvent event){
        if(eventsView.getSelectionModel().getSelectedItem()==null){
            errorNoMarkedEvent();
        }
        else{
            setSelectedEvent(eventsView.getSelectionModel().getSelectedItem());
            SceneManager.navigate(SceneName.ADDEVENT);
        }
    }
    /*
    The initialize method runs when the scene is opened. This method runs the
    initCols method and the loadData method. It also sets the selectedEvent to null
    for not bringing an old selected item to the next scene.
     */
    public void initialize(){
        initCols();
        loadData();
        setSelectedEvent(null);

    }
    //** initCols method is deciding what the table columns shall contain
    private void initCols(){
        eventNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getEventName()));
        eventTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getTime().toString()));
        eventDateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getDate().toString()));
        eventFacilityColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFacility().getFacilityName()));
    }

    //**The loadData method adds all the events from the list in Kulturhus to the tableview
    private void loadData(){
        observableList = FXCollections.observableList(getEvents());
        eventsView.setItems(observableList);
    }
    /*
    The deleteRow method checks if an event is selected, or else shows an errorMessage.
    If a field is selected, shows a confirmation alert, and then deletes the event if the
    user presses the ok button.
     */
    public void deleteRow(ActionEvent event){
        if(eventsView.getSelectionModel().getSelectedItem()==null){
            errorNoMarkedEvent();
        }
        else{
            Alert mb = new Alert(Alert.AlertType.CONFIRMATION);
            mb.setTitle("Bekreft");
            mb.setHeaderText("Du har trykket slett på "+ eventsView.getSelectionModel().getSelectedItem().getEventInfo().getEventName());
            mb.setContentText("Ønsker du virkerlig å slette dette arrangementet?");
            mb.showAndWait().ifPresent(response -> {
                if(response==ButtonType.OK){
                    observableList.remove(eventsView.getSelectionModel().getSelectedItem());
                }
            });
        }
    }
    //**setter and getter for the selectedEvent
    public static void setSelectedEvent(Event selectedEvent) {
        ShowEventController.selectedEvent = selectedEvent;
    }
    public static Event getSelectedEvent() {
        return selectedEvent;
    }

    @Override
    public void exit() {
    }

}


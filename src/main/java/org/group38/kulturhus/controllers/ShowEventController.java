package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.layout.VBox;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;
import org.group38.kulturhus.model.Event.Event;

import static org.group38.kulturhus.ErrorBoxes.errorNoMarkedEvent;
import static org.group38.kulturhus.model.Kulturhus.*;

public class ShowEventController implements MainController{
    private ObservableList<Event> observableList;
    private static Event selectedEvent;

    @FXML private TableView<Event> eventsView;
    @FXML private TableColumn<Event,String> eventDateColumn, eventTimeColumn, eventNameColumn, eventFacilityColumn;
    @FXML
    private void goToAddEvent(ActionEvent event){
       SceneManager.navigate(SceneName.ADDEVENT);
    }
    @FXML
    private void goToShowVenue(ActionEvent event){
        SceneManager.navigate(SceneName.SHOWVENUE);
    }
    public void initialize(){
        initCols();
        loadData();
        setSelectedEvent(null);

    }
    private void initCols(){
        eventNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getEventName()));
        eventTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getTime().toString()));
        eventDateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getDate().toString()));
        eventFacilityColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFacility().getFacilityName()));
    }

    private void loadData(){
        observableList = FXCollections.observableList(getEvents());
        eventsView.setItems(observableList);
    }
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

    //metode som bytter scene til visinfo og tar med seg eventet som er trykket på
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


package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;
import org.group38.kulturhus.model.Event.Event;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.group38.kulturhus.model.Kulturhus.getEvents;
import static org.group38.kulturhus.model.Kulturhus.opprett;

public class ShowEventController implements MainController{
    private ObservableList<Event> observableList;
    private static Event selectedEvent;

    @FXML private TableView<Event> eventsView;
    @FXML private TableColumn<Event,String> eventDateColumn, eventTimeColumn, eventNameColumn, eventFacilityColumn;

    @FXML
    public void goToAddEvent(ActionEvent event){
       SceneManager.navigate(SceneName.ADDEVENT);
    }
    @FXML
    public void goToShowVenue(ActionEvent event){
        SceneManager.navigate(SceneName.SHOWVENUE);
    }
    public void initialize(){
        opprett(); //kun for å lage et Event for å sjekke MIDLERTIDIG
        initCols();
        loadData();
        setSelectedEvent(null);
//        editableCols();
    }
    private void initCols(){
        eventNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getEventName()));
        eventTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getTime().toString()));
        eventDateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getDate().toString()));
        eventFacilityColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFacility().getFacilityName()));
    }
//    private void editableCols(){
//        eventsView.setEditable(true);
//
//        eventNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        eventNameColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> e) -> e.getTableView().getItems().get(e.getTablePosition().getRow()).getEventInfo().setEventName(e.getNewValue()));
//
//        eventDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        eventDateColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> e) -> e.getTableView().getItems().get(e.getTablePosition().getRow()).getEventInfo().setDate(StringtoLocalDate(e.getNewValue())));
//
//        eventTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        eventTimeColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> e) -> e.getTableView().getItems().get(e.getTablePosition().getRow()).getEventInfo().setTime(StringtoLocalTime(e.getNewValue())));
//
//        eventFacilityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        eventFacilityColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> e) -> e.getTableView().getItems().get(e.getTablePosition().getRow()).getFacility().setFacilityName(e.getNewValue()));
//    }

    private void loadData(){
        observableList = FXCollections.observableList(getEvents());
        eventsView.setItems(observableList);
    }
    public void deleteRow(ActionEvent event){
        if(eventsView.getSelectionModel().getSelectedItem()==null){
            showErrorMessage();
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
            showErrorMessage();
        }
        else {
            setSelectedEvent(eventsView.getSelectionModel().getSelectedItem());
            SceneManager.navigate(SceneName.SHOWTICKET);
        }
    }
    public void goToBuyTicket(ActionEvent event){
        if(eventsView.getSelectionModel().getSelectedItem()==null){
            showErrorMessage();
        }
        else{
            setSelectedEvent(eventsView.getSelectionModel().getSelectedItem());
            SceneManager.navigate(SceneName.ADDTICKET);
        }
    }
    public void goToCreateEvent(ActionEvent event){
        if(eventsView.getSelectionModel().getSelectedItem()==null){
            showErrorMessage();
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
    //method for opening an error if there is no selected fields
    public void showErrorMessage(){
        Alert mb = new Alert(Alert.AlertType.INFORMATION);
        mb.setHeaderText("Det er ingen rader som er markert");
        mb.setTitle("Feil");
        mb.setContentText("Vennligst marker en rad i tabellen");
        mb.show();
    }

    @Override
    public void exit() {
    }

}


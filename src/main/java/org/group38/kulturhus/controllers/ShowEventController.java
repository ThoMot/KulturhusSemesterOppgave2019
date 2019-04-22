package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;
import org.group38.kulturhus.model.Event.Event;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.group38.kulturhus.Utilities.Converters.StringtoLocalDate;
import static org.group38.kulturhus.Utilities.Converters.StringtoLocalTime;
import static org.group38.kulturhus.model.Kulturhus.getEvents;
import static org.group38.kulturhus.model.Kulturhus.opprett;

public class ShowEventController implements MainController{
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy");

    private ObservableList<Event> observableList;
    private List<Event> events;

    @FXML
    private MenuBar menuBar;
    @FXML
    private TableView<Event> eventsView;

    @FXML
    private Label eventName, eventDate;


    @FXML
    private TableColumn<Event,String> eventDateColumn, eventTimeColumn, eventNameColumn, eventFacilityColumn;

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
    public void initialize(){
        opprett(); //kun for å lage et Event for å sjekke
        initCols();
        loadData();
        editableCols();
    }
    private void initCols(){
        eventNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getEventName()));
        eventTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getTime().toString()));
        eventDateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventInfo().getDate().toString()));
        eventFacilityColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFacility().getFacilityName()));
    }
    private void editableCols(){
        eventsView.setEditable(true);

        eventNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        eventNameColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> e) -> e.getTableView().getItems().get(e.getTablePosition().getRow()).getEventInfo().setEventName(e.getNewValue()));

        eventDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        eventDateColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> e) -> e.getTableView().getItems().get(e.getTablePosition().getRow()).getEventInfo().setDate(StringtoLocalDate(e.getNewValue())));

        eventTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        eventTimeColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> e) -> e.getTableView().getItems().get(e.getTablePosition().getRow()).getEventInfo().setTime(StringtoLocalTime(e.getNewValue())));

        eventFacilityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        eventFacilityColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> e) -> e.getTableView().getItems().get(e.getTablePosition().getRow()).getFacility().setFacilityName(e.getNewValue()));
    }

    private void loadData(){
        observableList = FXCollections.observableList(getEvents());
        eventsView.setItems(observableList);
    }
    public void deleteRow(ActionEvent event){
        //vil du virkelig slette dette eventet?
        observableList.remove(eventsView.getSelectionModel().getSelectedItem());
    }

    @Override
    public void exit() {

    }
}


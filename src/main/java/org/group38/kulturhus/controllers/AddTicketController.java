package org.group38.kulturhus.controllers;


import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.Flow;

import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.controllers.ShowEventController.setSelectedEvent;

public class AddTicketController implements MainController{
    private Event thisEvent;

    @FXML
    private Label dateTime;
    @FXML
    private Label eventTitle;

    @FXML
    private void goToAddEvent(ActionEvent event) throws IOException {
        setSelectedEvent(null);
        SceneManager.navigate(SceneName.ADDEVENT);
    }

    @FXML
    private void goToShowTicket(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.SHOWTICKET);
    }

    @FXML
    private void goToShowEvent(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.ADDEVENT);
    }

    @FXML
    private void goToShowVenue(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.SHOWVENUE);
    }


    public void setThisEvent(Event thisEvent) {
        this.thisEvent = thisEvent;
    }
    public void setEventInfo(){
        eventTitle.setText(thisEvent.getEventInfo().getEventName());
        dateTime.setText(thisEvent.getEventInfo().getDate().toString()+", "+thisEvent.getEventInfo().getTime().toString());
    }

    @FXML
    private TextArea seatsFlowPane;

    public void setAllSeats(){
        Integer row = getSelectedEvent().getFacility().getRows();
        Integer seatNumber = getSelectedEvent().getFacility().getColumns();
        Button seat = new Button();

        for(int i = 0; i< row;i++){
            //seatsFlowPane.getChildren().add(new Button("hello"));
        }

        System.out.println(getSelectedEvent().allSeats());
    }

    public void initialize() {
    setThisEvent(getSelectedEvent());
    setEventInfo();

    //seatsFlowPane.setText(thisEvent.allSeats());

        System.out.println(getSelectedEvent().allSeats());

    }

    @Override
    public void exit() {

    }
}

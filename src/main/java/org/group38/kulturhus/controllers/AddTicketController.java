package org.group38.kulturhus.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.Event.Ticket;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;


import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.controllers.ShowEventController.setSelectedEvent;

public class AddTicketController implements MainController{
    private Event thisEvent;
    private Event event = getSelectedEvent();

    @FXML
    private Label dateTime, eventTitle, ticketPrice;

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
        SceneManager.navigate(SceneName.SHOWEVENT);
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
        ticketPrice.setText(String.valueOf(thisEvent.getTicketPrice()));
    }

    @FXML
    private Label seatRowInfoText;
    @FXML
    private TextField row;

    @FXML
    private TextField seatNumber;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Label seatsFlowPane;

    public void showFreeSeats(){
        seatsFlowPane.setText(getSelectedEvent().freeSeats());
    }

    public void buyTicket(){
        if(event instanceof EventNumberedSeating){
            ((EventNumberedSeating) event).buyTicket(Integer.parseInt(row.getText()),Integer.parseInt(seatNumber.getText()),phoneNumber.getText());
        }
        else if(event instanceof EventFreeSeating){
            ((EventFreeSeating) event).buyTicket(phoneNumber.getText());
        }
        System.out.println(getSelectedEvent().boughtTickets());
    }

    public void initialize() {
    setThisEvent(getSelectedEvent());
    setEventInfo();
    showFreeSeats();

    if(event instanceof EventFreeSeating){
        seatRowInfoText.setVisible(false);
        row.setVisible(false);
        seatNumber.setVisible(false);
        seatsFlowPane.setVisible(false);
    }

    }

    @Override
    public void exit() {

    }
}

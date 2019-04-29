package org.group38.kulturhus.controllers;


import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.Event.Ticket;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


import static org.group38.kulturhus.ErrorBoxes.*;
import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.controllers.ShowEventController.setSelectedEvent;

public class AddTicketController implements MainController{
    private Ticket thisTicket;
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
    private Label seatsList;

    public void showFreeSeats(){
        //seatsFlowPane.setText(getSelectedEvent().freeSeats());
        if(thisEvent instanceof EventNumberedSeating) seatsList.setText(((EventNumberedSeating) thisEvent).availableSeats());
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
        if(getSelectedEvent()!=null){
            setThisEvent(getSelectedEvent());
        }
        if(thisEvent!=null){
            setValues();
        }

        if(event instanceof EventFreeSeating){
            seatRowInfoText.setVisible(false);
            row.setVisible(false);
            seatNumber.setVisible(false);
            seatsList.setVisible(false);
        }

    }


    /*
    The setValue method adds the information from the event selected in showEvent scene if one was selected
    and adds it to the boxes in the showEvent scene.
     */
    private void setValues(){
        String rowString = String.valueOf(thisTicket.getFacility().getRows());
        row.setText(rowString);
        String seatNumberString = String.valueOf(thisEvent.getFacility().getColumns());
        seatNumber.setText(seatNumberString);
        phoneNumber.setText(thisEvent.getPhoneNr());
    }

    public void updateTicket(ActionEvent ticket) {
        if (thisEvent == null) {
            errorNoEvent();
        } else {
            try {
                if(thisEvent instanceof EventFreeSeating){
                    phoneNumber.setText(getSelectedEvent().getPhoneNr());
                } else if(thisEvent instanceof EventNumberedSeating){
                    phoneNumber.setText(thisEvent.getPhoneNr());


                    ((EventNumberedSeating) thisEvent).setRows(Integer.parseInt(row.getText()));
                    ((EventNumberedSeating) thisEvent).setColumns(Integer.parseInt(seatNumber.getText()));


                }

//                thisEvent.setTicketPrice(Double.parseDouble(ticketPrice.getText()));
//                thisEvent.getEventInfo().setEventName(eventName.getText());
//                thisEvent.getEventInfo().setDate(date.getValue());

            } catch (NumberFormatException e) {
                errorWrongInput("Setenummer må kan ikke inneholde rad tall enn " +
                        thisEvent.getFacility().getRows() + " eller mindre setenummer enn " +
                        thisEvent.getFacility().getColumns());
            } catch (NullPointerException e) {
                errorEmptyFields();
            } catch (Exception e){
                errorWrongInput(thisEvent.toString());
            }
        }
    }




    @Override
    public void exit() {

    }
}

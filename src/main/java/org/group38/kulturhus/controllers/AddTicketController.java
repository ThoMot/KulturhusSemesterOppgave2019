package org.group38.kulturhus.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.group38.kulturhus.model.Event.*;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;


import static org.group38.kulturhus.Utilities.ErrorBoxes.*;
import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.controllers.ShowEventController.setSelectedEvent;
import static org.group38.kulturhus.controllers.ShowTicketsController.getSelectedTicket;

public class AddTicketController implements MainController{
    private Event thisEvent;
    private Event event = getSelectedEvent();
    private Ticket thisTicket=getSelectedTicket();

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
        System.out.println(getSelectedEvent().getTickets());
    }
    private void setTicketInfo(){
        row.setText(thisTicket.getRow().toString());
        seatNumber.setText(thisTicket.getSeat().toString());
        phoneNumber.setText(thisTicket.getPhonenumber());
    }

    public void initialize() {
    setThisEvent(getSelectedEvent());
    setEventInfo();
    showFreeSeats();
    if(thisTicket!=null)setTicketInfo();

    if(event instanceof EventFreeSeating){
        seatRowInfoText.setVisible(false);
        row.setVisible(false);
        seatNumber.setVisible(false);
        seatsList.setVisible(false);
    }

    }

    public void updateTicket(ActionEvent event) {
        if (thisTicket == null) {
            errorBox("Kan ikke endre et objekt som ikke eksisterer", "Ingen arrangement valgt", "Gå til arrangementoversikten for å velge\n" +
                    "et arrangement du vil redigere");
        } else {
            try {
                thisTicket.setRow(Integer.parseInt(row.getText()));
                thisTicket.setSeat(Integer.parseInt(seatNumber.getText()));
                thisTicket.setPhonenumber(phoneNumber.getText());
                SceneManager.navigate(SceneName.SHOWTICKET);
            } catch (NumberFormatException e) { errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format\n\"Raden må være mellom 0 og" + thisEvent.getFacility().getRows() +"\n" +
                    "                        og setenummer mellom 0 og "+ thisEvent.getFacility().getColumns());
            } catch (NullPointerException e) { errorBox("Tomme felter", "Alle felter er ikke utfylt", "Vennligst fyll ut alle felter før du fortsetter");
            } catch (Exception e){errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format");
            }
        }
    }

    public void createTicket(ActionEvent event) {
        if (thisTicket != null) {
            errorBox("Feil", "Et objekt er allerede valg", "Du kan ikke opprette et nytt objekt når et annet er valgt \n " +
                    "vennligst gå tilbake til arrangementoversikten\n for å fjerne markering av billetten");
        } else {
            if(thisEvent instanceof EventFreeSeating){
                ((EventFreeSeating) thisEvent).buyTicket(phoneNumber.getText());
                SceneManager.navigate(SceneName.SHOWTICKET);
            }

            if(thisEvent instanceof EventNumberedSeating){
                ((EventNumberedSeating) thisEvent).buyTicket(Integer.parseInt(row.getText()),Integer.parseInt(seatNumber.getText()),phoneNumber.getText());
                SceneManager.navigate(SceneName.SHOWTICKET);
            }

        }
    }

    @Override
    public void exit() {

    }
}

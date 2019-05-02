package org.group38.kulturhus.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.group38.kulturhus.model.Event.*;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;


import static org.group38.kulturhus.Utilities.ErrorBoxesAndLabel.*;
import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.controllers.ShowEventController.setSelectedEvent;
import static org.group38.kulturhus.controllers.ShowTicketsController.getSelectedTicket;

public class AddTicketController implements MainController{
    private Event event = getSelectedEvent();
    private Ticket thisTicket=getSelectedTicket();
    private Event thisEvent;

    @FXML
    private Label dateTime, eventTitle, seatRowInfoText, seatsList;

    @FXML
    private TextField row, seatNumber, phoneNumber;

    @FXML
    private Button create, update;

    /**Methods for opening different scenes, and setting the selected event if needed in the next scene.
     *it also shows an errormessage in an alert if there is no selected event*/
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

    /**initialize runs when nagivated to scene,
     * This method loads the info from other methods, and uses the setSelectedEvent()
     * to add a reference to the event selected in the showEvent scene if chosen.
     * Methos aldo hides elements depending on type of event(seating or not)*/
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

        if(getSelectedTicket()!=null){
            setThisEvent(getSelectedEvent());
            create.setVisible(false);
        }
        else  {
            update.setVisible(false);
        }

    }
    public void setThisEvent(Event thisEvent) {
        this.thisEvent = thisEvent;
    }

    public void setEventInfo(){
        eventTitle.setText(thisEvent.getEventInfo().getEventName());
        dateTime.setText(thisEvent.getEventInfo().getDate().toString()+", "+thisEvent.getEventInfo().getTime().toString());
    }
    private void setTicketInfo(){
        row.setText(thisTicket.getRow().toString());
        seatNumber.setText(thisTicket.getSeat().toString());
        phoneNumber.setText(thisTicket.getPhonenumber());
    }
    public void showFreeSeats(){
        if(thisEvent instanceof EventNumberedSeating)
            seatsList.setText(((EventNumberedSeating) thisEvent).availableSeats());

    }

    /**createEvent checks if there was already an event selected and in that case shows an error.
     * If not the method proceeds to check what kind of event is created. The method throws exceptions from missing
     * input, and wrong input. If no exceptions are thrown, an event is created.*/
    public void createTicket(ActionEvent event) {
        if  (thisTicket != null) {
            errorBox("Feil", "Et objekt er allerede valg", "Du kan ikke opprette et nytt objekt " +
                    "når et annet er valgt \nvennligst gå tilbake til arrangementoversikten" +
                    "\n for å fjerne markering av billetten");
        } else {
            try {
                if (thisEvent instanceof EventFreeSeating) {
                    String newPhoneNumber = phoneNumber.getText();
                    ((EventFreeSeating) thisEvent).buyTicket(newPhoneNumber);
                    SceneManager.navigate(SceneName.SHOWTICKET);
                }

                if (thisEvent instanceof EventNumberedSeating) {
                    int newSeat = Integer.parseInt(seatNumber.getText());
                    int newRow = Integer.parseInt(row.getText());
                    String newPhoneNumber = phoneNumber.getText();
                    ((EventNumberedSeating) thisEvent).buyTicket(newRow,newSeat,newPhoneNumber);
                    SceneManager.navigate(SceneName.SHOWTICKET);
                }
            }catch (NumberFormatException e) { errorBox("Feil input", "Feil input i et eller flere felter",
                    "Vennligst sørg for at alle felter har riktig format" +
                            "\n\"Raden må være mellom 0 og " + thisEvent.getFacility().getRows() +
                            "\n og setenummer mellom 0 og " + thisEvent.getFacility().getColumns());
            } catch (NullPointerException e) {
                errorBox("Tomme felter", "Alle felter er ikke utfylt",
                        "Vennligst fyll ut alle felter før du fortsetter");
            }catch (IllegalArgumentException e) {
                errorBox("Feil i telefonnummer", "Feil input i telefonnummerfeltet",
                        "Telefonnummer må bestå av nøyaktig 8 siffer");
            }catch (IndexOutOfBoundsException e){ errorBox("Feil i seterad eller setenummer","Feil input i et eller flere felter",
                    "Vennligst sørg for at alle felter har riktig format" +
                            "\n\"Raden må være mellom 0 og " + thisEvent.getFacility().getRows() +
                            "\n og setenummer mellom 0 og " + thisEvent.getFacility().getColumns());
            } catch (Exception e){errorBox("Feil input", "Feil input i et eller flere felter",
                    "Vennligst sørg for at alle felter har riktig format");
            }
        }
    }

    /** updateEvent() tries to update an event if selected in showEvent scene.
     *This method throws exceptions for wrong input and missing input and displays it in an alert box.*/
    public void updateTicket(ActionEvent event) {
        if (thisTicket == null) {
            errorBox("Kan ikke endre et objekt som ikke eksisterer", "Ingen arrangement valgt",
                    "Gå til arrangementoversikten for å velge\n" + "et arrangement du vil redigere");
        } else {
            try {
                thisTicket.setRow(Integer.parseInt(row.getText()));
                thisTicket.setSeat(Integer.parseInt(seatNumber.getText()));
                thisTicket.setPhonenumber(phoneNumber.getText());
                SceneManager.navigate(SceneName.SHOWTICKET);
            } catch (NumberFormatException e) { errorBox("Feil input", "Feil input i et eller flere felter",
                    "Vennligst sørg for at alle felter har riktig format" +
                            "\nRaden må være mellom 0 og" + thisEvent.getFacility().getRows() +
                            "\nog setenummer mellom 0 og "+ thisEvent.getFacility().getColumns());
            } catch (NullPointerException e) { errorBox("Tomme felter", "Alle felter er ikke utfylt",
                    "Vennligst fyll ut alle felter før du fortsetter");
            } catch (Exception e){errorBox("Feil input", "Feil input i et eller flere felter",
                    "Vennligst sørg for at alle felter har riktig format");
            }
        }
    }

    @Override
    public void exit() {

    }
}

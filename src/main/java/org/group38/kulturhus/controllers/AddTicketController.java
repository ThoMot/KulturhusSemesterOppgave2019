package org.group38.kulturhus.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.group38.frameworks.Exceptions.SeatTakenException;
import org.group38.frameworks.Exceptions.WrongFileFormatException;
import org.group38.frameworks.concurrency.WriterThreadRunner;
import org.group38.kulturhus.model.FilePaths.DefaultFiles;
import org.group38.kulturhus.model.FilePaths.EditedFiles;
import org.group38.kulturhus.model.Event.*;
import org.group38.frameworks.sceneHandling.SceneManager;
import org.group38.frameworks.sceneHandling.SceneName;

import java.io.File;
import java.io.IOException;


import static org.group38.frameworks.ErrorBoxesAndLabel.*;
import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.controllers.ShowEventController.setSelectedEvent;
import static org.group38.kulturhus.controllers.ShowTicketsController.getObservableList;
import static org.group38.kulturhus.controllers.ShowTicketsController.getSelectedTicket;
import static org.group38.kulturhus.model.Kulturhus.*;

public class AddTicketController implements MainController{
    private Ticket thisTicket=getSelectedTicket();
    private Event thisEvent;
    private String fileName;
    SceneManager sceneManager = SceneManager.INSTANCE;
    File ticketFile = new File(EditedFiles.getActiveTicketFile());

    @FXML
    private Label dateTime, eventTitle, seatRowInfoText, seatsList;

    @FXML
    private TextField seatRow, seatNumber, phoneNumber;

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
        fileName = EditedFiles.getActiveTicketFile();
    if(thisTicket!=null)setTicketInfo();

    if(thisEvent instanceof EventFreeSeating){
        seatRowInfoText.setVisible(false);
        seatRow.setVisible(false);
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

    @Override
    public void refresh(){
        fileName = EditedFiles.getActiveTicketFile();

    }

    /** setThisEvent sets the ticket to the event chosen in showEventController*/
    public void setThisEvent(Event thisEvent) {
        this.thisEvent = thisEvent;
    }

    /** setEventInfo sets information from the event method to show the user which event the Ticket is connected to*/
    public void setEventInfo(){
        eventTitle.setText(thisEvent.getEventInfo().getEventName());
        dateTime.setText(thisEvent.getEventInfo().getDate().toString()+", "+thisEvent.getEventInfo().getTime().toString());
    }

    /** setTicketInfo sets information about all tickets in TableView */
    private void setTicketInfo(){
        seatRow.setText(thisTicket.getRow().toString());
        seatNumber.setText(thisTicket.getSeat().toString());
        phoneNumber.setText(thisTicket.getPhonenumber());
    }
    /** showFreeSeats shows all seats in a facility if event is a seatingNumbered event*/
    public void showFreeSeats(){
        if(thisEvent instanceof EventNumberedSeating)
            seatsList.setText(((EventNumberedSeating) thisEvent).availableSeats());

    }

    /** createEvent checks if there was already an event selected and in that case shows an error.
     * If not the method proceeds to check what kind of event is created. The method throws exceptions from missing
     * and wrong input. If no exceptions are thrown, an ticket is created. */
    public void createTicket(ActionEvent event) {
        if(thisTicket != null) {
            errorBox("Feil", "Et objekt er allerede valg",
                    "Du kan ikke opprette et nytt objekt når et annet er valgt" +
                            "\n vennligst gå tilbake til arrangementoversikten" +
                            "\n for å fjerne markering av billetten");
        } else {
            try {
                if (thisEvent instanceof EventFreeSeating) {
                    String newPhoneNumber = phoneNumber.getText();
                    ((EventFreeSeating) thisEvent).buyTicket(newPhoneNumber);
                    for(int i=0; i<getTickets().size(); i++){
                        if (getTickets().get(i).getEventId().equals(thisEvent.getEventId())){
                            getTickets().remove(i);
                        }
                    }
                    System.out.println("Dette er alle billettene etter:" + getTickets());
                    getTickets().addAll(thisEvent.getTickets());
                    System.out.println("Nå burde alle de nye ticketsene være lagt til" + getTickets());

                    ticketFile.delete();
                    System.out.println(ticketFile);
                    try {
                        WriterThreadRunner.WriterThreadRunner(getTickets(), fileName);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SceneManager.navigate(SceneName.SHOWTICKET);
                }

                if (thisEvent instanceof EventNumberedSeating) {
                    int newSeat = Integer.parseInt(seatNumber.getText());
                    int newRow = Integer.parseInt(seatRow.getText());
                    String newPhoneNumber = phoneNumber.getText();
                    ((EventNumberedSeating) thisEvent).buyTicket(newRow,newSeat,newPhoneNumber);
                    for(int i=0; i<getTickets().size(); i++){
                        if (getTickets().get(i).getEventId().equals(thisEvent.getEventId())){
                            getTickets().remove(i);
                        }
                    }
                    getTickets().addAll(thisEvent.getTickets());

                    ticketFile.delete();
                    try {
                        WriterThreadRunner.WriterThreadRunner(getTickets(), fileName);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    SceneManager.navigate(SceneName.SHOWTICKET);
                }
            } catch (IndexOutOfBoundsException e){
                errorBox("Billetten er utenfor registeret", "Du har valgt et setenummer eller en seterad\n som er utenfor registeret",
                        "Vennligst velg et annet sete");
            } catch (SeatTakenException e){errorBox("Opptatt", "Setet er allerede tatt", "Vennligst velg et annet sete");}
            catch (NumberFormatException e) {
                errorBox("Feil input", "Feil input i et eller flere felter",
                        "Vennligst sørg for at alle felter har riktig format" +
                                "\n\"Raden må være mellom 0 og " + thisEvent.getFacility().getRows() +
                                "\n og setenummer mellom 0 og " + thisEvent.getFacility().getColumns());
            } catch (NullPointerException e) {
                errorBox("Tomme felter", "Alle felter er ikke utfylt",
                        "Vennligst fyll ut alle felter før du fortsetter");
            } catch (IllegalArgumentException e) {
                errorBox("Feil i telefonnummer", "Feil input i telefonnummerfeltet",
                        "Telefonnummer må bestå av nøyaktig 8 siffer");
            } catch (Exception e){//errorBox("Feil input", "Feil input i et eller flere felter",
                   // "Vennligst sørg for at alle felter har riktig format");
                e.printStackTrace();
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
                getObservableList().remove(thisTicket);
                System.out.println(getObservableList());
                thisTicket.setRow(Integer.parseInt(seatRow.getText()));
                thisTicket.setSeat(Integer.parseInt(seatNumber.getText()));
                thisTicket.setPhonenumber(phoneNumber.getText());
                getObservableList().add(thisTicket);

                ticketFile.delete();
                try {
                    WriterThreadRunner.WriterThreadRunner(getTickets(), EditedFiles.getActiveTicketFile());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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


    public void defaultJOBJ(ActionEvent event){
        if(!fileName.equals(DefaultFiles.TICKETJOBJ.getFileName())){
            File file = new File(fileName);
            file.delete();

            try {
                WriterThreadRunner.WriterThreadRunner(getTickets(), fileName);
            } catch (InterruptedException e) {
                errorBox("Kan ikke skrive til fil", "Lagring kunne ikke gjennomføres", " ");
            }

            try{
                EditedFiles.setTicketJOBJ(DefaultFiles.TICKETJOBJ.getFileName());
            } catch (WrongFileFormatException e){
                errorBox("DEFAULT PATH ER KORRUPT", " ", " ");
            }
            refresh();
        } else errorBox("Feil", "DefaultPath for JOBJ allerede i bruk", "vennligs velg annet alternativ");
    }

    public void defaultCSV(ActionEvent event){
        if(!fileName.equals(DefaultFiles.TICKETCSV.getFileName())){
            try {
                WriterThreadRunner.WriterThreadRunner(getTickets(), fileName);
            } catch (InterruptedException e) {
                errorBox("Kan ikke skrive til fil", "Lagring kunne ikke gjennomføres", " ");
            }

            try{
                EditedFiles.setTicketCSV(DefaultFiles.TICKETCSV.getFileName());
            } catch (WrongFileFormatException e){
                errorBox("DEFAULT PATH ER KORRUPT", " ", " ");
            }
            refresh();
        } else errorBox("Feil", "DefaultPath for CSV allerede i bruk", "vennligs velg annet alternativ");
    }

    public void chooseFile(ActionEvent event){
        sceneManager.makePopupStage(new Stage(), SceneName.FILEEDITOR);
    }

    @Override
    public void exit() {

    }
}

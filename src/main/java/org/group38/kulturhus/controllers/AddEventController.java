package org.group38.kulturhus.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.model.Kulturhus.getEvents;

public class AddEventController implements MainController {
    private Event thisEvent;

    @FXML private TextField eventName, artist, ticketPrice, programInfo, time;
    @FXML private DatePicker date;
    @FXML private ComboBox facility, eventType;
    @FXML private ListView contactPerson;


    public AddEventController() {
    }

    @FXML
    private void goToShowEvent(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.SHOWEVENT);
    }

    @FXML
    private void goToShowVenue(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.SHOWVENUE);
    }

    @FXML
    BorderPane TEST;

    @FXML
    private void test(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/group38/newContact.fxml"));
            loader.setController(this);
            TEST.setRight(loader.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setThisEvent(Event thisEvent) {
        this.thisEvent = thisEvent;
    }
    public void initialize() {
        //get contactpersons add to list
        //get facilities add to combobox
        if(getSelectedEvent()!=null){
            setThisEvent(getSelectedEvent());
        }
        if(thisEvent!=null){
            setValues();
        }
    }
    private void setValues(){
        eventName.setText(thisEvent.getEventInfo().getEventName());
        if (thisEvent instanceof EventNumberedSeating)eventType.getSelectionModel().select("Event med setereservasjon");
        if (thisEvent instanceof EventFreeSeating) eventType.getSelectionModel().select("Event uten setereservasjon");
        artist.setText(thisEvent.getEventInfo().getPerformer());
        ticketPrice.setText(Double.toString(thisEvent.getTicketPrice()));
        programInfo.setText(thisEvent.getEventInfo().getProgram());
        date.setValue(thisEvent.getDate());
        facility.getSelectionModel().select(thisEvent.getFacility());
        time.setText(thisEvent.getTime().toString());
        //contactPerson.getSelectionModel().select(thisEvent.getContactPerson());

    }
    public void createEvent(ActionEvent event){
        if(thisEvent!=null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Objektet eksisterer fra før");
            alert.setHeaderText("Du kan ikke opprette et nytt objekt når et annet er valgt");
            alert.setContentText("Gå til arrangementoversikten for å \n" +
                    "tilbakestille valg av arrangement");
            alert.show();
        }
        else {
            //EventInfo eventInfo = new EventInfo(eventName.getText(), programInfo.getText(), artist.getText(), eventType.getText(), date.getValue(), LocalTime.parse(time.getText()));
            if (eventType.equals("Event med setereservasjon")) {
                //try catch som sender en errormelding dersom man putter inn feil input?
                //getEvents().add(new EventNumberedSeating(contactPerson.getSelectionModel().getSelectedItem(), facility.getValue(), Double.parseDouble(ticketPrice.getText()), eventInfo)));
            } else if (eventType.equals("Event uten setereservasjon")) {
                //try catch med Alert feilmelding dersom man putter feil input?
                //getEvents().add(new EventFreeSeating(contactPerson, facility, Double.parseDouble(ticketPrice.getText()), eventInfo));
            }
        }
    }
    //dette må lagres, oppdateres ikke i showEvent
    public void updateEvent(ActionEvent event){
        if(thisEvent==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kan ikke endre et objekt som ikke eksisterer");
            alert.setHeaderText("Ingen arrangement valgt");
            alert.setContentText("Gå til arrangementoversikten for å velge\n" +
                    "et arrangement du vil redigere");
            alert.show();
        }
        else {
            //try og catch med feilmeldinger ved feil input
            thisEvent.setTicketPrice(Double.parseDouble(ticketPrice.getText()));
            thisEvent.getEventInfo().setEventName(eventName.toString());
            thisEvent.getEventInfo().setDate(date.getValue());
            thisEvent.getEventInfo().setTime(LocalTime.parse(time.getText()));
            thisEvent.getEventInfo().setPerformers(artist.toString());
            thisEvent.getEventInfo().setProgram(programInfo.toString());
        }
    }

    @Override
    public void exit() {

    }
}

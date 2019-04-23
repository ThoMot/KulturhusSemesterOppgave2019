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
        if(getSelectedEvent()!=null){
            setThisEvent(getSelectedEvent());
        }
        if(thisEvent!=null){
            setValues();
        }
    }
    private void setValues(){
        eventName.setText(thisEvent.getEventInfo().getEventName());
        eventType.getSelectionModel().select(thisEvent.getClass());
        artist.setText(thisEvent.getEventInfo().getPerformer());
        ticketPrice.setText(Double.toString(thisEvent.getTicketPrice()));
        programInfo.setText(thisEvent.getEventInfo().getProgram());
        date.setValue(thisEvent.getDate());
        facility.getSelectionModel().select(thisEvent.getFacility());
        time.setText(thisEvent.getTime().toString());
        //contactPerson.getSelectionModel().select(thisEvent.getContactPerson());

    }
    public void createEvent(ActionEvent event){
        EventInfo eventInfo = new EventInfo(eventName.getText(), programInfo.getText(), artist.getText(), org.group38.kulturhus.Utilities.Converters.StringtoLocalDate(date.toString()), org.group38.kulturhus.Utilities.Converters.StringtoLocalTime(time.toString()));
        if(eventType.toString()=="class org.group38.kulturhus.model.Event.EventNumberedSeating"){
            //getEvents().add(new EventNumberedSeating(contactPerson, facility, org.group38.kulturhus.Utilities.Converters.StringtoDouble(ticketPrice.getText(), eventInfo)));
        }
        else if(eventType.toString()=="class org.group38.kulturhus.model.Event.EventFreeSeating"){
            //getEvents().add(new EventFreeSeating(contactPerson, facility, org.group38.kulturhus.Utilities.Converters.StringtoDouble(ticketPrice.getText()), eventInfo));
        }
    }
    //dette må lagres, oppdateres ikke i showEvent
    public void updateEvent(ActionEvent event){
        thisEvent.setTicketPrice(org.group38.kulturhus.Utilities.Converters.StringtoDouble(ticketPrice.getText()));
        thisEvent.getEventInfo().setEventName(eventName.toString());
        thisEvent.getEventInfo().setDate(date.getValue());
        //thisEvent.getEventInfo().setTime(org.group38.kulturhus.Utilities.Converters.StringtoLocalTime(time.getText())); noe galt med converter
        thisEvent.getEventInfo().setPerformers(artist.toString());
        thisEvent.getEventInfo().setProgram(programInfo.toString());
    }

    @Override
    public void exit() {

    }
}

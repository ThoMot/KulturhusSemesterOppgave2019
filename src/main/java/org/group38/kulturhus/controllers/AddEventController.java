package org.group38.kulturhus.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;

import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;

public class AddEventController implements MainController {
    private Event thisEvent;

    @FXML private TextField eventName;
    @FXML private TextField eventType;
    @FXML private TextField artist;
    @FXML private TextField ticketPrice;
    @FXML private TextField programInfo;
    @FXML private DatePicker date;
    @FXML private ComboBox facility;
    @FXML private ListView contactPerson;
    @FXML private TextField time;


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
        eventType.setText(thisEvent.getType());
        artist.setText(thisEvent.getEventInfo().getPerformer());
        ticketPrice.setText(Double.toString(thisEvent.getTicketPrice()));
        programInfo.setText(thisEvent.getEventInfo().getProgram());
        date.setValue(thisEvent.getDate());
        facility.getSelectionModel().select(thisEvent.getFacility());
        //contactPerson.getSelectionModel().select(thisEvent.getContactPerson());

    }
    public void opprettEvent(ActionEvent event){
        EventInfo eventInfo = new EventInfo(eventName.toString(), programInfo.toString(), artist.toString(), org.group38.kulturhus.Utilities.Converters.StringtoLocalDate(date.toString()), org.group38.kulturhus.Utilities.Converters.StringtoLocalTime(time.toString()));
        if(eventType.toString()=="EventNumberedSeating"){
            //EventNumberedSeating eventNumberedSeating= new EventNumberedSeating(contactPerson, facility, Double.parseDouble(ticketPrice.toString()), eventInfo);
        }
        else if(eventType.toString()=="EventFreeSeating"){
            //EventFreeSeating eventFreeSeating =new EventFreeSeating(contactPerson, facility, Double.parseDouble(ticketPrice.toString()), eventInfo))
        }
    }

    @Override
    public void exit() {

    }
}

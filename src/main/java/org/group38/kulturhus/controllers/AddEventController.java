package org.group38.kulturhus.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.group38.kulturhus.model.Event.Event;
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


    public AddEventController() {
    }

    @FXML
    private void goToAddTicket(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.ADDTICKET);
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
        //setfacility
        //setDate

    }

    @Override
    public void exit() {

    }
}

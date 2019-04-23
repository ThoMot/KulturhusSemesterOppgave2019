package org.group38.kulturhus.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;

import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;

//KJERSTI JOBBER HER
public class AddTicketController implements MainController{
    private Event thisEvent;

    @FXML
    private void goToAddEvent(ActionEvent event) throws IOException {
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

    public void initialize() {
    setThisEvent(getSelectedEvent());
    }
    //    private void buyTicket(ActionEvent event){
//        thisEvent=getSelectedEvent();
//        if (thisEvent instanceof EventNumberedSeating){
//            ((EventNumberedSeating) thisEvent).buyTicket();
//        }
//        if(thisEvent instanceof EventFreeSeating){
//            ((EventFreeSeating) thisEvent).buyTicket();
//        }
//    }



    @Override
    public void exit() {

    }
}

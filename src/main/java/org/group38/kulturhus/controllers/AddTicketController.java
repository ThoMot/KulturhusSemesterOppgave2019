package org.group38.kulturhus.controllers;


import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.time.LocalDate;

import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.controllers.ShowEventController.setSelectedEvent;

public class AddTicketController implements MainController{
    private Event thisEvent;

    @FXML
    private Label dateTime;
    @FXML
    private Label eventTitle;

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
        SceneManager.navigate(SceneName.ADDEVENT);
    }

    @FXML
    private void goToShowVenue(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.SHOWVENUE);
    }

    @FXML
    private GridPane freeSeatsView;

//    FXML
//    private Button allSeats;

    @FXML
    private Button seat;
//    private String seatNumber;

    public void setThisEvent(Event thisEvent) {
        this.thisEvent = thisEvent;
    }
    public void setEventInfo(){
        eventTitle.setText(thisEvent.getEventInfo().getEventName());
        dateTime.setText(thisEvent.getEventInfo().getDate().toString()+", "+thisEvent.getEventInfo().getTime().toString());
    }

    public void initialize() {
    setThisEvent(getSelectedEvent());
    setEventInfo();


    freeSets();


    }

//    private void addSeat(ActionEvent event){
//        int i =0;
//        GridPane seatView = new GridPane();
//
//        final Button seat = new Button ("Seat " + i);
//           final int numButton = getSelectedEvent().getColumns() + getSelectedEvent().getColumns();
//           seat.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent e) {
//                    System.out.println("id(" + seat.getId() + ") = " + numButton);
//                }
//            });
//            freeSeatsView.add(seat, i,1);
//    }

    public GridPane freeSets(){

        freeSeatsView = new GridPane();
        int i =0;

        Button seat = new Button ("Seat " + i);
        int numButton = getSelectedEvent().getColumns() + getSelectedEvent().getColumns();
        seat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("id(" + seat.getId() + ") = " + numButton);
            }
        });
        freeSeatsView.add(seat, i,1);
        return freeSeatsView;

//        for(int i = 0; i <= getSelectedEvent().getFacility().getRows(); i++){
//            if(getSelectedEvent().getFacility().getRows() == getSelectedEvent().getFacility().getRows()){
//                System.out.println("\n");
//                for(int j = 0; j <= getSelectedEvent().getFacility().getColumns(); j++){
//
//                    seat = new Button(i + " "+ j + ", ");
//
//                }
//            }
//
//        }
//        return seat;
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

package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Event.Ticket;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import static org.group38.kulturhus.model.Kulturhus.getFacilities;

public class ShowVenueController implements MainController {
    private ObservableList<Facility> observableList;
    private Event thisEvent;

    @FXML
    private TableView<Facility> facilitiesView;

    @FXML
    private TableColumn<Ticket,String> facilityNameColumn, facilityTypeColumn, seatRowColumn, seatNumberColumn;

    @FXML
    private void goToAddTicket(ActionEvent event){
        SceneManager.navigate(SceneName.ADDTICKET);
    }
    @FXML
    private void goToShowTicket(ActionEvent event){
        SceneManager.navigate(SceneName.SHOWTICKET);
    }
    @FXML
    private void goToAddEvent(ActionEvent event){
        SceneManager.navigate(SceneName.ADDEVENT);
    }
    @FXML
    private void goToShowVenue(ActionEvent event){
        SceneManager.navigate(SceneName.SHOWVENUE);
    }
    @FXML
    private void goToShowEvent(ActionEvent event){
        SceneManager.navigate(SceneName.SHOWEVENT);
    }

    @FXML
    private void goToAddVenue(ActionEvent event){
        SceneManager.navigate(SceneName.ADDVENUE);
    }




    @Override
    public void exit() {

    }
    public void initialize() {
        loadData();


    }

    /** loadData() adds all the tickets from the list in Kulturhus into TableView */
    private void loadData(){
        observableList = FXCollections.observableList(getFacilities());
        facilitiesView.setItems(observableList);
    }

    /** initCols() inputs information gathered in load to place where in scene/fxml-file */
    private void initCols(){

    }
}

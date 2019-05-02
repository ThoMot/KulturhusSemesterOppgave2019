package org.group38.kulturhus.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.group38.kulturhus.controllers.ShowVenueController.getThisFacility;
import static org.group38.kulturhus.model.Kulturhus.getFacilities;

public class AddVenueController implements MainController{
    private ObservableList<String> ol;
    private ArrayList<String> facilityTypes= new ArrayList<>();
    @FXML private String forsamling, teater, kino;
    private Facility thisFacility;
    @FXML private Button create, update;
    @FXML private TextField facilityName, row, columns, maxSeats;
    @FXML private Label maxSeats2, seating, seating2;
    @FXML private ComboBox facilityType;

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
        SceneManager.navigate(SceneName.SHOWEVENT);
    }

    @FXML
    private void goToShowVenue(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.SHOWVENUE);
    }
    public void initialize(){
        if(getThisFacility()==null) update.setVisible(false);
        else {
            setThisFacility(getThisFacility());
            loadValues();
            create.setVisible(false);
        }
    }
    @FXML private void editView(){
        if(facilityType.getSelectionModel().getSelectedItem().equals("Kinosal")||facilityType.getSelectionModel().getSelectedItem().equals("Teatersal")){
            maxSeats2.setVisible(false);
            maxSeats.setVisible(false);
            seating2.setVisible(true);
            seating.setVisible(true);
            row.setVisible(true);
            columns.setVisible(true);
        }
        else if(facilityType.getSelectionModel().getSelectedItem().equals("Forsamlingssal")){
            maxSeats2.setVisible(true);
            maxSeats.setVisible(true);
            seating2.setVisible(false);
            seating.setVisible(false);
            row.setVisible(false);
            columns.setVisible(false);
        }
    }

    private void loadValues(){

        facilityName.setText(thisFacility.getFacilityName());
        maxSeats.setText(Integer.toString(thisFacility.getMaxAntSeats()));
        row.setText(Integer.toString(thisFacility.getRows()));
        columns.setText(Integer.toString(thisFacility.getColumns()));
        facilityType.getSelectionModel().select(thisFacility.getFacilityType());
        editView();
    }

    public void setThisFacility(Facility thisFacility) {
        this.thisFacility = thisFacility;
    }

    @Override
    public void exit() {

    }
}

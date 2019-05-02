package org.group38.kulturhus.controllers;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.group38.kulturhus.Utilities.ErrorBoxes.errorBox;
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
    @FXML private Label updated, created;

    @FXML
    private void goToAddEvent(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.ADDEVENT);
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
        if(facilityType.getSelectionModel().getSelectedItem().equals("Forsamlingssal")){
            maxSeats2.setVisible(true);
            maxSeats.setVisible(true);
            seating2.setVisible(false);
            seating.setVisible(false);
            row.setVisible(false);
            columns.setVisible(false);
        }
       else{
            maxSeats2.setVisible(false);
            maxSeats.setVisible(false);
            seating2.setVisible(true);
            seating.setVisible(true);
            row.setVisible(true);
            columns.setVisible(true);
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
    @FXML
    private void createVenue(){
        if(facilityType.getSelectionModel().getSelectedItem()==null) {
            errorBox("Ikke valgt type facility", "Du har ikke valgt en lokaltype", "Vennligst velg en type fra nedtrekksmenyen");
        } else{
            if(facilityType.getSelectionModel().getSelectedItem().equals("Forsamlingssal")){
                try{
                    getFacilities().add(new Facility(facilityName.getText(), facilityType.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(maxSeats.getText())));
                    showLabel();
                } catch (NullPointerException e){ errorBox("Feil", "Det er tomme felter", "Vennligst fyll ut alle felter");
                } catch (IllegalArgumentException e){ errorBox("null eller færre plasser","Lokalet må minst ha en plass", "Vennligst legg inn et gyldig tall" );
                }
            }
            else{
                try {
                    getFacilities().add(new Facility(facilityName.getText(), facilityType.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(row.getText()), Integer.parseInt(columns.getText())));
                    showLabel();
                } catch (NullPointerException e){ errorBox("Feil", "Det er tomme felter", "Vennligst fyll ut alle felter");
                } catch (IllegalArgumentException e){ errorBox("null eller færre plasser","Lokalet må minst ha en plass", "Vennligst legg inn et gyldig tall" );
                }
            }
        }

    }
    private void showLabel(){
        created.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
        visiblePause.setOnFinished(click -> created.setVisible(false));
        visiblePause.play();
    }
    @FXML
    private void updateVenue(){

    }

    public void setThisFacility(Facility thisFacility) {
        this.thisFacility = thisFacility;
    }

    @Override
    public void exit() {

    }
}

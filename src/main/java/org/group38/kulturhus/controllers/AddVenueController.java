package org.group38.kulturhus.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.util.ArrayList;

import static org.group38.kulturhus.Utilities.ErrorBoxesAndLabel.errorBox;
import static org.group38.kulturhus.Utilities.ErrorBoxesAndLabel.showLabel;
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
    @FXML
    private void editView(){
        if(facilityType.getSelectionModel().getSelectedItem().equals("Forsamlingssal")){
            maxSeats2.setVisible(true);
            maxSeats.setVisible(true);
            seating2.setVisible(false);
            seating.setVisible(false);
            row.setVisible(false);
            columns.setVisible(false);
            columns.setText(String.valueOf(0));
            row.setText(String.valueOf(0));
        }
       else{
            maxSeats2.setVisible(false);
            maxSeats.setVisible(false);
            seating2.setVisible(true);
            seating.setVisible(true);
            row.setVisible(true);
            columns.setVisible(true);
            maxSeats.setText(String.valueOf(0));
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
                    showLabel(created);
                } catch (NullPointerException e){ errorBox("Feil", "Det er tomme felter", "Vennligst fyll ut alle felter");
                } catch (IllegalArgumentException e){ errorBox("Negativt antall plasser","Kan ikke være negativt antall seter", "Vennligst legg inn et gyldig tall" );
                }
            }
            else{
                try {
                    getFacilities().add(new Facility(facilityName.getText(), facilityType.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(row.getText()), Integer.parseInt(columns.getText())));
                    showLabel(created);
                } catch (NullPointerException e){ errorBox("Feil", "Det er tomme felter", "Vennligst fyll ut alle felter");
                } catch (IllegalArgumentException e){ errorBox("Negativt antall plasser","Kan ikke være negativt antall seter", "Vennligst legg inn et gyldig tall" );
                }
            }
        }
    }
    @FXML
    private void updateVenue(){
        if(facilityType.getSelectionModel().getSelectedItem()==null) errorBox("Ikke valgt type facility", "Du har ikke valgt en lokaltype", "Vennligst velg en type fra nedtrekksmenyen");
        else{
            try{
                thisFacility.setFacilityName(facilityName.getText());
                thisFacility.setFacilityType(facilityType.getSelectionModel().getSelectedItem().toString());

                //thisFacility.setMaxAntSeats(maxSeats);
                thisFacility.setRows(Integer.parseInt(row.getText()));
                thisFacility.setColumns(Integer.parseInt(columns.getText()));
            } catch (NullPointerException e){ errorBox("Feil", "Det er tomme felter", "Vennligst fyll ut alle felter");
            } catch (IllegalArgumentException e){ errorBox("Negativt antall plasser","Kan ikke være negativt antall seter", "Vennligst legg inn et gyldig tall" );
            }
        }
    }

    public void setThisFacility(Facility thisFacility) {
        this.thisFacility = thisFacility;
    }

    @Override
    public void exit() {

    }
}

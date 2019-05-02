package org.group38.kulturhus.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;

import static org.group38.kulturhus.Utilities.ErrorBoxesAndLabel.errorBox;
import static org.group38.kulturhus.Utilities.ErrorBoxesAndLabel.showLabel;
import static org.group38.kulturhus.controllers.ShowVenueController.getThisFacility;
import static org.group38.kulturhus.model.Kulturhus.getFacilities;

public class AddVenueController implements MainController{
    private Facility thisFacility;
    @FXML private Button create, update;
    @FXML private TextField facilityName, seatRow, columns, maxSeats;
    @FXML private Label maxSeats2, seating, seating2;
    @FXML private ComboBox facilityType;
    @FXML private Label updated, created;
    @FXML
    /** these methods switches the scenes from the menubar*/
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
    /** initialize is called when the scene is opened, if a facility is selected for editing in the previous scene
     * then this method hides the createbutton, and adds the values from the selected facility. If no facility was selected
     * the updatebutton is hidden*/
    public void initialize(){
        if(getThisFacility()==null) update.setVisible(false);
        else {
            setThisFacility(getThisFacility());
            loadValues();
            create.setVisible(false);
        }
    }

    @Override
    public void refresh(){
//        observableList.clear();
//
//        try {
//            observableList.addAll(ReaderThreadRunner.startReader(fileName));
//
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /** editView adds or removes values based on what kind of facility the user wants to create, and hides the values not
     * used for that kind of facility*/
    @FXML
    private void editView(){
        if(facilityType.getSelectionModel().getSelectedItem().equals("Forsamlingssal")){
            maxSeats2.setVisible(true);
            maxSeats.setVisible(true);
            seating2.setVisible(false);
            seating.setVisible(false);
            seatRow.setVisible(false);
            columns.setVisible(false);
            columns.setText(String.valueOf(0));
            seatRow.setText(String.valueOf(0));
        }
       else{
            maxSeats2.setVisible(false);
            maxSeats.setVisible(false);
            seating2.setVisible(true);
            seating.setVisible(true);
            seatRow.setVisible(true);
            columns.setVisible(true);
            maxSeats.setText(String.valueOf(0));
        }
    }
/** loadValues gets the values from the facility selected in the previous scene for editing*/
    private void loadValues(){
        facilityName.setText(thisFacility.getFacilityName());
        maxSeats.setText(Integer.toString(thisFacility.getMaxAntSeats()));
        seatRow.setText(Integer.toString(thisFacility.getRows()));
        columns.setText(Integer.toString(thisFacility.getColumns()));
        facilityType.getSelectionModel().select(thisFacility.getFacilityType());
        editView();
    }
    /** createVenue cheks if facilityType is selected, and if it is, tries to create a new Facility with the input given
     * by the user. If the input is wrong or missing, an errorbox is shown, the facilityType decides which constructor is used*/
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
                    getFacilities().add(new Facility(facilityName.getText(), facilityType.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(seatRow.getText()), Integer.parseInt(columns.getText())));
                    showLabel(created);
                } catch (NullPointerException e){ errorBox("Feil", "Det er tomme felter", "Vennligst fyll ut alle felter");
                } catch (IllegalArgumentException e){ errorBox("Negativt antall plasser","Kan ikke være negativt antall seter", "Vennligst legg inn et gyldig tall" );
                }
            }
        }
    }
    /** updateVenue checks if a facility is selected and tries to update the variables
     * if the input is wrong, it shows an errorbox*/
    //TODO bli ferdig med denne
    @FXML
    private void updateVenue(){
        if(facilityType.getSelectionModel().getSelectedItem()==null) errorBox("Ikke valgt type facility", "Du har ikke valgt en lokaltype", "Vennligst velg en type fra nedtrekksmenyen");
        else{
            try{
                thisFacility.setFacilityName(facilityName.getText());
                thisFacility.setFacilityType(facilityType.getSelectionModel().getSelectedItem().toString());
                //thisFacility.setMaxAntSeats(maxSeats);
                thisFacility.setRows(Integer.parseInt(seatRow.getText()));
                thisFacility.setColumns(Integer.parseInt(columns.getText()));
                showLabel(updated);
            } catch (NullPointerException e){ errorBox("Feil", "Det er tomme felter", "Vennligst fyll ut alle felter");
            } catch (IllegalArgumentException e){ errorBox("Negativt antall plasser","Kan ikke være negativt antall seter", "Vennligst legg inn et gyldig tall" );
            }
        }
    }
/** setMethods for theFacility*/
    public void setThisFacility(Facility thisFacility) {
        this.thisFacility = thisFacility;
    }

    @Override
    public void exit() {

    }
}

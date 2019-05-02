package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.File;

import static org.group38.kulturhus.Utilities.ErrorBoxesAndLabel.errorBox;
import static org.group38.kulturhus.model.Kulturhus.getFacilities;

public class ShowVenueController implements MainController {
    private ObservableList<Facility> observableList;
    private static Facility thisFacility;

    @FXML
    private TableView<Facility> facilitiesView;

    @FXML
    private TableColumn<Facility,String> facilityNameColumn, facilityTypeColumn, seatRowColumn, seatNumberColumn, totalSeats;

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
    private void goToAddVenue(ActionEvent event){ SceneManager.navigate(SceneName.ADDVENUE); }

    @FXML
    private void goToEditVenue() {
        if (facilitiesView.getSelectionModel().getSelectedItem() == null) {
            errorBox("Feil", "Det er ingen rader som er markert", "Vennligst marker en rad i tabellen");
        } else {
            setThisFacility(facilitiesView.getSelectionModel().getSelectedItem());
            SceneManager.navigate(SceneName.ADDVENUE);
        }
    }
    public void initialize() {
        setThisFacility(null);
        loadData();
        initCols();
    }

    /** loadData() adds all the tickets from the list in Kulturhus into TableView */
    private void loadData(){
        observableList = FXCollections.observableList(getFacilities());
        facilitiesView.setItems(observableList);
    }

    /** initCols() inputs information gathered in load to place where in scene/fxml-file */
    private void initCols(){
        facilityNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFacilityName()));
        facilityTypeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFacilityType()));
        seatRowColumn.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getRows())));
        seatNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getColumns())));
        totalSeats.setCellValueFactory(data-> new SimpleStringProperty(Integer.toString(data.getValue().getMaxAntSeats())));
    }
    @FXML
    private void deleteRow(ActionEvent event){
        if(facilitiesView.getSelectionModel().getSelectedItem()==null){
            errorBox("Feil", "Det er ingen rader som er markert", "Vennligst marker en rad i tabellen");
        }
        else{
            Alert mb = new Alert(Alert.AlertType.CONFIRMATION);
            mb.setTitle("Bekreft");
            mb.setHeaderText("Du har trykket slett på "+ facilitiesView.getSelectionModel().getSelectedItem().getFacilityName());
            mb.setContentText("Ønsker du virkerlig å slette dette lokalet?");
            mb.showAndWait().ifPresent(response -> {
                if(response== ButtonType.OK){
                    observableList.remove(facilitiesView.getSelectionModel().getSelectedItem());

                    File file = new File("Event.csv");
                    file.delete();
//                    try {
//                        WriterThreadRunner.WriterThreadRunner(observableList, "Event.csv");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            });
        }
    }

    public void setThisFacility(Facility thisFacility) {
        this.thisFacility = thisFacility;
    }

    public static Facility getThisFacility() {
        return thisFacility;
    }

    @Override
    public void exit() {

    }
}

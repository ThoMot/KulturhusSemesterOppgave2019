package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.group38.frameworks.concurrency.ReaderThreadRunner;
import org.group38.frameworks.concurrency.WriterThreadRunner;
import org.group38.kulturhus.model.FilePaths.DefaultFiles;
import org.group38.kulturhus.model.FilePaths.EditedFiles;
import org.group38.frameworks.Exceptions.WrongFileFormatException;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.frameworks.sceneHandling.SceneManager;
import org.group38.frameworks.sceneHandling.SceneName;

import java.io.File;
import java.util.concurrent.ExecutionException;

import static org.group38.frameworks.ErrorBoxesAndLabel.errorBox;
import static org.group38.kulturhus.model.Kulturhus.getFacilities;

public class ShowVenueController implements MainController {
    private ObservableList<Facility> observableList;
    private static Facility thisFacility;
    private String fileName;
    private SceneManager sceneManager = SceneManager.INSTANCE;

    @FXML private TableView<Facility> facilitiesView;
    @FXML private TableColumn<Facility,String> facilityNameColumn, facilityTypeColumn, seatRowColumn, seatNumberColumn, totalSeats;


 /** these methods is switching between the scenes and setting thisFacility if needed*/
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
    /** the initialize method is run when the scene is opened.
     * It loads the data into the tables and resets thisFacility
     * so that no facility is chosen*/
    public void initialize() {
        setThisFacility(null);
        loadData();
        initCols();

    }

    @Override
    public void refresh(){
        getFacilities().clear();
        fileName = EditedFiles.getActiveFacilityFile();

        try {
            getFacilities().addAll(ReaderThreadRunner.startReader(fileName));

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        observableList = FXCollections.observableList(getFacilities());
        facilitiesView.setItems(observableList);
    }


    /** loadData() adds all the facilites from the list in Kulturhus into TableView */
    private void loadData(){

        fileName = EditedFiles.getActiveFacilityFile();
        getFacilities().clear();

        try {
            getFacilities().addAll(ReaderThreadRunner.startReader(fileName));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }


        observableList = FXCollections.observableList(getFacilities());
        facilitiesView.setItems(observableList);
    }

    public void defaultJOBJ(ActionEvent event){
        if(!fileName.equals(DefaultFiles.FACILITYJOBJ.getFileName())){
            File file = new File(fileName);
            file.delete();

            try {
                WriterThreadRunner.WriterThreadRunner(getFacilities(), fileName);
            } catch (InterruptedException e) {
                errorBox("Kan ikke skrive til fil", "Lagring kunne ikke gjennomføres", " ");
            }
           //TODO HVORDAN BEST HÅNDTERE DEMME EXCEPTION
            try {
                EditedFiles.setFacilityJOBJ(DefaultFiles.FACILITYJOBJ.getFileName());
            } catch (WrongFileFormatException e){
                errorBox("default file er korrupt", " ", "");
            }
            refresh();
        } else errorBox("Feil", "DefaultPath for JOBJ allerede i bruk", "vennligs velg annet alternativ");
    }

    public void defaultCSV(ActionEvent event){
        if(!fileName.equals(DefaultFiles.FACILITYCSV.getFileName())){
            File file = new File(fileName);
            file.delete();
            try {
                WriterThreadRunner.WriterThreadRunner(getFacilities(), fileName);
            } catch (InterruptedException e) {
                errorBox("Kan ikke skrive til fil", "Lagring kunne ikke gjennomføres", " ");
            }

            try{
                EditedFiles.setFacilitysCSV(DefaultFiles.FACILITYCSV.getFileName());
            } catch (WrongFileFormatException e){
                errorBox("Error in default path", "default path is not valid", "");
            }
            refresh();
        } else errorBox("Feil", "DefaultPath for CSV allerede i bruk", "vennligs velg annet alternativ");
    }

        public void chooseFile(ActionEvent event){
            sceneManager.makePopupStage(new Stage(), SceneName.FILEEDITOR);
        }



    /** initCols() decides what data should be in each column of the tableView */
    private void initCols(){
        facilityNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFacilityName()));
        facilityTypeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFacilityType()));
        seatRowColumn.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getRows())));
        seatNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getColumns())));
        totalSeats.setCellValueFactory(data-> new SimpleStringProperty(Integer.toString(data.getValue().getMaxAntSeats())));
    }
    /** the deleteRow method checks if a row is marked and asks permission befoire deleting the object*/
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

                    File file = new File(EditedFiles.getActiveFacilityFile());
                    file.delete();
                    try {
                        WriterThreadRunner.WriterThreadRunner(getFacilities(), EditedFiles.getActiveFacilityFile());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }
/** setter and getter methods*/
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

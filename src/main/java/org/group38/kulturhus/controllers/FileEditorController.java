package org.group38.kulturhus.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.group38.kulturhus.model.EditedFiles;
import org.group38.frameworks.Exeptions.WrongFileFormatException;
import org.group38.kulturhus.model.SaveLoad.FileHandler;
import org.group38.kulturhus.sceneHandling.SceneManager;

import java.io.File;

import static org.group38.kulturhus.Utilities.ErrorBoxesAndLabel.errorBox;

public class FileEditorController {


    private SceneManager sceneManager = SceneManager.INSTANCE;
    private File selectedFile;
    private String header = "Wrong File Format";
    //private ErrorBox error;
    private MainController activeController;
    private FXMLLoader currentLoader;
    private Stage optionsStage;
    String fileName;
    FileHandler fileHandler = new FileHandler();




    public void setCSVEvent(ActionEvent event){
        fileName = fileHandler.chooseFile(null);
        if( fileName == null) {
            return;
        }
        try {
            EditedFiles.setEventsCSV(fileName);
        } catch (WrongFileFormatException e){
            errorBox("Feil format på filen", "Kan ikke lese fra denne filen", "Venligst velg en CSV fil");
        }

        System.out.println(fileName);
    }

    //TODO Duplicate code kan puttes i en metode
    public void setJOBJEvent(ActionEvent event){
        fileName = fileHandler.chooseFile(null);
        if( fileName == null) {
            return;
        }
        try {
            EditedFiles.setEventsJOBJ(fileName);
        } catch (WrongFileFormatException e){
            errorBox("Feil format på filen", "Venligst velg en JOBJ fil", "Default JOBJ vil bli lest");
        }

        System.out.println(fileName);
        System.out.println(EditedFiles.getEventJOBJ());
    }

    public void setCSVContact(ActionEvent event){
        fileName = fileHandler.chooseFile(null);
        if( fileName == null) {
            return;
        }
        try {
            EditedFiles.setContactCSV(fileName);
        } catch (WrongFileFormatException e){
            errorBox("Feil format på filen", "Kan ikke lese fra denne filen", "Venligst velg en CSV fil");
        }

        System.out.println(fileName);
    }

    //TODO Duplicate code kan puttes i en metode
    public void setJOBJContact(ActionEvent event){
        fileName = fileHandler.chooseFile(null);
        if( fileName == null) {
            return;
        }
        try {
            EditedFiles.setContactJOBJ(fileName);
        } catch (WrongFileFormatException e){
            errorBox("Feil format på filen", "Venligst velg en JOBJ fil", "Default JOBJ vil bli lest");
        }

        System.out.println(fileName);
    }


    public void setCSVFacility(ActionEvent event){
        fileName = fileHandler.chooseFile(null);
        if( fileName == null) {
            return;
        }
        try {
            EditedFiles.setFacilitysCSV(fileName);
        } catch (WrongFileFormatException e){
            errorBox("Feil format på filen", "Kan ikke lese fra denne filen", "Venligst velg en CSV fil");
        }

        System.out.println(fileName);
    }

    //TODO Duplicate code kan puttes i en metode
    public void setJOBJFacility(ActionEvent event){
        fileName = fileHandler.chooseFile(null);
        if( fileName == null) {
            return;
        }
        try {
            EditedFiles.setFacilityJOBJ(fileName);
        } catch (WrongFileFormatException e){
            errorBox("Feil format på filen", "Venligst velg en JOBJ fil", "Default JOBJ vil bli lest");
        }

        System.out.println(fileName);
    }

    public void setCSVTickets(ActionEvent event){
        fileName = fileHandler.chooseFile(null);
        if( fileName == null) {
            return;
        }
        try {
            EditedFiles.setTicketCSV(fileName);
        } catch (WrongFileFormatException e){
            errorBox("Feil format på filen", "Kan ikke lese fra denne filen", "Venligst velg en CSV fil");
        }

        System.out.println(fileName);
    }

    //TODO Duplicate code kan puttes i en metode
    public void setJOBJTickets(ActionEvent event){
        fileName = fileHandler.chooseFile(null);
        if( fileName == null) {
            return;
        }
        try {
            EditedFiles.setTicketJOBJ(fileName);
        } catch (WrongFileFormatException e){
            errorBox("Feil format på filen", "Venligst velg en JOBJ fil", "Default JOBJ vil bli lest");
        }

        System.out.println(fileName);
    }

    public void exit() {
        currentLoader = sceneManager.getCurrentLoader();
        activeController = currentLoader.getController();
        activeController.refresh();
        optionsStage = sceneManager.getCurrentPopUpStage();
        optionsStage.setScene(null);
        optionsStage.close();


    }


}

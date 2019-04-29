package org.group38.kulturhus;

import javafx.scene.control.Alert;

public class ErrorBoxes {
    public static void errorEmptyFields(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Alle felter er ikke utfylt");
        alert.setContentText("Vennligst fyll ut alle felter før du fortsetter\n");
        alert.setTitle("Tomme felter");
        alert.show();
    }
    public static void errorWrongInput(String e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Feil input i et eller flere felter");
        alert.setContentText("Vennligst sørg for at alle felter har riktig format\n"+e);
        alert.setTitle("Feil input");
        alert.show();
    }
    public static void errorDuplicate(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Objektet eksisterer fra før");
        alert.setHeaderText("Du kan ikke opprette et nytt objekt når et annet er valgt");
        alert.setContentText("Gå til arrangementoversikten for å \n" +
                "tilbakestille valg av arrangement");
        alert.show();
    }
    public static void errorNoEvent() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Kan ikke endre et objekt som ikke eksisterer");
        alert.setHeaderText("Ingen arrangement valgt");
        alert.setContentText("Gå til arrangementoversikten for å velge\n" +
                "et arrangement du vil redigere");
        alert.show();
    }
    public static void errorNoMarkedEvent(){
        Alert mb = new Alert(Alert.AlertType.INFORMATION);
        mb.setHeaderText("Det er ingen rader som er markert");
        mb.setTitle("Feil");
        mb.setContentText("Vennligst marker en rad i tabellen");
        mb.show();
    }

}

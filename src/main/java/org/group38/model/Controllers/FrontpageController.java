package org.group38.model.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FrontpageController{


    @FXML
    private MenuItem addEvent;
    @FXML
    private MenuItem showEvent;
    @FXML
    private MenuItem addTicket;
    @FXML
    private MenuItem showTicket;
    @FXML
    private MenuItem showVenue;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private MenuBar menuBar;



    @FXML
    private void goToAddTicket(ActionEvent event) throws IOException {
            Parent homeScreen = FXMLLoader.load(getClass().getResource("org/group38/addTicket.fxml"));
            Scene scene = new Scene(homeScreen);
            Stage stage = (Stage)  menuBar.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void goToShowTicket(ActionEvent event) throws IOException {
        Parent homeScreen = FXMLLoader.load(getClass().getResource("showTickets.fxml"));
        Scene scene = new Scene(homeScreen);
        Stage stage = (Stage)  menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void goToAddEvent(ActionEvent event) throws IOException {
        Parent homeScreen = FXMLLoader.load(getClass().getResource("addEvent.fxml"));
        Scene scene = new Scene(homeScreen);
        Stage stage = (Stage)  menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToShowEvent(ActionEvent event) throws IOException {
        Parent homeScreen = FXMLLoader.load(getClass().getResource("showEvents.fxml"));
        Scene scene = new Scene(homeScreen);
        Stage stage = (Stage)  menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToShowVenue(ActionEvent event) throws IOException {
        Parent homeScreen = FXMLLoader.load(getClass().getResource("showVenue.fxml"));
        Scene scene = new Scene(homeScreen);
        Stage stage = (Stage)  menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}


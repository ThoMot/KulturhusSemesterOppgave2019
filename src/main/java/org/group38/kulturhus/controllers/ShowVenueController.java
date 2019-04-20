package org.group38.kulturhus.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;

import static org.group38.kulturhus.model.Kulturhus.getEvents;

public class ShowVenueController implements MainController {

    @FXML
    private TableView tableView;

    @Override
    public void exit() {

    }
    public void initialize() {
//        tableView.getItems().setAll(getEvents());
//        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        tableView.getSelectionModel().selectFirst();

    }
}

package org.group38;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.group38.model.Event.Ticket;

import java.util.Calendar;

//KJERSTI JOBBER HER
public class TicketOverViewController {

    @FXML
    private String seatRowField;

    @FXML
    private String seatNumberField;

    @FXML
    private Integer seatNumber;

    @FXML
    private Calendar dateField;

    @FXML
    private TextField timeField;

    @FXML
    private Double priceField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField facilityNameField;

    @FXML
    private TextField eventNameField;

    public Ticket getNewTicket(){
        Integer row = Integer.parseInt(seatRowField);
        Integer seatNumber = Integer.parseInt(seatNumberField);
        Calendar date = dateField;
        String phoneNumber = phoneNumberField.getText();
        Double price = priceField;
        String facilityName = facilityNameField.getText();
        String eventName = eventNameField.getText();

        Ticket newTicket = new Ticket(row, seatNumber, date, price,phoneNumber,facilityName,eventName);
        return newTicket;
    }




}

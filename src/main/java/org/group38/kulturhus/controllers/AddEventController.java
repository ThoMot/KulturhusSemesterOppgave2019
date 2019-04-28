package org.group38.kulturhus.controllers;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.model.Kulturhus.*;
import static org.group38.kulturhus.model.Validate.isValidTime;

public class AddEventController implements MainController {
    private Event thisEvent;
    private ObservableList<ContactPerson> ol;
    private ObservableList<Facility> ol2;

    @FXML private TextField eventName, artist, ticketPrice, programInfo, time, type; //addEvent
    @FXML private TextField firstName, lastName, email, company, phoneNumber, webPage, other; //addcontactPerson
    @FXML private DatePicker date;
    @FXML private ComboBox facility, eventType;
    @FXML private TableView contactPerson;
    @FXML private TableColumn<ContactPerson, String> firstNameColumn, lastNameColumn, phoneNumberColumn;

    @FXML
    private void goToShowEvent(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.SHOWEVENT);
    }

    @FXML
    private void goToShowVenue(ActionEvent event) throws IOException {
        SceneManager.navigate(SceneName.SHOWVENUE);
    }

    @FXML
    BorderPane TEST;

    @FXML
    private void goToAddContactPerson(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/group38/newContact.fxml"));
            loader.setController(this);
            TEST.setRight(loader.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setThisEvent(Event thisEvent) {
        this.thisEvent = thisEvent;
    }
    public void initialize() {
        initCols();
        loadInfo();
        if(getSelectedEvent()!=null){
            setThisEvent(getSelectedEvent());
        }
        if(thisEvent!=null){
            setValues();
        }
    }
    private void initCols(){
        firstNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));
        phoneNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getContactInfo().getPhoneNr()));
    }
    private void loadInfo(){
        //get contactpersons add to list
        ol = FXCollections.observableList(getContactPeople());
        contactPerson.setItems(ol);

        //get facilities add to combobox
        ol2 = FXCollections.observableList(getFacilities());
        facility.setItems(ol2);
    }
    public void createContactPerson(ActionEvent event){
        try {
            ContactInfo contactInfo = new ContactInfo(email.getText(), phoneNumber.getText());
            getContactPeople().add(new ContactPerson(firstName.getText(), lastName.getText(), contactInfo));
        }
        catch (Exception e){
            errorFeilInput(e.toString());
        }
    }

    private void setValues(){
        eventName.setText(thisEvent.getEventInfo().getEventName());
        if (thisEvent instanceof EventNumberedSeating)eventType.getSelectionModel().select("Event med setereservasjon");
        if (thisEvent instanceof EventFreeSeating) eventType.getSelectionModel().select("Event uten setereservasjon");
        artist.setText(thisEvent.getEventInfo().getPerformer());
        ticketPrice.setText(Double.toString(thisEvent.getTicketPrice()));
        programInfo.setText(thisEvent.getEventInfo().getProgram());
        date.setValue(thisEvent.getDate());
        facility.getSelectionModel().select(thisEvent.getFacility());
        time.setText(thisEvent.getTime().toString());
        type.setText(thisEvent.getType());
        contactPerson.getSelectionModel().select(thisEvent.getContactPerson()); //Denne funker ikke
    }
    public void createEvent(ActionEvent event) {
        if (thisEvent != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Objektet eksisterer fra før");
            alert.setHeaderText("Du kan ikke opprette et nytt objekt når et annet er valgt");
            alert.setContentText("Gå til arrangementoversikten for å \n" +
                    "tilbakestille valg av arrangement");
            alert.show();
        } else {
            if (!isValidTime(time.getText())) errorFeilInput("Tiden er på feil format\n Tiden skal være på følgende format\n TT:mm");
            else {
                EventInfo eventInfo = new EventInfo(eventName.getText(), programInfo.getText(), artist.getText(), type.getText(), date.getValue(), LocalTime.parse(time.getText()));
                if (eventType.getValue().equals("Event med setereservasjon")) { //if(facility.getValue().getMaxAntSeats!=0)
                    try {
                        getEvents().add(new EventNumberedSeating((ContactPerson) contactPerson.getSelectionModel().getSelectedItem(), (Facility) facility.getValue(), Double.parseDouble(ticketPrice.getText()), eventInfo));
                    } catch(NumberFormatException e){ errorFeilInput("Billettprisen må være en double \n Skriv prisen på følgende format\n 000.0");
                    } catch(NullPointerException e){ errorTommeFelter();
                    } catch (Exception e) { errorFeilInput(e.toString());
                    }
                } else if (eventType.getValue().equals("Event uten setereservasjon")) { //if(facility.getValue().getMaxAntSeats==0)
                    try {
                        getEvents().add(new EventFreeSeating((ContactPerson) contactPerson.getSelectionModel().getSelectedItem(), (Facility) facility.getValue(), Double.parseDouble(ticketPrice.getText()), eventInfo));
                    } catch(NumberFormatException e){errorFeilInput("Billettprisen må være en double \n Skriv prisen på følgende format\n 000.0");
                    } catch(NullPointerException e){ errorTommeFelter();
                    } catch (Exception e) { errorFeilInput(e.toString());
                    }
                }
            }
        }
    }
    public void updateEvent(ActionEvent event){
        if(thisEvent==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kan ikke endre et objekt som ikke eksisterer");
            alert.setHeaderText("Ingen arrangement valgt");
            alert.setContentText("Gå til arrangementoversikten for å velge\n" +
                    "et arrangement du vil redigere");
            alert.show();
        }
        else {
            //if else som sjekker ticketprice og time
            thisEvent.setTicketPrice(Double.parseDouble(ticketPrice.getText()));
            thisEvent.getEventInfo().setEventName(eventName.getText());
            thisEvent.getEventInfo().setDate(date.getValue());
            thisEvent.getEventInfo().setTime(LocalTime.parse(time.getText()));
            thisEvent.getEventInfo().setPerformers(artist.getText());
            thisEvent.getEventInfo().setProgram(programInfo.getText());
        }
    }
    private void errorTommeFelter(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Alle felter er ikke utfylt");
        alert.setContentText("Vennligst fyll ut alle felter før du fortsetter\n");
        alert.setTitle("Tomme felter");
        alert.show();
    }
    private void errorFeilInput(String e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Feil input i et eller flere felter");
        alert.setContentText("Vennligst sørg for at alle felter har riktig format\n"+e);
        alert.setTitle("Feil input");
        alert.show();
    }

    @Override
    public void exit() {

    }
}

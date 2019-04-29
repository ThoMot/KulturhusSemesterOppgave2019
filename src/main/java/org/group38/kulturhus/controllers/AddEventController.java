package org.group38.kulturhus.controllers;

import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javafx.util.Duration;
import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.sceneHandling.SceneManager;
import org.group38.kulturhus.sceneHandling.SceneName;

import static org.group38.kulturhus.ErrorBoxes.*;
import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.model.Kulturhus.*;
import static org.group38.kulturhus.model.Validate.isNotEmptyString;

public class AddEventController implements MainController {
    //data field
    private Event thisEvent;
    private ContactPerson thisContactPerson;
    private ObservableList<ContactPerson> ol;
    private ObservableList<Facility> ol2;
    @FXML private TextField eventName, artist, ticketPrice, time, type; //addEvent
    @FXML private TextArea programInfo, other;
    @FXML private TextField firstName, lastName, email, company, phoneNumber, webPage; //addcontactPerson
    @FXML private DatePicker date;
    @FXML private ComboBox facility;
    @FXML private TableView contactPerson;
    @FXML private TableColumn<ContactPerson, String> firstNameColumn, lastNameColumn, phoneNumberColumn;
    @FXML private BorderPane contactPersonPane;
    @FXML private Label createContLb, createEvLb;
    @FXML private Button create, update, createCont, updateCont;

    //**Methods for opening to different scenes
    @FXML
    private void goToShowEvent(ActionEvent event){ SceneManager.navigate(SceneName.SHOWEVENT); }
    @FXML
    private void goToShowVenue(ActionEvent event){ SceneManager.navigate(SceneName.SHOWVENUE); }
    @FXML
    private void goToAddContactPerson(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/group38/newContact.fxml"));
            loader.setController(this);
            contactPersonPane.setRight(loader.load());
            setThisContactPerson(null);
            updateCont.setVisible(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //**Setting the event to the event chosen in showEventController
    public void setThisEvent(Event thisEvent) {
        this.thisEvent = thisEvent;
    }
    /*
    The initialize method runs when the scene is opened,
    * This method loads the info from other methods, and uses the setSelectedEvent()
    * to add a reference to the event selected in the showEvent scene if chosen
    */
    public void initialize() {
        initCols();
        loadInfo();
        if(getSelectedEvent()!=null){
            setThisEvent(getSelectedEvent());
            create.setVisible(false);
        }
        else update.setVisible(false);
        if(thisEvent!=null){
            setValues();
        }
    }
    //** initCols method is deciding what the table columns shall contain
    private void initCols(){
        firstNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));
        phoneNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getContactInfo().getPhoneNr()));
    }
    //**loadInfo method adds the facility list to the combobox and the contactpeople list to the tableview
    private void loadInfo(){
        ol = FXCollections.observableList(getContactPeople());
        contactPerson.setItems(ol);

        ol2 = FXCollections.observableList(getFacilities());
        facility.setItems(ol2);
    }
    /*
    The setValue method adds the information from the event selected in showEvent scene if one was selected
    and adds it to the boxes in the showEvent scene.
     */
    private void setValues(){
        eventName.setText(thisEvent.getEventInfo().getEventName());
        artist.setText(thisEvent.getEventInfo().getPerformer());
        ticketPrice.setText(Double.toString(thisEvent.getTicketPrice()));
        programInfo.setText(thisEvent.getEventInfo().getProgram());
        date.setValue(thisEvent.getDate());
        facility.getSelectionModel().select(thisEvent.getFacility());
        time.setText(thisEvent.getTime().toString());
        type.setText(thisEvent.getType());
        contactPerson.getSelectionModel().select(thisEvent.getContactPerson());
    }
    /*
    createEvent checks if there was already an event selected and in that case shows an error. If not the method proceeds
    to check what kind of event is created. The method throws exceptions from missing input, and wrong input. If no exceptions are thrown, an event is created.
     */
    public void createEvent(ActionEvent event) {

        if (contactPerson.getSelectionModel().getSelectedItem() == null) errorEmptyFields();
        else {
            Facility f = (Facility) facility.getSelectionModel().getSelectedItem();
            if (f.getMaxAntSeats() == 0) {
                try {
                    EventInfo eventInfo = new EventInfo(eventName.getText(), programInfo.getText(), artist.getText(), type.getText(), date.getValue(), LocalTime.parse(time.getText()));
                    getEvents().add(new EventNumberedSeating((ContactPerson) contactPerson.getSelectionModel().getSelectedItem(), (Facility) facility.getValue(), Double.parseDouble(ticketPrice.getText()), eventInfo));
                    createEvLb.setVisible(true);
                    PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
                    visiblePause.setOnFinished(click -> createEvLb.setVisible(false));
                    visiblePause.play();
                } catch (NumberFormatException e) { errorWrongInput("Billettprisen må være en double \n Skriv prisen på følgende format\n 000.0");
                } catch (DateTimeParseException e) { errorWrongInput("Tiden er på feil format\n Tiden skal være på følgende format\n TT:mm");
                } catch (NullPointerException e) { errorEmptyFields();
                } catch (Exception e) { errorWrongInput(e.toString());
                }
            } else if (f.getMaxAntSeats() != 0) {
                try {
                    EventInfo eventInfo = new EventInfo(eventName.getText(), programInfo.getText(), artist.getText(), type.getText(), date.getValue(), LocalTime.parse(time.getText()));
                    getEvents().add(new EventFreeSeating((ContactPerson) contactPerson.getSelectionModel().getSelectedItem(), (Facility) facility.getValue(), Double.parseDouble(ticketPrice.getText()), eventInfo));
                    createEvLb.setVisible(true);
                    PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
                    visiblePause.setOnFinished(click -> createEvLb.setVisible(false));
                    visiblePause.play();
                } catch (NumberFormatException e) { errorWrongInput("Billettprisen må være en double \n Skriv prisen på følgende format\n 000.0");
                } catch (DateTimeParseException e) { errorWrongInput("Tiden er på feil format\n Tiden skal være på følgende format\n TT:mm");
                } catch (NullPointerException e) { errorEmptyFields();
                } catch (Exception e) { errorWrongInput(e.toString());
                }
            }
        }
    }
    /*
    updateEvent tries to update an event if selected in showeEvent scene.
    This method throws exceptions for wrong input and missing input and displays it in an alert box.
     */
    public void updateEvent(ActionEvent event) {
        try {
            thisEvent.setTicketPrice(Double.parseDouble(ticketPrice.getText()));
            thisEvent.getEventInfo().setEventName(eventName.getText());
            thisEvent.getEventInfo().setDate(date.getValue());
            thisEvent.getEventInfo().setTime(LocalTime.parse(time.getText()));
            thisEvent.getEventInfo().setPerformers(artist.getText());
            thisEvent.getEventInfo().setProgram(programInfo.getText());
            createEvLb.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
            visiblePause.setOnFinished(click -> createEvLb.setVisible(false));
            visiblePause.play();
        } catch (NumberFormatException e) { errorWrongInput("Billettprisen må være en double \n Skriv prisen på følgende format\n 000.0");
        } catch (NullPointerException e) { errorEmptyFields();
        } catch (DateTimeParseException e) { errorWrongInput("Tiden er på feil format\n Tiden skal være på følgende format\n TT:mm");
        } catch (Exception e){ errorWrongInput(e.toString());
        }
    }

    public void setThisContactPerson(ContactPerson thisContactPerson) {
        this.thisContactPerson = thisContactPerson;
    }
    /*
    The createContactPerson method tries to create a contactPerson and throws an exception
    if the input is wrong or missing. Th exceptions are shown in an alert box. When/if the
    contactPerson is created, the createContact scene is closed.
     */
    public void createContactPerson(ActionEvent event){
        try {
            ContactInfo contactInfo = new ContactInfo(email.getText(), phoneNumber.getText());
            getContactPeople().add(new ContactPerson(firstName.getText(), lastName.getText(), contactInfo));
            if (isNotEmptyString(company.getText()))
                getContactPeople().get(getContactPeople().size() - 1).setAffiliation(company.getText());
            if (isNotEmptyString(webPage.getText()))
                getContactPeople().get(getContactPeople().size() - 1).setWebPage(webPage.getText());
            if (isNotEmptyString(other.getText()))
                getContactPeople().get(getContactPeople().size() - 1).setNotes(other.getText());
            createContLb.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
            visiblePause.setOnFinished(click -> createContLb.setVisible(false));
            visiblePause.play();
            //Må LEGGE INN AT KONTAKTPERSONSCENEN LUKKES HER THORA
            loadInfo();
        } catch (NullPointerException e) {
            errorEmptyFields();
        } catch (Exception e) {
            errorWrongInput(e.toString());
        }
    }

    public void updateContactPerson(ActionEvent event){
        if(contactPerson.getSelectionModel().getSelectedItem()==null){
            errorNoMarkedContactPerson();
        }
        else{
            setThisContactPerson((ContactPerson)contactPerson.getSelectionModel().getSelectedItem());
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/group38/newContact.fxml"));
                loader.setController(this);
                contactPersonPane.setRight(loader.load());
                createCont.setVisible(false);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setValuesContactPerson();
    }
    public void updateContactPersonComplete(ActionEvent event){
        if(thisContactPerson==null) errorNoContactPerson();
        else{
            try{
                thisContactPerson.setFirstName(firstName.getText());
                thisContactPerson.setLastName(lastName.getText());
                thisContactPerson.getContactInfo().setEmail(email.getText());
                thisContactPerson.getContactInfo().setPhoneNr(phoneNumber.getText());
                if(isNotEmptyString(other.getText())) thisContactPerson.setNotes(other.getText());
                if(isNotEmptyString(webPage.getText()))thisContactPerson.setWebPage(webPage.getText());
                if(isNotEmptyString(company.getText()))thisContactPerson.setAffiliation(company.getText());
                createContLb.setVisible(true);
                PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
                visiblePause.setOnFinished(click -> createContLb.setVisible(false));
                visiblePause.play();
                //Må LEGGE INN AT KONTAKTPERSONSCENEN LUKKES HER THORA
            }
            catch (NullPointerException e) { errorEmptyFields();}
            catch (Exception e){ errorWrongInput(e.toString());
            }

        }
    }
    private void setValuesContactPerson(){
        firstName.setText(thisContactPerson.getFirstName());
        lastName.setText(thisContactPerson.getLastName());
        email.setText(thisContactPerson.getEmail());
        company.setText(thisContactPerson.getAffiliation());
        phoneNumber.setText(thisContactPerson.getPhoneNr());
        other.setText(thisContactPerson.getNotes());
        webPage.setText(thisContactPerson.getWebPage());
    }

    @Override
    public void exit() {

    }
}

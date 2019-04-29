package org.group38.kulturhus.controllers;

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
    private ObservableList<ContactPerson> ol;
    private ObservableList<Facility> ol2;
    @FXML private TextField eventName, artist, ticketPrice, time, type; //addEvent
    @FXML private TextArea programInfo;
    @FXML private TextField firstName, lastName, email, company, phoneNumber, webPage, other; //addcontactPerson
    @FXML private DatePicker date;
    @FXML private ComboBox facility, eventType;
    @FXML private TableView contactPerson;
    @FXML private TableColumn<ContactPerson, String> firstNameColumn, lastNameColumn, phoneNumberColumn;
    @FXML BorderPane contactPersonPane;

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
        }
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
    The createContactPerson method tries to create a contactPerson and throws an exception
    if the input is wrong or missing. Th exceptions are shown in an alert box. When/if the
    contactPerson is created, the createContact scene is closed.
     */
    public void createContactPerson(ActionEvent event){
        try {
            ContactInfo contactInfo = new ContactInfo(email.getText(), phoneNumber.getText());
            getContactPeople().add(new ContactPerson(firstName.getText(), lastName.getText(), contactInfo));
            if(isNotEmptyString(company.getText())) getContactPeople().get(getContactPeople().size()-1).setAffiliation(company.getText());
            if(isNotEmptyString(webPage.getText())) getContactPeople().get(getContactPeople().size()-1).setWebPage(webPage.getText());
            if(isNotEmptyString(other.getText())) getContactPeople().get(getContactPeople().size()-1).setNotes(other.getText());
            System.out.println(getContactPeople().get(getContactPeople().size()-1));
            //Må LEGGE INN AT KONTAKTPERSONSCENEN LUKKES HER THORA
            loadInfo();
        }
        catch (Exception e){
            errorWrongInput(e.toString());
        }
    }

    /*
    The setValue method adds the information from the event selected in showEvent scene if one was selected
    and adds it to the boxes in the showEvent scene.
     */
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
    /*
    createEvent checks if there was already an event selected and in that case shows an error. If not the method proceeds
    to check what kind of event is created. The method throws exceptions from missing input, and wrong input. If no exceptions are thrown, an event is created.
     */
    public void createEvent(ActionEvent event) {
        if (thisEvent != null) {
            errorDuplicate();
        }
        else if (eventType.getValue().equals("Event med setereservasjon")) { //if(facility.getValue().getMaxAntSeats!=0)
            try {
                EventInfo eventInfo = new EventInfo(eventName.getText(), programInfo.getText(), artist.getText(), type.getText(), date.getValue(), LocalTime.parse(time.getText()));
                getEvents().add(new EventNumberedSeating((ContactPerson) contactPerson.getSelectionModel().getSelectedItem(), (Facility) facility.getValue(), Double.parseDouble(ticketPrice.getText()), eventInfo));
            } catch (NumberFormatException e){ errorWrongInput("Billettprisen må være en double \n Skriv prisen på følgende format\n 000.0");
            } catch (DateTimeParseException e) { errorWrongInput("Tiden er på feil format\n Tiden skal være på følgende format\n TT:mm");
            } catch (NullPointerException e){ errorEmptyFields(e);
            } catch (Exception e) { errorWrongInput(e.toString());
            }
        } else if (eventType.getValue().equals("Event uten setereservasjon")) { //if(facility.getValue().getMaxAntSeats==0)
            try {
                EventInfo eventInfo = new EventInfo(eventName.getText(), programInfo.getText(), artist.getText(), type.getText(), date.getValue(), LocalTime.parse(time.getText()));
                getEvents().add(new EventFreeSeating((ContactPerson) contactPerson.getSelectionModel().getSelectedItem(), (Facility) facility.getValue(), Double.parseDouble(ticketPrice.getText()), eventInfo));
            } catch (NumberFormatException e){ errorWrongInput("Billettprisen må være en double \n Skriv prisen på følgende format\n 000.0");
            } catch (DateTimeParseException e) { errorWrongInput("Tiden er på feil format\n Tiden skal være på følgende format\n TT:mm");
            } catch (NullPointerException e){ errorEmptyFields(e);
            } catch (Exception e) { errorWrongInput(e.toString());
            }

        }
    }
    /*
    updateEvent tries to update an event if selected in showeEvent scene.
    This method throws exceptions for wrong input and missing input and displays it in an alert box.
     */
    public void updateEvent(ActionEvent event) {
        if (thisEvent == null) {
            errorNoEvent();
        } else {
            try {
                thisEvent.setTicketPrice(Double.parseDouble(ticketPrice.getText()));
                thisEvent.getEventInfo().setEventName(eventName.getText());
                thisEvent.getEventInfo().setDate(date.getValue());
                thisEvent.getEventInfo().setTime(LocalTime.parse(time.getText()));
                thisEvent.getEventInfo().setPerformers(artist.getText());
                thisEvent.getEventInfo().setProgram(programInfo.getText());
            } catch (NumberFormatException e) { errorWrongInput("Billettprisen må være en double \n Skriv prisen på følgende format\n 000.0");
            } catch (NullPointerException e) { errorEmptyFields(e);
            } catch (DateTimeParseException e) { errorWrongInput("Tiden er på feil format\n Tiden skal være på følgende format\n TT:mm");
            } catch (Exception e){ errorWrongInput(event.toString());
            }
        }
    }

    @Override
    public void exit() {

    }
}

package org.group38.kulturhus.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.concurrent.ExecutionException;

import javafx.stage.Stage;
import org.group38.frameworks.concurrency.ReaderThreadRunner;
import org.group38.frameworks.concurrency.WriterThreadRunner;
import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.FilePaths.DefaultFiles;
import org.group38.kulturhus.model.FilePaths.EditedFiles;
import org.group38.kulturhus.model.Event.Event;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.frameworks.Exeptions.WrongFileFormatException;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.frameworks.sceneHandling.SceneManager;
import org.group38.frameworks.sceneHandling.SceneName;

import static org.group38.frameworks.ErrorBoxesAndLabel.*;
import static org.group38.kulturhus.controllers.ShowEventController.getSelectedEvent;
import static org.group38.kulturhus.model.Kulturhus.*;
import static org.group38.frameworks.Validate.isNotEmptyString;




public class AddEventController implements MainController {
    //data field
    private SceneManager sceneManager = SceneManager.INSTANCE;
    private File eventFile = new File(EditedFiles.getActiveEventFile());
    File contactFile = new File(EditedFiles.getActiveContactFile());
    private Event thisEvent;
    private ContactPerson thisContactPerson;
    private ObservableList<ContactPerson> ol;
    private ObservableList<Facility> ol2;
    private String fileNameC = DefaultFiles.CONTACTJOBJ.getFileName();
    private String fileNameF = DefaultFiles.FACILITYJOBJ.getFileName();
    @FXML private TextField eventName, artist, ticketPrice, time; //addEvent
    @FXML private TextArea programInfo, other, editFacility;
    @FXML private TextField firstName, lastName, email, company, phoneNumber, webPage; //addcontactPerson
    @FXML private DatePicker date;
    @FXML private ComboBox facility;
    @FXML private TableView contactPerson;
    @FXML private TableColumn<ContactPerson, String> firstNameColumn, lastNameColumn, phoneNumberColumn;
    @FXML private BorderPane contactPersonPane;
    @FXML private Label createContLb, createEvLb, contLabel, facilityLb;
    @FXML private Button create, update, createContact, updateContact;


    /**Methods for opening to different scenes*/
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
            updateContact.setVisible(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**The initialize method runs when the scene is opened,
    * This method loads the info from other methods, and uses the setSelectedEvent()
    * to add a reference to the event selected in the showEvent scene if chosen*/
    public void initialize() {
        initCols();
        loadInfo();
        if(getSelectedEvent()!=null){
            setThisEvent(getSelectedEvent());
            create.setVisible(false);
            facility.setVisible(false);
            facilityLb.setVisible(false);
            editFacility.setVisible(true);
        }
        else update.setVisible(false);
        if(thisEvent!=null){
            setValues();
        }
    }

    @Override
    public void refresh(){
        getContactPeople().clear();
        getFacilities().clear();

        fileNameF = EditedFiles.getActiveFacilityFile();
        fileNameC = EditedFiles.getActiveContactFile();

        try {
            getContactPeople().addAll(ReaderThreadRunner.startReader(fileNameC));
            getFacilities().addAll(ReaderThreadRunner.startReader(fileNameF));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        ol = FXCollections.observableList(getContactPeople());
        ol2 = FXCollections.observableList(getFacilities());
        contactPerson.setItems(ol);
        facility.setItems(ol2);

    }

    public void defaultJOBJ(ActionEvent event){
        if(!fileNameC.equals(DefaultFiles.CONTACTJOBJ.getFileName()) || !fileNameF.equals(DefaultFiles.FACILITYJOBJ)){
            File file = new File(fileNameC);
            File file2 = new File(fileNameF);
            file.delete();
            file2.delete();

            try {
                WriterThreadRunner.WriterThreadRunner(getContactPeople(), fileNameC);
                WriterThreadRunner.WriterThreadRunner(getFacilities(), fileNameF);
            } catch (InterruptedException e) {
                errorBox("Kan ikke skrive til fil", "Lagring kunne ikke gjennomføres", " ");
            }
            //TODO HVORDAN BEST HÅNDTERE DEMME EXCEPTION
            try {
                EditedFiles.setFacilityJOBJ(DefaultFiles.FACILITYJOBJ.getFileName());
                EditedFiles.setContactJOBJ(DefaultFiles.CONTACTJOBJ.getFileName());
            } catch (WrongFileFormatException e){
                errorBox("default file er korrupt", " ", "");
            }
            refresh();
        } else errorBox("Feil", "DefaultPath for JOBJ allerede i bruk", "vennligs velg annet alternativ");
    }

    public void defaultCSV(ActionEvent event){
        if(!fileNameC.equals(DefaultFiles.CONTACTCSV.getFileName()) || !fileNameF.equals(DefaultFiles.FACILITYCSV)){

            File file = new File(fileNameC);
            File file2 = new File(fileNameF);
            file.delete();
            file2.delete();
            try {
                WriterThreadRunner.WriterThreadRunner(getContactPeople(), fileNameC);
                WriterThreadRunner.WriterThreadRunner(getFacilities(), fileNameF);
            } catch (InterruptedException e) {
                errorBox("Kan ikke skrive til fil", "Lagring kunne ikke gjennomføres", " ");
            }

//TODO Hvordan best håndtere denne exception
            try{
                EditedFiles.setFacilitysCSV(DefaultFiles.FACILITYCSV.getFileName());
                EditedFiles.setContactCSV(DefaultFiles.CONTACTCSV.getFileName());
            } catch (WrongFileFormatException e){
                errorBox("HVA", "Skrives", "HER");
            }
            refresh();
        } else errorBox("Feil", "DefaultPath for CSV allerede i bruk", "vennligs velg annet alternativ");
    }





    public void chooseFile(){
        sceneManager.makePopupStage(new Stage(), SceneName.FILEEDITOR);
    }


    /** initCols method is deciding what the table columns shall contain*/
    private void initCols(){
        firstNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));
        phoneNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getContactInfo().getPhoneNr()));
    }
    /**loadInfo method adds the facility list to the combobox and the contactpeople list to the tableview*/
    private void loadInfo(){
        getContactPeople().clear();
        getFacilities().clear();

        fileNameC = EditedFiles.getActiveContactFile();
        fileNameF = EditedFiles.getActiveFacilityFile();

        try {
            getContactPeople().addAll(ReaderThreadRunner.startReader(fileNameC));
            getFacilities().addAll(ReaderThreadRunner.startReader(fileNameF));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }


        ol = FXCollections.observableList(getContactPeople());
        ol2 = FXCollections.observableList(getFacilities());
        contactPerson.setItems(ol);
        facility.setItems(ol2);

    }
    /**The setValue method adds the information from the event selected in showEvent scene if one was selected
    *and adds it to the boxes in the showEvent scene.*/
    private void setValues(){
        eventName.setText(thisEvent.getEventInfo().getEventName());
        artist.setText(thisEvent.getEventInfo().getPerformer());
        ticketPrice.setText(Double.toString(thisEvent.getTicketPrice()));
        programInfo.setText(thisEvent.getEventInfo().getProgram());
        date.setValue(thisEvent.getDate());
        facility.getSelectionModel().select(thisEvent.getFacility());
        time.setText(thisEvent.getTime().toString());
        contactPerson.getSelectionModel().select(thisEvent.getContactPerson());
    }
    /**createEvent checks if there was already an event selected and in that case shows an error. If not the method proceeds
    *to check what kind of event is created. The method throws exceptions from missing input, and wrong input. If no exceptions are thrown, an event is created.*/
    @FXML
    private void createEvent(ActionEvent event) {
        if (contactPerson.getSelectionModel().getSelectedItem() == null) errorBox("Tomme felter", "Alle felter er ikke utfylt", "Vennligst fyll ut alle felter før du fortsetter");
        else {
            Facility f = (Facility) facility.getSelectionModel().getSelectedItem();
            if (f.getMaxAntSeats() == 0) {
                try {
                    EventInfo eventInfo = new EventInfo(eventName.getText(), programInfo.getText(), artist.getText(), ((Facility) facility.getSelectionModel().getSelectedItem()).getFacilityType(), date.getValue(), LocalTime.parse(time.getText()));
                    getEvents().add(new EventNumberedSeating((ContactPerson) contactPerson.getSelectionModel().getSelectedItem(), (Facility) facility.getValue(), Double.parseDouble(ticketPrice.getText()), eventInfo));
                    eventFile.delete();
                    try {
                        WriterThreadRunner.WriterThreadRunner(getEvents(), EditedFiles.getActiveEventFile());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    showLabel(createEvLb);


                } catch (NumberFormatException e) { errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format\nBillettprisen må være en double Skriv prisen \npå følgende format 000.0");
                } catch (DateTimeParseException e) { errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format\nTiden er på feil format\n Tiden skal være på følgende format\n TT:mm");
                } catch (NullPointerException e) { errorBox("Tomme felter", "Alle felter er ikke utfylt", "Vennligst fyll ut alle felter før du fortsetter");
                } catch (IllegalArgumentException e){ errorBox("Opptatt lokale", "Dette lokalet er opptatt til angitt tid", "Vennligst velg et annet tidspunkt eller et annet lokale");
                } catch (Exception e) { errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format");
                }

            } else if (f.getMaxAntSeats() != 0) {
                try {
                    EventInfo eventInfo = new EventInfo(eventName.getText(), programInfo.getText(), artist.getText(), ((Facility) facility.getSelectionModel().getSelectedItem()).getFacilityType(), date.getValue(), LocalTime.parse(time.getText()));
                    getEvents().add(new EventFreeSeating((ContactPerson) contactPerson.getSelectionModel().getSelectedItem(), (Facility) facility.getValue(), Double.parseDouble(ticketPrice.getText()), eventInfo));
                    eventFile.delete();
                    try {
                        WriterThreadRunner.WriterThreadRunner(getEvents(), EditedFiles.getActiveEventFile());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    showLabel(createEvLb);




                } catch (NumberFormatException e) { errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format\nBillettprisen må være en double Skriv prisen \npå følgende format 000.0");
                } catch (DateTimeParseException e) { errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format\nTiden er på feil format\n Tiden skal være på følgende format\n TT:mm");
                } catch (NullPointerException e) { errorBox("Tomme felter", "Alle felter er ikke utfylt", "Vennligst fyll ut alle felter før du fortsetter");
                } catch (IllegalArgumentException e){errorBox("Opptatt lokale", "Dette lokalet er opptatt til angitt tid", "Vennligst velg et annet tidspunkt eller et annet lokale");
                } catch (Exception e) { errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format");
                }
            }
        }
    }
    /**updateEvent tries to update an event if selected in showeEvent scene.
    *This method throws exceptions for wrong input and missing input and displays it in an alert box.*/
    @FXML
    private void updateEvent(ActionEvent event) {
        try {
            thisEvent.setTicketPrice(Double.parseDouble(ticketPrice.getText()));
            thisEvent.getEventInfo().setEventName(eventName.getText());
            thisEvent.getEventInfo().setDate(date.getValue());
            thisEvent.getEventInfo().setTime(LocalTime.parse(time.getText()));
            thisEvent.getEventInfo().setPerformer(artist.getText());
            thisEvent.getEventInfo().setProgram(programInfo.getText());

            eventFile.delete();
            try {
                WriterThreadRunner.WriterThreadRunner(getEvents(), EditedFiles.getActiveEventFile());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            showLabel(createEvLb);

        } catch (NumberFormatException e) { errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format\nBillettprisen må være en double Skriv prisen \npå følgende format 000.0");
        } catch (NullPointerException e) { errorBox("Tomme felter", "Alle felter er ikke utfylt", "Vennligst fyll ut alle felter før du fortsetter");
        } catch (DateTimeParseException e) { errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format\nTiden er på feil format\n Tiden skal være på følgende format\n TT:mm");
        } catch (Exception e){ errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format");
        }
    }

    /**The createContactPerson method tries to create a contactPerson and throws an exception
    *if the input is wrong or missing. Th exceptions are shown in an alert box. When/if the
    *contactPerson is created, the createContact scene is closed.*/
    @FXML
    private void createContactPerson(ActionEvent event){
        try {
            ContactInfo contactInfo = new ContactInfo(email.getText(), phoneNumber.getText());
            getContactPeople().add(new ContactPerson(firstName.getText(), lastName.getText(), contactInfo));
            if (isNotEmptyString(company.getText()))
                getContactPeople().get(getContactPeople().size() - 1).setAffiliation(company.getText());
            if (isNotEmptyString(webPage.getText()))
                getContactPeople().get(getContactPeople().size() - 1).setWebPage(webPage.getText());
            if (isNotEmptyString(other.getText()))
                getContactPeople().get(getContactPeople().size() - 1).setNotes(other.getText());

            contactFile.delete();
            try {
                WriterThreadRunner.WriterThreadRunner(getContactPeople(), EditedFiles.getActiveContactFile());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            showLabel(createContLb);

            //TODO flytte dette over i Scene manager??
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/group38/chooseContact.fxml"));
            loader.setController(this);
            contactPersonPane.setRight(loader.load());
            setThisContactPerson(null);


            loadInfo();
        } catch (NullPointerException e) {
            errorBox("Tomme felter", "Alle felter er ikke utfylt", "Vennligst fyll ut alle felter før du fortsetter");
        } catch (Exception e) {
            errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format");
        }
    }

    /**The updateContactPerson goes to the scene for updating the contactPerson and runs the
    setter method for selected contactPerson.*/
    @FXML
    private void updateContactPerson(ActionEvent event){
        if(contactPerson.getSelectionModel().getSelectedItem()==null){
            errorBox("Feil", "Det er ingen kontaktperson som er markert", "Vennligst marker en kontaktperson du vil redigere");
        }
        else{
            setThisContactPerson((ContactPerson)contactPerson.getSelectionModel().getSelectedItem());
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/group38/newContact.fxml"));
                loader.setController(this);
                contactPersonPane.setRight(loader.load());
                setValuesContactPerson();
                createContact.setVisible(false);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**The updateContactPersonComplete method tries to set the new values to the selected contactPerson
    *and throws exceptions for missing input and for wrong input*/
    @FXML
    private void updateContactPersonComplete(ActionEvent event){
        try{
            ol.remove(thisContactPerson);
            thisContactPerson.setFirstName(firstName.getText());
            thisContactPerson.setLastName(lastName.getText());
            thisContactPerson.getContactInfo().setEmail(email.getText());
            thisContactPerson.getContactInfo().setPhoneNr(phoneNumber.getText());
            if(isNotEmptyString(other.getText())) thisContactPerson.setNotes(other.getText());
            if(isNotEmptyString(webPage.getText()))thisContactPerson.setWebPage(webPage.getText());
            if(isNotEmptyString(company.getText()))thisContactPerson.setAffiliation(company.getText());
            ol.add(thisContactPerson);
            contactFile.delete();
            try {
                WriterThreadRunner.WriterThreadRunner(getContactPeople(), fileNameC);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            showLabel(createContLb);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/group38/chooseContact.fxml"));
            loader.setController(this);
            contactPersonPane.setRight(loader.load());
            setThisContactPerson(null);
        }
        catch (NullPointerException e) { errorBox("Tomme felter", "Alle felter er ikke utfylt", "Vennligst fyll ut alle felter før du fortsetter");}
        catch (Exception e){ errorBox("Feil input", "Feil input i et eller flere felter", "Vennligst sørg for at alle felter har riktig format");
        }
    }
    /**The setValueContactPerson method is used for adding the information from
    *the selected contactperson to the editscene.*/
    private void setValuesContactPerson(){
        firstName.setText(thisContactPerson.getFirstName());
        lastName.setText(thisContactPerson.getLastName());
        email.setText(thisContactPerson.getEmail());
        company.setText(thisContactPerson.getAffiliation());
        phoneNumber.setText(thisContactPerson.getPhoneNr());
        other.setText(thisContactPerson.getNotes());
        webPage.setText(thisContactPerson.getWebPage());
    }
    /**checks if a row is selected, and then asks for permission to delete the object
     * if the user presses ok, the contactPerson selected is deleted */
    @FXML
    private void deleteRow(ActionEvent event){
        if(contactPerson.getSelectionModel().getSelectedItem()==null){
            errorBox("Feil", "Det er ingen kontaktperson som er markert", "Vennligst marker en kontaktperson du vil redigere");
        }
        else{
            Alert mb = new Alert(Alert.AlertType.CONFIRMATION);
            mb.setTitle("Bekreft");
            mb.setHeaderText("Du har trykket på slett kontaktperson");
            mb.setContentText("Ønsker du virkerlig å slette denne kontaktpersonen?");
            mb.showAndWait().ifPresent(response -> {
                if(response==ButtonType.OK){
                    ol.remove(contactPerson.getSelectionModel().getSelectedItem());

                    contactFile.delete();
                    try {
                        WriterThreadRunner.WriterThreadRunner(getContactPeople(), EditedFiles.getActiveContactFile());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }
    /**Setting the event to the event chosen in showEventController*/
    private void setThisEvent(Event thisEvent) {
        this.thisEvent = thisEvent;
    }
    /**setting the contactperson to bring the objekt over to the edit contactperson scene */
    private void setThisContactPerson(ContactPerson thisContactPerson) {
        this.thisContactPerson = thisContactPerson;
    }

    @Override
    public void exit() {
    }
}

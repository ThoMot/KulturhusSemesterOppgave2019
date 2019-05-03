package org.group38.kulturhus.model.ContactPerson;

import java.io.Serializable;

import static org.group38.frameworks.Validate.*;

public class ContactPerson extends Person implements Serializable {
    //Optional
    private String webPage;
    private String affiliation;
    private String notes;
    private ContactInfo contactInfo;


    /** Constructor for Contactperson
     * the empty constrctor is used for writing to file*/
    private ContactPerson(){
        super();
    }

    public ContactPerson(String firstName, String lastName, ContactInfo contactInfo){
        super(firstName, lastName);
        this.contactInfo = contactInfo;
    }

    /** Getter and setter for contactPersons webpage. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
    if(!isValidWebPage(webPage)) throw new IllegalArgumentException("Websiden er på feil format\n må avsluttes med feks .com/.no eller lignende");
        this.webPage = webPage;
    }

    /** Getter and setter for contactPersons affiliation. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    /** Getter and setter for contactPersons notes. */
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private void setContactInfo(String email, String phoneNr) {
        contactInfo.setEmail(email);
        contactInfo.setPhoneNr(phoneNr);
    }

    /** getter for Contactpersons phoneNumber for getting right information from eventInfo */
    public ContactInfo getContactInfo(){ return contactInfo; }

    public String getPhoneNr() { return getContactInfo().getPhoneNr(); }

    public String getEmail() { return getContactInfo().getEmail(); }


    @Override
    public String toString() {
        return "ContactPerson{" + " firstname= " + super.getFirstName() + " lastname= " + super.getLastName() +
                " webPage='" + webPage + '\'' +
                ", affiliation='" + affiliation + '\'' +
                ", notes='" + notes + '\'' +
                ", contactInfo=" + contactInfo.toString() +
                '}';
    }
}



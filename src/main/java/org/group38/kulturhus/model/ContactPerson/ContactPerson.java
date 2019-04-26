//legge inn if else i alle setmetoder for å sjekke for riktig input
//tostring metode

package org.group38.kulturhus.model.ContactPerson;

import org.group38.kulturhus.model.SaveLoad.CsvBase;

import static org.group38.kulturhus.model.Validate.isOnlyLetters;
import static org.group38.kulturhus.model.Validate.isValidWebPage;

public class ContactPerson extends Person implements CsvBase {
    //Optional
    private String webPage;
    private String affiliation;
    private String notes;
    private ContactInfo contactInfo;


public ContactPerson(String firstName, String lastName, ContactInfo contactInfo){
    super(firstName, lastName);
    this.contactInfo = contactInfo;
}

    public void setWebPage(String webPage) {
    if(!isValidWebPage(webPage)) throw new IllegalArgumentException("Websiden er på feil format");
        this.webPage = webPage;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }



    public String getWebPage() {
        return webPage;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getNotes() {
        return notes;
    }

    public ContactInfo getContactInfo(){ return contactInfo; }




    @Override
    public String toString() {
        return "ContactPerson{" + " firstname= " + super.getFirstName() + " lastname= " + super.getLastName() +
                " webPage='" + webPage + '\'' +
                ", affiliation='" + affiliation + '\'' +
                ", notes='" + notes + '\'' +
                ", contactInfo=" + contactInfo.toString() +
                '}';
    }

    @Override
    public String toCSV() {
        return null;
    }
}



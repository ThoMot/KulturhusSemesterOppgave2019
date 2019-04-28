//legge inn if else i alle setmetoder for å sjekke for riktig input
//tostring metode

package org.group38.kulturhus.model.ContactPerson;

import org.group38.kulturhus.model.SaveLoad.CsvBase;

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

private ContactPerson(){
    super();
}

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private void setContactInfo(String email, String phoneNr) {
        contactInfo.setEmail(email);
        contactInfo.setPhoneNr(phoneNr);
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

    @Override
    public String toCSV() {
        return null;
    }
}



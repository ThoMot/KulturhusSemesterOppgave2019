//legge inn if else i alle setmetoder for å sjekke for riktig input
//tostring metode

package org.group38.model.ContactPerson;

public class ContactPerson extends Person {
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

}



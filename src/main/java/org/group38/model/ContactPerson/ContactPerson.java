//legge inn if else i alle setmetoder for å sjekke for riktig input
//tostring metode

package org.group38.model.ContactPerson;

public class ContactPerson {
    //Optional
    private String webPage;
    private String affiliation;
    private String notes;


public ContactPerson(Personalia personalia, ContactInfo contactInfo){

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



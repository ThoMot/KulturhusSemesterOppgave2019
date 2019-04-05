//legge inn if else i alle setmetoder for å sjekke for riktig input
//tostring metode

package org.group38.model;

public class ContactPerson {
    //Required
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNr;
    //Optional
    private String webPage;
    private String affiliation;
    private String notes;


public ContactPerson(String firstName, String lastName, String email, String phoneNr){
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNr = phoneNr;
}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNr() {
        return phoneNr;
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

//legge inn if else i alle setmetoder for å sjekke for riktig input
//tostring metode

package org.group38.kulturhus.model.ContactPerson;

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

    public ContactInfo getContactInfo(){ return contactInfo; }

    public String getFirstName() { return super.getFirstName(); }

    public String getLastName() { return super.getLastName(); }


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



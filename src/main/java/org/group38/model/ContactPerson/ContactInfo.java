package org.group38.model.ContactPerson;

public class ContactInfo {
    private String email;
    private String phoneNr;

    public ContactInfo(String email, String phoneNr) {
        this.email = email;
        this.phoneNr = phoneNr;

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }


    @Override
    public String toString() {
        return "ContactInfo{" +
                "email='" + email + '\'' +
                ", phoneNr='" + phoneNr + '\'' +
                '}';
    }
}



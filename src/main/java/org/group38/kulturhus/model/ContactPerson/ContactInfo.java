package org.group38.kulturhus.model.ContactPerson;

import static org.group38.kulturhus.model.Validate.isValidEmail;
import static org.group38.kulturhus.model.Validate.validatePhoneNr;

public class ContactInfo {
    private String email;
    private String phoneNr;

    public ContactInfo(String email, String phoneNr) {
        if(!isValidEmail(email)) throw new IllegalArgumentException("Eposten er på feil format.\n Den skal bestå av to deler adskilt med @");
        if(!validatePhoneNr(phoneNr)) throw new IllegalArgumentException("Telefonnummer er ikke gyldig.\n Det skal kun bestå av 8 tall");
        this.email = email;
        this.phoneNr = phoneNr;

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!isValidEmail(email)) throw new IllegalArgumentException("Eposten er på feil format.\n Den skal bestå av to deler adskilt med @");
    this.email = email;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        if(!validatePhoneNr(phoneNr)) throw new IllegalArgumentException("Telefonnummer er ikke gyldig.\n Det skal kun bestå av 8 tall");
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



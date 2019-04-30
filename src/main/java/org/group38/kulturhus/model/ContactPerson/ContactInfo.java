package org.group38.kulturhus.model.ContactPerson;

import java.io.Serializable;

import static org.group38.kulturhus.model.Validate.*;

public class ContactInfo implements Serializable {
    private String email;
    private String phoneNr;

    public ContactInfo(String email, String phoneNr) {
      if(!isValidEmail(email.toLowerCase())) throw new IllegalArgumentException("Eposten er på feil format.\n Den skal bestå av to deler adskilt med @ \nog avslutte med .com/.no eller lignende");
       if(!isValidPhoneNr(phoneNr)) throw new IllegalArgumentException("Telefonnummer er ikke gyldig.\n Det skal kun bestå av 8 tall");
        this.email = email.toLowerCase();
        this.phoneNr = phoneNr;

    }

    public ContactInfo(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!isValidEmail(email.toLowerCase())) throw new IllegalArgumentException("Eposten er på feil format.\n Den skal bestå av to deler adskilt med @");
    this.email = email.toLowerCase();
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        if(!isValidPhoneNr(phoneNr)) throw new IllegalArgumentException("Telefonnummer er ikke gyldig.\n Det skal kun bestå av 8 tall");
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



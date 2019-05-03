package org.group38.kulturhus.model.ContactPerson;

import java.io.Serializable;

import static org.group38.frameworks.Validate.*;

public class ContactInfo implements Serializable {
    private String email;
    private String phoneNr;

    /** Constructor of ContactInfo
     * The empty constructer is used to write to file*/
    public ContactInfo(){
    }

    public ContactInfo(String email, String phoneNr) {
      if(!isValidEmail(email.toLowerCase())) throw new IllegalArgumentException("Eposten er på feil format.\n Den skal bestå av to deler adskilt med @ \nog avslutte med .com/.no eller lignende");
       if(!isValidPhoneNr(phoneNr)) throw new IllegalArgumentException("Telefonnummer er ikke gyldig.\n Det skal kun bestå av 8 tall");
        this.email = email.toLowerCase();
        this.phoneNr = phoneNr;

    }

    /** Getter and setter for contactPersons email. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!isValidEmail(email.toLowerCase())) throw new IllegalArgumentException("Eposten er på feil format.\n Den skal bestå av to deler adskilt med @");
    this.email = email.toLowerCase();
    }

    /** Getter and setter for contactPersons phoneNumber. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
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



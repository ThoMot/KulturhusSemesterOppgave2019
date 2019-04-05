package org.group38.model;

import org.group38.model.ContactPerson.ContactInfo;
import org.group38.model.ContactPerson.Person;

public class Performer extends Person {

    ContactInfo contactInfo;


    public Performer(String firstName, String lastName) {
        super(firstName, lastName);
    }


    @Override
    public String toString() {
        return "Performer{" +
                "contactInfo=" + contactInfo +
                '}';
    }
}

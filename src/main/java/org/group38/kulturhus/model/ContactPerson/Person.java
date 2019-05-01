package org.group38.kulturhus.model.ContactPerson;

import java.io.Serializable;

import static org.group38.kulturhus.Utilities.Validate.*;

public abstract class Person implements Serializable {

    private String firstName;
    private String lastName;


    public Person(String firstName, String lastName) {
        if(!isOnlyLetters(firstName)) throw new IllegalArgumentException("Fornavn kan kun inneholde bokstaver");
        if(!isNotEmptyString(firstName)) throw new NullPointerException("Fornavn må fylles ut");
        if(!isOnlyLetters(lastName)) throw new IllegalArgumentException("Etternavn kan kun inneholde boklstaver");
        if(!isNotEmptyString(lastName)) throw new NullPointerException("Etternavn må fylles ut");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected Person(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(!isOnlyLetters(firstName)) throw new IllegalArgumentException("Fornavn kan kun inneholde bokstaver");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(!isOnlyLetters(lastName)) throw new IllegalArgumentException("Etternavn kan kun inneholde boklstaver");
        this.lastName = lastName;
    }
}

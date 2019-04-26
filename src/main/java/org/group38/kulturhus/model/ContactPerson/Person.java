package org.group38.kulturhus.model.ContactPerson;

import static org.group38.kulturhus.model.Validate.isOnlyLetters;

public abstract class Person {

    private String firstName;
    private String lastName;


    public Person(String firstName, String lastName) {
        if(!isOnlyLetters(firstName)) throw new IllegalArgumentException("Fornavn kan kun inneholde bokstaver");
        if(!isOnlyLetters(lastName)) throw new IllegalArgumentException("Etternavn kan kun inneholde boklstaver");
        this.firstName = firstName;
        this.lastName = lastName;
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

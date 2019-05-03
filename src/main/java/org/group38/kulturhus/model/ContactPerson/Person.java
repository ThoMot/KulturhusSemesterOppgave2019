package org.group38.kulturhus.model.ContactPerson;

import javafx.collections.ObservableList;

import java.io.Serializable;

import static org.group38.frameworks.Validate.*;

public abstract class Person implements Serializable {
    //datafield
    private String firstName;
    private String lastName;


    /** Constructor for person
     * the empty constructorer is used for writing to file*/
    protected Person(){
    }
    public Person(String firstName, String lastName) {
        if(!isOnlyLetters(firstName)) throw new IllegalArgumentException("Fornavn kan kun inneholde bokstaver");
        if(!isNotEmptyString(firstName)) throw new NullPointerException("Fornavn må fylles ut");
        if(!isOnlyLetters(lastName)) throw new IllegalArgumentException("Etternavn kan kun inneholde boklstaver");
        if(!isNotEmptyString(lastName)) throw new NullPointerException("Etternavn må fylles ut");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /** Getter and setter for contactPersons firstname. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(!isOnlyLetters(firstName)) throw new IllegalArgumentException("Fornavn kan kun inneholde bokstaver");
        this.firstName = firstName;
    }

    /** Getter and setter for contactPersons lastName. To see validation go to -> org/group38/kulturhus/Utilities/Validate.java*/
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(!isOnlyLetters(lastName)) throw new IllegalArgumentException("Etternavn kan kun inneholde boklstaver");
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj.getClass()!=this.getClass()) return false;
        Person other = (Person) obj;
        return this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName);
    }
}

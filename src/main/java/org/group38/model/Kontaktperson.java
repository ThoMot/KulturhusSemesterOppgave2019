package org.group38.model;

public class Kontaktperson {
    //Required
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNr;
    //Optional
    private String webPage;
    private String affiliation;
    private String notes;





private Kontaktperson(Builder builder){
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.email = builder.email;
    this.phoneNr = builder.phoneNr;
    this.webPage = builder.webPage;
    this.affiliation = builder.affiliation;
    this.notes = builder.notes;

}


public static class Builder {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNr;
    //Optional
    private String webPage;
    private String affiliation;
    private String notes;

    public Builder firstName(final String firstName){
        this.firstName = firstName;
        return this;
    }

    public Builder lastname(final String lastName){
        this.lastName = lastName;
        return this;
    }

    public Builder email(final String email){
        this.email = email;
        return this;
    }

    public Builder phoneNr(final String phoneNr){
        this.phoneNr = phoneNr;
        return this;
    }

    public Builder webPage(final String webPage){
        this.webPage = webPage;
        return this;
    }

    public Builder affiliation(final String affiliation){
        this.affiliation = affiliation;
        return this;
    }

    public Builder notes(final String notes){
        this.notes = notes;
        return this;
    }

    public Kontaktperson build(){
        return new Kontaktperson(this);
    }


}



}

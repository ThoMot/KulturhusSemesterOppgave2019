package org.group38.kulturhus.model;

public class Validate {

    private static boolean validatePhoneNr(String phoneNr) {
        if (phoneNr.matches("\\d{10}")) return true;
        else return false;
    }
}

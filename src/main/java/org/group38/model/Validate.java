package org.group38.model;

public class Validate {

    private static boolean validatePhoneNr(String phoneNr) {
        if (phoneNr.matches("\\d{10}")) return true;
        else return false;
    }
}

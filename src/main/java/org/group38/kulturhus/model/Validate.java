package org.group38.kulturhus.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    public static boolean validatePhoneNr(String phoneNr) {
        if (phoneNr.matches("-?\\d+(\\.\\d+)?")) {
            if (phoneNr.length() == 8) return true;
            else return false;
        }
        else return false;
    }
    public static boolean isOnlyLetters(String string){
        char[] chars = string.toCharArray();
        for(char c : chars){
            if(!Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }
    public static boolean isValidEmail(String email){
        Pattern regex = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$");
        Matcher matcher=regex.matcher(email);
        return ((Matcher) matcher).find();
    }

    public static boolean isValidWebPage(String webPage){
        Pattern regex = Pattern.compile("^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$");
        Matcher matcher=regex.matcher(webPage);
        return matcher.find();
    }

}


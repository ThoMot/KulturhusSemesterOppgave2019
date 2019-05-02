package org.group38.kulturhus.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
/** the validation methods checks if the input matches a regex
 * or some ither criteria and returns true or false based on the outcome*/
    public static boolean isValidPhoneNr(String phoneNr) {
        if (phoneNr.matches("-?\\d+(\\.\\d+)?")) {
            if (phoneNr.length() == 8) return true;
            else return false;
        }
        else return false;
    }
    public static boolean isOnlyLetters(String str){
        char[] chars = str.toCharArray();
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
    public static boolean isValidTime(String time){
        if(time.matches("(?:[0-1][0-9]|2[0-4]):[0-5]\\d")){
            return true;
        }
        else return false;
    }
    public static boolean isNotEmptyString(String str){
        if(str != null && !str.isEmpty()) return true;
        else return false;
    }
    public static boolean isValidNumber(int number){
        if(number>1) return true;
        else return false;
    }
}


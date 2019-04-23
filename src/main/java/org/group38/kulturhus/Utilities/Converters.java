package org.group38.kulturhus.Utilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Converters {
    public static LocalDate StringtoLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
    public static LocalTime StringtoLocalTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM:yy");
        LocalTime localTime = LocalTime.parse(time, formatter);
        return localTime;
    }
    public static double StringtoDouble(String price){
        return Double.parseDouble(price);
    }
}

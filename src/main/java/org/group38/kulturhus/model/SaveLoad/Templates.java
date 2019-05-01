package org.group38.kulturhus.model.SaveLoad;

import org.group38.kulturhus.model.Exeptions.NoSuchSubclassException;

import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Templates {
    public static final String ens = "org.group38.kulturhus.model.Event.EventNumberedSeating";
    public static final String efs = "org.group38.kulturhus.model.Event.EventFreeSeating";
    private static final String ticket = "org.group38.kulturhus.model.Event.Ticket";
    private static final String contact = "org.group38.kulturhus.model.ContactPerson.ContactPerson";
    private static final String contactInfo = "org.group38.kulturhus.model.ContactPerson.ContactInfo";
    private static final String facility = "org.group38.kulturhus.model.facility.Facility";
    private static final String person = "org.group38.kulturhus.model.ContactPerson.Person";
    private static final String eventInfo = "org.group38.kulturhus.model.Event.EventInfo";







    //Sjekker hvilket pattern objektet skal bruke
    public static String[] getterPattern(Class clazz) {
        String[] patterns;
        if (clazz.getName().equals(ens)
                || clazz.getName().equals(efs)) {
            patterns = new String[] {"getPhoneNr", "getFacilityName", "getEventId", "getEventName", "getType",
                    "getProgram", "getPerformer", "getTicketPrice",
                    "getDate", "getTime"};
            return patterns;
        } else if(clazz.getName().equals(ticket)){
            patterns = new String[] { "getPhonenumber", "getEventId", "getPrice", "getSeat", "getRow", "getDate", "getTime"};
            return patterns;
        } else if(clazz.getName().equals(contact)) {
            patterns = new String[]{"getPhoneNr", "getFirstName", "getLastName", "getEmail", "getNotes", "getAffiliation", "getWebPage"};
            return patterns;
        } else if(clazz.getName().equals(facility)) {
            patterns = new String[]{"getFacilityName", "getFacilityType", "getMaxAntSeats", "getRows", "getColumns"};
            return patterns;
        } else return null;
    }

     private String facilityName;
     private String facilityType;
     private int maxAntSeats;
     private int rows;
     private int columns;



    static String[] setterPattern(Class clazz){
            String[] patterns;
            if (clazz.getName().equals(ens)
                    || clazz.getName().equals(efs)) {
                patterns = new String[] {"setPhoneNr", "setEventId", "setEventName", "setType",
                        "setProgram", "setPerformer", "setTicketPrice", "setFacilityName", "setMaxSeats",
                        "setDate", "setTime", "setRows", "setColumns"};
                return patterns;
            } else if(clazz.getName().equals(ticket)){
                patterns = new String[] { "setPhonenumber", "setPrice", "setSeat", "setRow", "setDate", "setTime"};
                return patterns;
            } else if(clazz.getName().equals(contact)){
                patterns = new String[]{"setPhoneNr", "setFirstName", "setLastName", "setEmail", "setNotes", "setAffiliation", "setWebPage"};
                return patterns;
            } else return null;
        }


    static String[] readerPattern(Class clazz){
        String[] patterns;
        if (clazz.getName().equals(ens)
                || clazz.getName().equals(efs)) {
            patterns = new String[] {"setPhoneNr", "setEventId", "setEventName", "setType",
                    "setProgram", "setPerformer", "setTicketPrice", "setFacilityName", "setMaxSeats",
                    "setDate", "setTime", "setRows", "setColumns"};
            return patterns;
        } else if(clazz.getName().equals(ticket)){
            patterns = new String[] { "setPhonenumber", "setPrice", "setSeat", "setRow", "setDate", "setTime"};
            return patterns;
        } else if(clazz.getName().equals(contact)){
            patterns = new String[]{contactInfo, person, person, contactInfo, contact, contact, contact};
            return patterns;
        } else return null;
    }

    public static boolean isBigObject(Class clazz) {
        return  (clazz.getName().equals(ens) || clazz.getName().equals(efs));
    }

    public static LinkedHashMap<String, Integer> getEventInfo(LinkedHashMap<String, Integer> eventMap){
        eventMap.remove("phoneNr");
        eventMap.remove("facilityName");

        return eventMap;
    }

    public static Class getSubClass(Class clazz) throws ClassNotFoundException {
        if (clazz.getName().equals(efs) | clazz.getName().equals(ens))

            return Class.forName(eventInfo);
        else return null;//throw new NoSuchSubclassException("There is no such subclass");
    }



}

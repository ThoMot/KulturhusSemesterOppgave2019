package org.group38.kulturhus.model.SaveLoad;

 abstract class Templates {
    private static final String ens = "org.group38.kulturhus.model.Event.EventNumberedSeating";
    private static final String efs = "org.group38.kulturhus.model.Event.EventFreeSeating";
    private static final String ticket = "org.group38.kulturhus.model.Event.Ticket";
    private static final String contact = "org.group38.kulturhus.model.ContactPerson.ContactPerson";
    private static final String contactInfo = "org.group38.kulturhus.model.ContactPerson.ContactInfo";
    private static final String eventInfo = "org.group38.kulturhus.model.Event.EventInfo";
    private static final String person = "org.group38.kulturhus.model.ContactPerson.Person";







    //Sjekker hvilket pattern objektet skal bruke
    public static String[] getterPattern(Class clazz) {
        String[] patterns;
        if (clazz.getName().equals(ens)
                || clazz.getName().equals(efs)) {
            patterns = new String[] {"getPhoneNr", "getEventId", "getEventName", "getType",
                    "getProgram", "getPerformer", "getTicketPrice", "getFacilityName", "getMaxSeats",
                    "getDate", "getTime", "getRows", "getColumns"};
            return patterns;
        } else if(clazz.getName().equals(ticket)){
            patterns = new String[] { "getPhonenumber", "getPrice", "getSeat", "getRow", "getDate", "getTime"};
            return patterns;
        } else if(clazz.getName().equals(contact)){
            patterns = new String[]{"getPhoneNr", "getFirstName", "getLastName", "getEmail", "getNotes", "getAffiliation", "getWebPage"};
            return patterns;
        } else return null;
    }

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


}
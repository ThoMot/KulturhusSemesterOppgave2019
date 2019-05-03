package org.group38.kulturhus.model.FilePaths;

import org.group38.frameworks.Exeptions.WrongFileFormatException;

public class EditedFiles {
    private static String eventCSV;
    private static String eventJOBJ;
    private static String contactCSV;
    private static String contactJOBJ;
    private static String facilityCSV;
    private static String facilityJOBJ;
    private static String ticketCSV;
    private static String ticketJOBJ;
    private static String activeEventFile;
    private static String activeFacilityFile;
    private static String activeContactFile;
    private static String activeTicketFile;
    
    public static void setEventsCSV(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            eventCSV = null;
            return;
        }
        if(isCSV(fileName)){
            eventCSV = fileName;
            activeEventFile = fileName;
        } else throw new WrongFileFormatException("Venligst velg en CSV fil");
    }

    public static void setEventsJOBJ(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            eventJOBJ = null;
            return;
        }
        if(isJOBJ(fileName)){
            eventJOBJ = fileName;
            activeEventFile = fileName;
        } else throw new WrongFileFormatException("Venligst velg en JOBJ fil");
    }

    public static void setContactCSV(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            contactCSV = null;
            return;
        }
        if(isCSV(fileName)){
            contactCSV = fileName;
            activeContactFile = fileName;
        } else throw new WrongFileFormatException("Venligst velg en CSV fil");
    }

    public static void setContactJOBJ(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            contactJOBJ = null;
            return;
        }
        if(isJOBJ(fileName)){
            contactJOBJ = fileName;
            activeContactFile = fileName;
        } else throw new WrongFileFormatException("Venligst velg en JOBJ fil");
    }

    public static void setFacilitysCSV(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            facilityCSV = null;
            return;
        }
        if(isCSV(fileName)){
            facilityCSV = fileName;
            activeFacilityFile = fileName;
        } else throw new WrongFileFormatException("Venligst velg en CSV fil");
    }

    public static void setFacilityJOBJ(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            facilityJOBJ = null;
            return;
        }
        if(isJOBJ(fileName)){
            facilityJOBJ = fileName;
            activeFacilityFile = fileName;
        } else throw new WrongFileFormatException("Venligst velg en JOBJ fil");
    }

    public static void setTicketCSV(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            ticketCSV = null;
            return;
        }
        if(isCSV(fileName)){
            ticketCSV = fileName;
            activeTicketFile = fileName;
        } else throw new WrongFileFormatException("Venligst velg en CSV fil");
    }

    public static void setTicketJOBJ(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            ticketJOBJ = null;
            return;
        }
        if(isJOBJ(fileName)){
            ticketJOBJ = fileName;
            activeTicketFile = fileName;
        } else throw new WrongFileFormatException("Venligst velg en JOBJ fil");
    }

    private static boolean isCSV(String fileName){
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        return (ext.equals("csv"));

    }

    private static boolean isJOBJ(String fileName){
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        return (ext.equals("jobj"));
    }

    public static String getActiveEventFile(){
        if(activeEventFile !=null){
            return activeEventFile;
        } else return DefaultFiles.EVENTJOBJ.getFileName();
    }

    public static String getActiveFacilityFile(){
        if(activeFacilityFile !=null){
            return activeFacilityFile;
        } else return DefaultFiles.FACILITYJOBJ.getFileName();
    }

    public static String getActiveContactFile(){
        if(activeContactFile !=null){
            return activeContactFile;
        } else return DefaultFiles.CONTACTJOBJ.getFileName();
    }

    public static String getActiveTicketFile(){
        if(activeTicketFile !=null){
            return activeTicketFile;
        } else return DefaultFiles.TICKETJOBJ.getFileName();
    }

    public static String getEventCSV(){
        if(eventCSV != null){
            return eventCSV;
        } else return DefaultFiles.EVENTCSV.getFileName();
    }

    public static String getEventJOBJ(){
        if(eventJOBJ != null){
            return eventJOBJ;
        } else return DefaultFiles.EVENTJOBJ.getFileName();
    }

    public static String getContactCSV(){
        if(contactCSV != null){
            return contactCSV;
        } else return DefaultFiles.CONTACTCSV.getFileName();
    }

    public static String getContactJOBJ(){
        if(contactJOBJ != null){
            return contactJOBJ;
        } else return DefaultFiles.CONTACTJOBJ.getFileName();

    }

    public static String getFacilityCSV(){
        if(facilityCSV != null){
            return facilityCSV;
        } else return DefaultFiles.FACILITYCSV.getFileName();

    }

    public static String getFacilityJOBJ(){
        if(facilityJOBJ != null){
            return facilityJOBJ;
        } else return DefaultFiles.FACILITYJOBJ.getFileName();
    }

    public static String getTicketCSV(){
        if(ticketCSV != null){
            return ticketCSV;
        } else return DefaultFiles.TICKETCSV.getFileName();

    }

    public static String getTicketJOBJ(){
        if(ticketJOBJ != null){
            return ticketJOBJ;
        } else return DefaultFiles.TICKETJOBJ.getFileName();

    }

}

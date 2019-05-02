package org.group38.kulturhus.model;

import org.group38.kulturhus.model.Exeptions.WrongFileFormatException;
import org.group38.kulturhus.model.SaveLoad.Save.WriteToCSV;
import org.group38.kulturhus.model.SaveLoad.Save.WriterJOBJ;

import java.util.SplittableRandom;

public class EditedFiles {

    private static String eventCSV;
    private static String eventJOBJ;
    private static String contactCSV;
    private static String contactJOBJ;
    private static String facilityCSV;
    private static String facilityJOBJ;
    private static String ticketCSV;
    private static String ticketJOBJ;

    public static void setEventsCSV(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            eventCSV = null;
            return;
        }
        if(isCSV(fileName)){
            eventCSV = fileName;
        } else throw new WrongFileFormatException("Venligst velg en CSV fil");
    }

    public static void setEventsJOBJ(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            eventJOBJ = null;
            return;
        }
        if(isJOBJ(fileName)){
            eventJOBJ = fileName;
        } else throw new WrongFileFormatException("Venligst velg en JOBJ fil");
    }

    public static void setContactCSV(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            contactCSV = null;
            return;
        }
        if(isCSV(fileName)){
            contactCSV = fileName;
        } else throw new WrongFileFormatException("Venligst velg en CSV fil");
    }

    public static void setContactJOBJ(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            contactJOBJ = null;
            return;
        }
        if(isJOBJ(fileName)){
            contactJOBJ = fileName;
        } else throw new WrongFileFormatException("Venligst velg en JOBJ fil");
    }

    public static void setFacilitysCSV(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            facilityCSV = null;
            return;
        }
        if(isCSV(fileName)){
            facilityCSV = fileName;
        } else throw new WrongFileFormatException("Venligst velg en CSV fil");
    }

    public static void setFacilityJOBJ(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            facilityJOBJ = null;
            return;
        }
        if(isJOBJ(fileName)){
            facilityJOBJ = fileName;
        } else throw new WrongFileFormatException("Venligst velg en JOBJ fil");
    }

    public static void setTicketCSV(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            ticketCSV = null;
            return;
        }
        if(isCSV(fileName)){
            ticketCSV = fileName;
        } else throw new WrongFileFormatException("Venligst velg en CSV fil");
    }

    public static void setTicketJOBJ(String fileName) throws WrongFileFormatException {
        if(fileName == null){
            ticketJOBJ = null;
            return;
        }
        if(isJOBJ(fileName)){
            ticketJOBJ = fileName;
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

package org.group38.kulturhus.model.FilePaths;

public enum DefaultFiles {
    EVENTCSV("Files/Events.csv"),
    TICKETCSV("Files/Tickets.csv"),
    CONTACTCSV("Files/Contacts.csv"),
    FACILITYCSV("Files/Facilities.csv"),
    EVENTJOBJ("Files/Events.jobj"),
    TICKETJOBJ("Files/Tickets.jobj"),
    CONTACTJOBJ("Files/Contacts.jobj"),
    FACILITYJOBJ("Files/Facilities.jobj");

    private String fileName;

    DefaultFiles(String filePath){
        this.fileName = filePath;
    }

    public String getFileName(){
        return fileName;
    }


}

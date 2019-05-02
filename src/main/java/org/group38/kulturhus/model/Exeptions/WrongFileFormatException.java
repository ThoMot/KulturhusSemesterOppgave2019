package org.group38.kulturhus.model.Exeptions;

public class WrongFileFormatException extends Exception {

    public WrongFileFormatException(String error){
        super(error);
    }
}

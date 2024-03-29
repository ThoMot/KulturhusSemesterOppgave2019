package org.group38.frameworks.SaveLoad.Load;

import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.FilePaths.EditedFiles;
import org.group38.kulturhus.model.Event.Ticket;
import org.group38.frameworks.Exceptions.ParsingException;
import org.group38.kulturhus.Utilities.Templates;
import org.group38.kulturhus.model.facility.Facility;

import java.io.*;
import java.lang.reflect.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ReadCSV implements ReaderInterface {
    CsvParser csvParser = new CsvParser();



    /** Method is called to decide if the objects to be read are smaller objects of bigger more complex objects */
    public <T> ArrayList<T> readObjects(String filename) throws ParsingException, IOException, ReflectiveOperationException {
        List<List<String>> records = csvParser.parseCsv(filename);


        List<String> headers = csvParser.getHeaders();
        ArrayList<T> returnObj = new ArrayList<>();

        Class clazz = Class.forName(records.get(0).get(0));
        Class parentclazz = clazz.getSuperclass();
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        Field[] fields = clazz.getDeclaredFields();
        Field[] parentFields = parentclazz.getDeclaredFields();

        headers = (convertToLowerCase(headers));


        if(Templates.isBigObject(clazz)){
            returnObj = readBigObject(filename);
        } else returnObj = readSmallObjects(filename);

        return returnObj;
    }



    /** This methods will read from CSV if the object is a small Object. It fully uses reflection and works for all
     * small non complex objects and objects containing one object. */
    private static <T> ArrayList<T> readSmallObjects(String filename) throws ParsingException, IOException, ReflectiveOperationException {
        File file = new File(filename);
        if (file.length() == 0) {
            return new ArrayList<>();
        }

        CsvParser csvParser = new CsvParser();
        List<List<String>> records = csvParser.parseCsv(filename);
        List<String> headers = csvParser.getHeaders();
        ArrayList<T> returnObj = new ArrayList<>();

        Class clazz = Class.forName(records.get(0).get(0));
        Class parentclazz = clazz.getSuperclass();
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        Field[] fields = clazz.getDeclaredFields();
        Field[] parentFields = parentclazz.getDeclaredFields();

        headers = (convertToLowerCase(headers));

        HashMap<String, Integer> settableValues;
        HashMap<String, Integer> parentValues;
        HashMap<String, Integer> otherValues = new HashMap<>();

        //setter maps for verdier
        settableValues = setMap(fields, headers);
        parentValues = setMap(parentFields, headers);

        for (int i = 1; i<headers.size(); i++) {
            if (!settableValues.containsKey(headers.get(i)) && !parentValues.containsKey(headers.get(i))) {
                otherValues.put(headers.get(i), headers.indexOf(headers.get(i)));
            }
        }

        for (List<String> objVal : records) {
            T test = (T) constructor.newInstance();
            //set settable values
            setFields(settableValues, clazz, test, objVal);
            //SetParent values
            setFields(parentValues, parentclazz, test, objVal);
            //SetOther Values
            if (!otherValues.isEmpty()) {
                setComplexFields(fields, clazz, test, otherValues, objVal);
            }
            returnObj.add(test);
        }
        return returnObj;
    }


    /**This class makes a big object in our case an Event that has multiple complex objects in several layers.
     * Unfortunately this method is not as generic as we wanted to due to lack of time. Refactoring has also been
     * something we did not have time to do. It is however fully working and semi-generic.
     */
    private static <T> ArrayList<T> readBigObject(String filename) throws ParsingException, IOException, ReflectiveOperationException {

        CsvParser csvParser = new CsvParser();

        List<List<String>> records = csvParser.parseCsv(filename);
        List<String> headers = csvParser.getHeaders();
        ArrayList<T> returnObj = new ArrayList<>();

        Class clazz = Class.forName(records.get(0).get(0));
        Class parentclazz = clazz.getSuperclass();

        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Field[] parentFields = parentclazz.getDeclaredFields();

        headers = (convertToLowerCase(headers));
        HashMap<String, Integer> parentValues;
        LinkedHashMap<String, Integer> otherValues = new LinkedHashMap<>();

        //setter maps for verdier
        // settableValues = setMap(fields, headers);
        parentValues = setMap(parentFields, headers);

        for (int i = 1; i < headers.size(); i++) {
            if (!parentValues.containsKey(headers.get(i))) {
                otherValues.put(headers.get(i), headers.indexOf(headers.get(i)));
            }
        }

        LinkedHashMap<String, Integer> searchValues = new LinkedHashMap<>();

        for (int i = 1; i < 4; i++) {
            searchValues.put(headers.get(i), headers.indexOf(headers.get(i)));
        }


        for (Map.Entry<String, Integer> entry : otherValues.entrySet());

        Class subclass = Templates.getSubClass(clazz);
        otherValues = Templates.getEventInfo(otherValues);


        for (List<String> objVal : records) {

            T test = (T) constructor.newInstance();

            setFields(parentValues, parentclazz, test, objVal);
            if (!otherValues.isEmpty()) {
                Constructor subCons = subclass.getDeclaredConstructor();
                subCons.setAccessible(true);

                Field field = parentclazz.getDeclaredField("eventInfo");
                field.setAccessible(true);

                field.set(test, setComplexObj(otherValues, subclass, objVal));

            }

            ArrayList<ContactPerson> ContactPerson = readSmallObjects(EditedFiles.getContactCSV());
            ArrayList<Facility> Facility = readSmallObjects(EditedFiles.getFacilityCSV());
            ArrayList<Ticket> Tickets = readSmallObjects(EditedFiles.getTicketCSV());



            for(ContactPerson c : ContactPerson){
                if (c.getPhoneNr().equals(objVal.get(searchValues.get("phoneNr")))){
                    Field field = parentclazz.getDeclaredField("contactPerson");
                    field.setAccessible(true);
                    field.set(test, c);
                }
            }

            for(Facility f : Facility){
                if (f.getFacilityName().equals(objVal.get(searchValues.get("facilityName")))){
                    Field field = parentclazz.getDeclaredField("facility");
                    field.setAccessible(true);
                    field.set(test, f);
                }
            }

            ArrayList<Ticket> belonging = new ArrayList<>();
            for(Ticket c : Tickets){
                if (c.getEventId().equals(objVal.get(searchValues.get("eventId")))){
                    belonging.add(c);
                }
            }
            Field field = clazz.getDeclaredField("tickets");
            field.setAccessible(true);
            field.set(test, belonging);

            returnObj.add(test);
        }
        return returnObj;
    }



    //setting maps for different field types
    /** This method sets hasmaps for different fieldtypes. Class fieldds and Parent fields are added to maps with their
     * positions mapped to where they are found in header
     */
    private static HashMap<String, Integer> setMap(Field[] fields, List<String> headers){
        HashMap<String, Integer> map = new HashMap<>();
        for (Field field : fields) {
            if (headers.contains(field.getName())) {
                int index = headers.indexOf(field.getName());
                map.put(field.getName(), index);
            }
        }
        return map;
    }


    /**makes a map of the subclasses that needs to be set for the object we are returning from the reader */
    private static HashMap<Field, Class> listSubclasses(Field[] fields) {
        HashMap<Field, Class> subClazz = new HashMap<>();

        for (Field checkField : fields) {
            if (!checkField.getType().isPrimitive() && checkField.getType() != String.class &&
                    !subClazz.containsKey(checkField.getType())) {
                subClazz.put(checkField, checkField.getType());
            }
        } return subClazz;
    }


    /** Sets the complex fields of the return object. That is the values of the objects contained in out return object. */
    private static <T> void setComplexFields(Field[] fields, Class clazz, T instance, HashMap<String,
            Integer> otherValues, List<String> objVal) throws ReflectiveOperationException{
        for (Map.Entry<Field, Class> entry : listSubclasses(fields).entrySet()) {
            Field field = clazz.getDeclaredField(entry.getKey().getName());
            field.setAccessible(true);
            field.set(instance, setComplexObj(otherValues, entry.getValue(), objVal));
        }
    }



    /** converts the header values to lower case so they can be used to set the fields of the class,
     * parentclass and contained objects */
    private static List<String> convertToLowerCase(List<String> headers) {
        List<String> fieldNames = new ArrayList<>();
        String firstLetter;
        for (String header : headers) {
            firstLetter = header.substring(0, 1).toLowerCase();
            fieldNames.add(firstLetter + header.substring(1));
        }
        return fieldNames;
    }


    /**Takes in the map of values that does not pertain to the contained objects and sets them */
    private static <T> void setFields(HashMap<String, Integer> map, Class clazz, T instance, List<String> csvObject) throws NoSuchFieldException, IllegalAccessException{
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Field fieldToSet = clazz.getDeclaredField(entry.getKey());
            fieldToSet.setAccessible(true);
            if (fieldToSet.getType().isPrimitive()) {
                setPrimitive(instance, fieldToSet, csvObject.get((entry.getValue())));
            } else setNonPrimitive(instance, fieldToSet, csvObject.get((entry.getValue())));
        }
    }

    /**Instantiates the objects used to make the return objects. These are the objects that are used to make the returnable object*/
    private static <T> T instantateComplexObj(Class clazz) throws NoSuchMethodException, IllegalAccessException, ReflectiveOperationException{
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        T obj = (T) constructor.newInstance();
        return obj;
    }


    /** sets the compllex fields to the complex object */
    private static <T> T setComplexObj(HashMap<String, Integer> otherValues, Class clazz, List<String> objVal) throws NoSuchMethodException, ReflectiveOperationException {
        T instance = instantateComplexObj(clazz);
        for (Map.Entry<String, Integer> entry : otherValues.entrySet()) {
            Field fieldToSet = clazz.getDeclaredField(entry.getKey());
            fieldToSet.setAccessible(true);
            if (fieldToSet.getType().isPrimitive()) {
                setPrimitive(instance, fieldToSet, (objVal.get(entry.getValue())));
            } else setNonPrimitive(instance, fieldToSet, (objVal.get(entry.getValue())));
        }
        return instance;
    }


    /** Sets non primitive values to the objevt passed as parameteer */
    private static <T> void setNonPrimitive(T instance, Field field, String value) throws IllegalAccessException, NumberFormatException{
        String typeName = field.getType().getName();
        if(typeName.equals(String.class.getName())){
            field.set(instance, value);
        } else if(typeName.equals(LocalDate.class.getName())){
            field.set(instance,  LocalDate.parse(value));
        } else if(typeName.equals(LocalTime.class.getName())){
            field.set(instance,  LocalTime.parse(value));
        } else if (typeName.equals((UUID.class.getName()))){
            field.set(instance, UUID.fromString(value));
        }
    }


    /** sets primitive fields for the object passed as parameter */
    private static <T> void setPrimitive(T instance, Field field, String value) throws IllegalAccessException, NumberFormatException {
        String typeName = field.getType().getName();

        if (typeName.equals(long.class.getName())) {
            field.setLong(instance, Long.parseLong(value));
        } else if (typeName.equals(int.class.getName())) {
            field.setInt(instance, Integer.parseInt(value));
        } else if (typeName.equals(boolean.class.getName())) {
            field.setBoolean(instance, Boolean.parseBoolean(value));
        } else if (typeName.equals(double.class.getName())) {
            field.setDouble(instance, Double.parseDouble(value));
        } else if (typeName.equals(float.class.getName())) {
            field.setFloat(instance, Float.parseFloat(value));
        } else if (typeName.equals(short.class.getName())) {
            field.setShort(instance, Short.parseShort(value));
        } else if (typeName.equals(byte.class.getName())) {
            field.setByte(instance, Byte.parseByte(value));
        } else if (typeName.equals(char.class.getName())) {
            field.setChar(instance, value.length() == 0 ? ' ' : value.charAt(0));
        }
    }
}

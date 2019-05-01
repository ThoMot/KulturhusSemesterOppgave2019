package org.group38.kulturhus.model.SaveLoad.Load;

import org.group38.kulturhus.model.Exeptions.ParsingException;
import org.group38.kulturhus.model.SaveLoad.Templates;

import java.io.*;
import java.lang.reflect.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ReadCSV implements ReaderInterface {
    String filename;
    String[] classes = new String[]{};



    public <T> void checkClass(String filename, Class clazz){
        if(clazz == Templates.efs.getClass() || clazz == Templates.ens.getClass()){
            ArrayList<T> list = new ArrayList<>();

        }

    }

    public <T> ArrayList<T> readObjects(String filename) throws ParsingException, IOException, ReflectiveOperationException {
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


        if(Templates.isBigObject(clazz)){
            returnObj = readBigObject(filename);
        } else returnObj = readSmallObjects(filename);

        return returnObj;
    }

    public static <T> ArrayList<T> readBigObject(String filename) throws ParsingException, ReflectiveOperationException {
        ArrayList n = new ArrayList();
return n;
    }



    public static <T> ArrayList<T> readSmallObjects(String filename) throws ParsingException, IOException, ReflectiveOperationException {
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



    //setting maps for different field types
    private static HashMap<String, Integer> setMap(Field[] fields, List<String> headers){
        HashMap<String, Integer> map = new HashMap<>();
        for (Field field : fields) {
            if (headers.contains(field.getName())) {
                int index = headers.indexOf(field.getName());
                System.out.println(field + " vi kan sette dette feltet " + index);
                map.put(field.getName(), index);
            }
        }
        return map;
    }


    //Makes a list of the subclasses that need to be set
    private static HashMap<Field, Class> listSubclasses(Field[] fields) {
        HashMap<Field, Class> subClazz = new HashMap<>();

        for (Field checkField : fields) {
            if (!checkField.getType().isPrimitive() && checkField.getType() != String.class &&
                    !subClazz.containsKey(checkField.getType())) {
                subClazz.put(checkField, checkField.getType());
            }
        } return subClazz;
    }

    //sets the complex fields of the return object
    private static <T> void setComplexFields(Field[] fields, Class clazz, T instance, HashMap<String,
            Integer> otherValues, List<String> objVal) throws ReflectiveOperationException{
        for (Map.Entry<Field, Class> entry : listSubclasses(fields).entrySet()) {
            Field field = clazz.getDeclaredField(entry.getKey().getName());
            field.setAccessible(true);
            field.set(instance, getComplexObj(otherValues, entry.getValue(), objVal));
        }
    }



    private static List<String> convertToLowerCase(List<String> headers) {
        List<String> fieldNames = new ArrayList<>();
        String firstLetter;
        for (String header : headers) {
            firstLetter = header.substring(0, 1).toLowerCase();
            fieldNames.add(firstLetter + header.substring(1));
        }
        return fieldNames;
    }


    private static <T> void setFields(HashMap<String, Integer> map, Class clazz, T instance, List<String> csvObject) throws NoSuchFieldException, IllegalAccessException{
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Field fieldToSet = clazz.getDeclaredField(entry.getKey());
            fieldToSet.setAccessible(true);
            if (fieldToSet.getType().isPrimitive()) {
                setPrimitive(instance, fieldToSet, csvObject.get((entry.getValue())));
            } else if(fieldToSet.getType() == String.class) {
                fieldToSet.set(instance, (csvObject.get(entry.getValue())));
            } else setNonPrimitive(instance, fieldToSet, csvObject.get((entry.getValue())));
        }
    }


    private static <T> T getComplexObj(HashMap<String, Integer> otherValues, Class sub, List<String> objVal) throws NoSuchMethodException, ReflectiveOperationException {

        Constructor<T> constructor = sub.getDeclaredConstructor();
        constructor.setAccessible(true);
        T obj = (T) constructor.newInstance();

        for (Map.Entry<String, Integer> entry : otherValues.entrySet()) {
            Field fieldToSet = sub.getDeclaredField(entry.getKey());
            fieldToSet.setAccessible(true);
            fieldToSet.set(obj, (objVal.get(entry.getValue())));
        }
        return obj;
    }


    private static <T> void setNonPrimitive(T instance, Field field, String value) throws IllegalAccessException, NumberFormatException{
        String typeName = field.getType().getName();
        if(typeName.equals(LocalDate.class.getName())){
            field.set(instance,  LocalDate.parse(value));
        } else if(typeName.equals(LocalTime.class.getName())){
            field.set(instance,  LocalTime.parse(value));
        }
    }


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

package org.group38.kulturhus.model.SaveLoad;

import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.EventNumberedSeating;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ReadCSV {
    String filename;
    String[] classes = new String[]{};


    public static boolean isSetter(Method method) {
        return Modifier.isPublic(method.getModifiers()) &&
                method.getReturnType().equals(void.class) &&
                method.getParameterTypes().length == 1 &&
                method.getName().matches("^set[A-Z].*");
    }


    private static List<String> toLowerCase(List<String> headers) {
        List<String> fieldNames = new ArrayList<>();
        String firstLetter = null;
        for (String header : headers) {
            firstLetter = header.substring(0, 1).toLowerCase();
            fieldNames.add(firstLetter + header.substring(1));
        }
        return fieldNames;
    }


    public static <T> ArrayList<T> readObjects(String filename, Class clazz) throws IOException, ReflectiveOperationException {

        List<List<String>> records = new ArrayList<>();
        List<String> headers;
        Class parentclazz = clazz.getSuperclass();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String headerline;
            headerline = br.readLine();
            String[] head = headerline.split(";");
            headers = Arrays.asList(head);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }


        ArrayList<T> returnObj = new ArrayList<>();


        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        System.out.println(headers);
        Field[] fields = clazz.getDeclaredFields();

        headers = (toLowerCase(headers));


        HashMap<String, String> map = new HashMap<String, String>();

        String[] template = Templates.readerPattern(clazz);

        for (int i = 0; i < template.length; i++)
            map.put(headers.get(i), template[i]);


        HashMap<String, Integer> settableValues = new HashMap<>();
        HashMap<String, Integer> otherValues = new HashMap<>();
        HashMap<String, Integer> parentValues = new HashMap<>();


        for (Field field : fields) {
            if (headers.contains(field.getName())) {
                int index = headers.indexOf(field.getName());
                System.out.println(field + " vi kan sette dette feltet " + index);
                settableValues.put(field.getName(), index);
            }
        }


        //TODO flytt
        Field[] parentFields = parentclazz.getDeclaredFields();

        for (Field field : parentFields) {
            if (headers.contains(field.getName())) {
                int index = headers.indexOf(field.getName());
                System.out.println(field + " Dette er et parent field " + index);
                parentValues.put(field.getName(), index);
            }
        }

        //TODO flytt
        int increment = 0;

        for (String head : headers) {
            if (!settableValues.containsKey(head) && !parentValues.containsKey(head)) {
                otherValues.put(head, increment);
                increment++;
            }
        }


        for (Map.Entry<String, Integer> entry : settableValues.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            System.out.println(entry.getValue());
        }

        for (List objVal : records) {


            T test = (T) constructor.newInstance();

            ContactInfo contactInfo = new ContactInfo();
            Field field = clazz.getDeclaredField("contactInfo");
            field.setAccessible(true);
            field.set(test, contactInfo);


            //TODO Skill ut i egen metode
            for (Map.Entry<String, Integer> entry : settableValues.entrySet()) {
                Field fieldToSet = clazz.getDeclaredField(entry.getKey());
                fieldToSet.setAccessible(true);
                fieldToSet.set(test, (objVal.get(entry.getValue())));
            }

            for (Map.Entry<String, Integer> entry : parentValues.entrySet()) {
                Field fieldToSet = parentclazz.getDeclaredField(entry.getKey());
                fieldToSet.setAccessible(true);
                fieldToSet.set(test, (objVal.get(entry.getValue())));
            }

            if (!otherValues.isEmpty()) {
                System.out.println(otherValues + "Other Values");
                List<Class> subClazz = new ArrayList<>();
                for (Field checkField : fields) {
                    if (!checkField.getType().isPrimitive() && checkField.getType() != String.class && !subClazz.contains(checkField.getType())) {
                        subClazz.add(checkField.getType());
                    }
                }

                for (Class sub : subClazz) {

                }


            }

            Field checkField;
            int objectCounter;
            for (Map.Entry<String, Integer> entry : parentValues.entrySet()) {


            }


            returnObj.add(test);


        }
        return returnObj;
    }


    public <T> T getCompObj(HashMap<String, Integer> otherValues, Class sub) throws NoSuchMethodException, ReflectiveOperationException {

        Constructor<T> constructor = sub.getDeclaredConstructor();
        Field[] fields = sub.getDeclaredFields();
        constructor.setAccessible(true);
        T obj = (T) constructor.newInstance();


        for (Map.Entry<String, Integer> entry : otherValues.entrySet()) {
            Field fieldToSet = sub.getDeclaredField(entry.getKey());
            fieldToSet.setAccessible(true);
            //fieldToSet.set(obj, (objVal.get(entry.getValue())));


        }


//        Class clazz = field.getType();
//        Constructor constructor = clazz.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        Field[] fields = clazz.getDeclaredFields();
//
//        T t = (T)constructor.newInstance(field);

        return obj;
    }
}




//    private <T> void setPrimitive(T instance, Field field, ) throws IllegalAccessException, NumberFormatException {
//        String typeName = field.getType().getName();
//        String value = node.getValue();
//
//        if(typeName.equals(long.class.getName())) {
//            field.setLong(instance, Long.parseLong(value));
//        } else if(typeName.equals(int.class.getName())) {
//            field.setInt(instance, Integer.parseInt(value));
//        } else if(typeName.equals(boolean.class.getName())) {
//            field.setBoolean(instance, Boolean.parseBoolean(value));
//        } else if(typeName.equals(double.class.getName())) {
//            field.setDouble(instance, Double.parseDouble(value));
//        } else if(typeName.equals(float.class.getName())) {
//            field.setFloat(instance, Float.parseFloat(value));
//        } else if(typeName.equals(short.class.getName())) {
//            field.setShort(instance, Short.parseShort(value));
//        } else if(typeName.equals(byte.class.getName())) {
//            field.setByte(instance, Byte.parseByte(value));
//        } else if(typeName.equals(char.class.getName())) {
//            field.setChar(instance, value.length() == 0 ? ' ' : value.charAt(0));
//        }
//    }















            //Type [] types =


//    public static void checkFields(Field[] fields) throws NoSuchMethodException, ReflectiveOperationException {
//        Class clazz;
//        for (Field field : fields) {
//            if (!field.getType().isPrimitive()) {
//                if (!field.getType().equals(String.class)) {
//                    clazz = field.getType();
//                    Constructor constructor = clazz.getDeclaredConstructor();
//                    Field [] fields1 = clazz.getDeclaredFields();
//                    checkFields(fields1);
//
//                    Object t = constructor.newInstance();
//
//                }
//
//            }
//        }
//    }
//
//
//
//
//
//    public static List<List<String>> readObjects(String filename, Class clazz) throws IOException, ReflectiveOperationException {
//
//        List<List<String>> records = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(";");
//                records.add(Arrays.asList(values));
//            }
//        }
//
//        ArrayList<Object> returnObj = new ArrayList<>();
//        Constructor constructor = clazz.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        List<String> headers = records.get(0);
//        System.out.println(headers);
//
//        for(List string : records){
//
//            Object t = constructor.newInstance();
//            Field[] fields = clazz.getDeclaredFields();
//            checkFields(fields);
//
//
//
//
//
//
//
//
//
//
//                } return records;



        //  Field[] fields = clazz.getDeclaredFields();

        //     Class test = fields[0].getType();
        //System.out.println(clazz.toString());
        //   for(Field field : fields)


//        //henter konstruktørene
//        Constructor[] constructors = clazz.getDeclaredConstructors();
//
//        //henter feltene
//        Field[] fields = clazz.getDeclaredFields();
//
//        int antall=0;
//
//        //henter pattern for klassen:
//        String[] pattern = Templates.setterPattern(clazz);
//        System.out.println(pattern + " mønster");
//
//        for (List string : records){
//            if(string.get(0).equals(clazz.toString())){
//
//
//                //Fikk til å opprette generisk objekt av klassen:
//                Method[] methods = clazz.getDeclaredMethods();
//                Constructor empty = clazz.getDeclaredConstructor();
//                empty.setAccessible(true);
//            Object t = empty.newInstance();
//            System.out.println(t.getClass());
//
//
//            //bruke samme metode som i skrive klassen?
//                for(Method method : methods){
//                    if (isSetter(method)){
//                        System.out.println(antall++);
//                        System.out.println(method.getName());
//
//                    }
//                }
//
//                Method[] mid = new Method[20];
//                System.out.println("antall metoder " + antall);
//                for (Method method : methods) {
//                    if (isSetter(method)) {
//
//                        String name = method.getName();
//                        System.out.println(name);
//
//                        for (int i=0; i<pattern.length; i++){
//                            if (name.equals(pattern[i])){
//                                mid[i] = method;
//                            }
//                        }
//                    }
//                }
//
//
//                fields = clazz.getDeclaredFields();
//                int i = 0;
//                //4 fields
//                for(Field field : fields){
//                    if(field.getType().isPrimitive())
//                        System.out.println(field.getName() + " " + field);
//                    field.setAccessible(true);
//                    Type type = field.getType();
//                    System.out.println(type);
//                    field.set(t, (string.get(1)));
//
//
//
//                }
//
//
//
//
//            }
//
//
//            };
//         for(Constructor constructor : constructors)
//        System.out.println(constructor.toString());



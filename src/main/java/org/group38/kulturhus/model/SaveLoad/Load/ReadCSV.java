package org.group38.kulturhus.model.SaveLoad.Load;

import org.group38.kulturhus.model.Exeptions.ParsingException;
import org.group38.kulturhus.model.SaveLoad.Templates;

import java.io.*;
import java.lang.reflect.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ReadCSV {
    String filename;
    String[] classes = new String[]{};



    public <T> void checkClass(String filename, Class clazz){
        if(clazz == Templates.efs.getClass() || clazz == Templates.ens.getClass()){
            ArrayList<T> list = new ArrayList<>();





        }

    }

//    public <T> ArrayList<T> readObjects(String paths){
//        ArrayList<T> en = new ArrayList<>();
//        en.add(paths);
//      return ;
//    }



    private static List<String> convertToLowerCase(List<String> headers) {
        List<String> fieldNames = new ArrayList<>();
        String firstLetter;
        for (String header : headers) {
            firstLetter = header.substring(0, 1).toLowerCase();
            fieldNames.add(firstLetter + header.substring(1));
        }
        return fieldNames;
    }




    public static <T> ArrayList<T> readObjects(String filename, Class clazz) throws ParsingException, IOException, ReflectiveOperationException {
        File file = new File(filename);
        if (file.length() == 0) {
            return new ArrayList<>();
        }

        CsvParser csvParser = new CsvParser();
        List<List<String>> records = csvParser.parseCsv(filename);
        List<String> headers = csvParser.getHeaders();


        Class parentclazz = clazz.getSuperclass();
        ArrayList<T> returnObj = new ArrayList<>();


        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        System.out.println(headers);
        Field[] fields = clazz.getDeclaredFields();

        headers = (convertToLowerCase(headers));


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

        for (String head : headers) {
            if (!settableValues.containsKey(head) && !parentValues.containsKey(head)) {
                otherValues.put(head, headers.indexOf(head));
            }
        }


        for (Map.Entry<String, Integer> entry : otherValues.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            System.out.println(entry.getValue());
        }

        for (List<String> objVal : records) {


            T test = (T) constructor.newInstance();


            //TODO Skill ut i egen metode
            //set settable values
            setFields(settableValues, clazz, test, objVal);

            //SetParent values
            setFields(parentValues, parentclazz, test, objVal);

            //SetOther Values
            if (!otherValues.isEmpty()) {

                HashMap<Field, Class> subClazz = new HashMap<>();

                for (Field checkField : fields) {
                    if (!checkField.getType().isPrimitive() && checkField.getType() != String.class && !subClazz.containsKey(checkField.getType())) {
                        subClazz.put(checkField, checkField.getType());
                    }

                }

                for (Map.Entry<Field, Class> entry : subClazz.entrySet()) {
                    Field field = clazz.getDeclaredField(entry.getKey().getName());
                    field.setAccessible(true);
                    field.set(test, getCompObj(otherValues, entry.getValue(), objVal));
                }
            }
            returnObj.add(test);


        }
        return returnObj;
    }

    public static boolean isComplex(Field[] fields) {
        for (Field field : fields) {
            if (!field.getType().isPrimitive() && field.getType() != String.class) {
                return true;
            }
        }
        return false;
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


    private static <T> T getCompObj(HashMap<String, Integer> otherValues, Class sub, List<String> objVal) throws NoSuchMethodException, ReflectiveOperationException {

        Constructor<T> constructor = sub.getDeclaredConstructor();
        Field[] fields = sub.getDeclaredFields();
        constructor.setAccessible(true);
        T obj = (T) constructor.newInstance();
        System.out.println(obj.getClass());


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



package org.group38.kulturhus.model.SaveLoad.Save;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.SaveLoad.Templates;
import org.group38.kulturhus.model.facility.Facility;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;


public class WriteToCSV implements WriterInterface { //TODO implementer interface. Gjør så WriteObjects tar inn Arralist i stedet for Object.


    @Override
    public void writeObject(Object object, String filename) throws IOException {
        ObservableList<Object> objects = FXCollections.observableArrayList();
        objects.add(object);

        writeObjects(objects, filename);

    }

    public static boolean isGetter(Method method) {
        if (Modifier.isPublic(method.getModifiers()) &&
                method.getParameterTypes().length == 0) {
            if (method.getName().matches("^get[A-Z].*") &&
                    !method.getReturnType().equals(void.class) && !method.getReturnType().equals(Facility.class)
                    && !method.getReturnType().equals(ContactPerson.class) && !method.getReturnType().equals(EventInfo.class)
                    && !method.getReturnType().equals(ContactInfo.class) && !method.getReturnType().equals(ArrayList.class))
                return true;
        }
        return false;
    }


    private static StringBuilder getterItteration(Method[] methods, String[] pattern, Object object) {
        StringBuilder sb = new StringBuilder();
        String name;

        int antall = 0;
        for(Method method : methods){
            if (isGetter(method)){
                antall++;
            }
        }

        Method[] mid = new Method[antall];
        System.out.println("antall metoder " + antall);
        for (Method method : methods) {
            if (isGetter(method)) {

                    name = method.getName();
                    System.out.println(name);

                    for (int i=0; i<pattern.length; i++){
                        if (name.equals(pattern[i])){
                           mid[i] = method;
                        }
                    }
            }
        }
        System.out.println("DENNE HER");
        System.out.println(Arrays.toString(mid));
        try {
            System.out.println(sb + "dette er stringen");
            for (Method method : mid) {
                System.out.println(Arrays.toString(mid));
           //     System.out.println(method.invoke(object));
                if(method.invoke(object) == null){
                    sb.append("-");
                } else sb.append(method.invoke(object).toString());
                System.out.println(method + " " + "denne funket");
                System.out.println(sb);
                sb.append(";");
            }
            sb.append("\n");
        } catch (InvocationTargetException | IllegalAccessException e){
            e.printStackTrace();
        }
        return sb;
    }

    //Skriver headere på filen første gang den opprettes
    private static void writeHeaders(Object object, String filename){
        FileWriter fileWriter = null;
        String[] patterns = Templates.getterPattern(object.getClass());
        StringBuilder sb = new StringBuilder();

        for(String pattern : patterns){
            sb.append(pattern.substring(3));
            sb.append(";");
        }

        try {
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
            fileWriter = new FileWriter(filename, true);
            fileWriter.write(sb.toString());
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


//Skriver objerkter til fil



    public <T> void writeObjects(ObservableList<T> objects, String filename) throws IOException {
        FileWriter fileWriter = null;
        final String nextline = "\n";
        String[] pattern;


//        //sjekk filnavn


        for (T object : objects) {

            Class<?> clazz = object.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            StringBuilder save = new StringBuilder();


            pattern = Templates.getterPattern(object.getClass());
            System.out.println(pattern);


            //sjekk om har superklasse og hent gettere
            if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
                Class parentClazz = clazz.getSuperclass();
                Method[] parentmethods = parentClazz.getDeclaredMethods();
                Method[] common = new Method[parentmethods.length + methods.length];
                System.arraycopy(methods, 0, common, 0, methods.length);
                System.arraycopy(parentmethods, 0, common, methods.length, parentmethods.length);
                System.out.println("Denne" + Arrays.toString(common));
                save.append(getterItteration(common, pattern, object));
            } else save.append(getterItteration(methods, pattern, object));

//Skriv til fil
            try {
                //TODO sjekk om filen er skrevet i fra før.
                writeHeaders(object, filename);
                save.deleteCharAt(save.length() - 1);
                fileWriter = new FileWriter(filename, true);
                fileWriter.write(save.toString());
                fileWriter.write(nextline);


            } catch (IOException x) {
                x.printStackTrace();
            } finally {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    //Sorterer metodelisten etter mønsteret
    private static Method[] sortMethods(String[] patterns, Method[] methods){
        Method[] sortedMethods = new Method[methods.length];


        for(int i = 0; i<patterns.length; i++){
            for (Method method : methods){
                if(method.getName().equals(patterns[i])){
                    sortedMethods[i] = method;
                }
            }
        }

        return sortedMethods;
    }
}













        //Random access file - eksempel på canvas
    //en som skriver en og en
    //en som oppdaterer en linje




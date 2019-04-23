package org.group38.kulturhus.model.SaveLoad;

import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.Event.Ticket;
import org.group38.kulturhus.model.facility.Facility;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SaveCsvInterface {

    public static boolean isGetter(Method method) {
        if (Modifier.isPublic(method.getModifiers()) &&
                method.getParameterTypes().length == 0) {
            if (method.getName().matches("^get[A-Z].*") &&
                    !method.getReturnType().equals(void.class) && !method.getReturnType().equals(Facility.class)
                    && !method.getReturnType().equals(ContactPerson.class) && !method.getReturnType().equals(EventInfo.class))
                return true;
        }
        return false;
    }


    private static StringBuilder getterItteration(Method[] methods, Object object){
        StringBuilder sb = new StringBuilder();
        for (Method method : methods) {
            if (isGetter(method)) {
                try {
                    sb.append(method.invoke(object).toString());
                    sb.append(",");
                } catch (IllegalAccessException | InvocationTargetException y) {
                    y.printStackTrace();
                }
            }
        }
        return sb;
    }

    public static void writeObject(CsvBase object) {
        FileWriter fileWriter = null;
        List<CsvBase> objects = new ArrayList<>();
        final String divider = ",";
        final String nextline = "\n";
        String filename = null;

        objects.add(object);

        if (object instanceof EventNumberedSeating || object instanceof EventFreeSeating) {
            filename = "events.csv";
        } else if (object instanceof ContactPerson) {
            filename = "contactPerson.csv";
        } else if (object instanceof Ticket) {
            filename = "tickets.csv";
        }

        Class <?> clazz = object.getClass();

        Method[] methods = clazz.getDeclaredMethods();
        StringBuilder save = new StringBuilder();
        save.append(getterItteration(methods, object));

        if(clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class){
            System.out.println(clazz.getSuperclass());
            Class parentClazz = clazz.getSuperclass();
            Method[] parentmethods = parentClazz.getDeclaredMethods();
            save.append(getterItteration(parentmethods, object));
            }

        try {
            fileWriter = new FileWriter(filename, true);
            fileWriter.write(save.toString());
            fileWriter.write(nextline);
            save.deleteCharAt(save.length()-1);
            System.out.println(save.toString());


        } catch (IOException x){
            x.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        }

    }



             /*   try {
                    fileWriter = new FileWriter(filename, true);
                    for (CsvBase objectInfo : objects) {
                        fileWriter.append(objectInfo.toCSV());

                        System.out.println(objectInfo.toCSV());
                    }

                   // return true;
                } catch (IOException e) {
                    e.printStackTrace();
                   // return false;
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
}












        //Random access file - eksempel på canvas
    //en som skriver en og en
    //en som oppdaterer en linje


*/

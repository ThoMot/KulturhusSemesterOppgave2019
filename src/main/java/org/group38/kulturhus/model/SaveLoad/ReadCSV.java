package org.group38.kulturhus.model.SaveLoad;

import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.EventNumberedSeating;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSV {
    String filename;

    public static void readObjects(String filename) throws FileNotFoundException, IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }

        
        }

    }






//   /public static ArrayList<EventNumberedSeating> readSubstituteCSV(String path) throws IOException{
//
//        ArrayList<EventNumberedSeating> substituteUsers = new ArrayList<>();
//        BufferedReader reader = null;
//
//        try {
//            reader = Files.newBufferedReader(Paths.get(path));
//            String line = null; // read first line
//
//            // read the rest and create Persons for each line
//            while((line=reader.readLine()) != null) {
//                substituteUsers.add(parseSubstitute(line));
//            }
//        } finally {
//            if(reader != null) {
//                reader.close();
//            }
//        }
//        return substituteUsers;
//    }
//
//    private static EventNumberedSeating parseSubstitute(String line) {
//        // split line string into two using the separator ","
//        String[] split = line.split(",");
//
//
//        String username = split[0];
//        String lastname = split[1];
//        String email = split[2];
//
//        return new EventNumberedSeating();
//    }
//
//
//*/


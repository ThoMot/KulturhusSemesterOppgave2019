package org.group38.kulturhus.model.SaveLoad.Load;

import org.group38.kulturhus.model.Exeptions.ParsingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvParser {
    String headerline;
    List<String> headers;

    protected CsvParser(){
    }

    public List<List<String>> parseCsv(String filename) throws ParsingException {
        List<List<String>> records = new ArrayList<>();



        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String headerline;

            headerline = br.readLine();
            String[] head = headerline.split(";");
            headers = Arrays.asList(head);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
//                System.out.println(values.length + " values " + headers.size() + " header size");
//                System.out.println(Arrays.toString(values));
//                System.out.println(headers);
                //TODO FINN EN MÅTE Å SJEKKE FOR BEGGE DE TO EVENT TYPENE
                //if (values.length == headers.size()) {
                    records.add(Arrays.asList(values));
                //} else throw new ParsingException("CSV-File has wrong format");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }

    public List<String> getHeaders(){
        return headers;
    }

}

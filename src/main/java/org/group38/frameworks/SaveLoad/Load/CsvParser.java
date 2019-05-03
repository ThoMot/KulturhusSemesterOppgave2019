package org.group38.frameworks.SaveLoad.Load;

import org.group38.frameworks.Exceptions.ParsingException;

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
                    records.add(Arrays.asList(values));
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

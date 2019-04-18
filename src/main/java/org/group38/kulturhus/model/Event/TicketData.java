package org.group38.kulturhus.model.Event;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TicketData {
    private static TicketData instance = new TicketData();
    private static String fileName = "tickets.txt";
    private DateTimeFormatter formatter;

    private ObservableList<Ticket> tickets;

    public TicketData(){
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public static TicketData getInstance() {
        return instance;
    }

    public static String getFileName() {
        return fileName;
    }

    public ObservableList<Ticket> getTickets(){
        return tickets;
    }

    public void addTicket(Ticket ticket){
        tickets.add(ticket);
    }


//    public void loadTickets() throws IOException {
//        tickets = FXCollections.observableArrayList();
//        Path path = Paths.get(fileName);
//        BufferedReader br = Files.newBufferedReader(path);
//
//        String input;
//        try{
//            while((input = br.readLine()) != null){
//                String[] holidayPieces = input.split("\t");
//
//                String name = holidayPieces[0];
//                String dateString =  holidayPieces[1];
//
//                LocalDate date = LocalDate.parse(dateString, formatter);
//                Ticket ticket = new Ticket(name, date);
//                tickets.add(ticket);
//            }
//        }catch(IOException e) {
//            e.printStackTrace();
//        } finally{
//            if(br != null){
//                br.close();
//            }
//        }
//    }
//
//    public void storeTickets()throws IOException{
//        Path path = Paths.get(fileName);
//        BufferedWriter bw = Files.newBufferedWriter(path);
//        try {
//            Iterator<Ticket> iter = tickets.iterator();
//            while(iter.hasNext()){
//                Ticket ticket = iter.next();
//                bw.write(String.format("%s\t%s", ticket.getPhonenumber(), ticket.getDate().format(formatter)));
//                bw.newLine();
//            }
//        }finally {
//            if (bw != null){
//                bw.close();
//            }
//
//        }
//    }

    public void deleteTickets(Ticket holiday){
        tickets.remove(holiday);
    }
}


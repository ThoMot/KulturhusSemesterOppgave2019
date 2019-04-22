package org.group38;

import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.Event.EventFreeSeating;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.facility.Facility;
import org.group38.kulturhus.model.SaveLoad.SaveCsvInterface;
import org.group38.kulturhus.model.SaveLoad.SaveJobj;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;



import org.group38.kulturhus.model.ContactPerson.ContactPerson;
import org.group38.kulturhus.model.ContactPerson.ContactInfo;
import org.group38.kulturhus.model.Event.EventInfo;
import org.group38.kulturhus.model.Event.EventNumberedSeating;
import org.group38.kulturhus.model.Event.Performer;
import org.group38.kulturhus.model.Event.Ticket;
import org.group38.kulturhus.model.facility.Facility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;

public class EnhetsTesting {
    public static void main(String[] args) {
        int antallFeil=0;
        Facility facility=new Facility("Sal1","kino", 10,10);


        LocalDate d = LocalDate.of(2019, Month.APRIL, 22);
        LocalTime t = LocalTime.of(22,00);

        ContactPerson ContactPerson = new ContactPerson("Martina", "Førre", new ContactInfo("martina@gmail.com","11223344"));
        EventInfo eventInfo = new EventInfo("Max Manus", "film","Ane Dahl Torp",d, t);

        EventNumberedSeating maxManus=new EventNumberedSeating(ContactPerson, facility, 100, eventInfo);


        ContactInfo martinasInfo = new ContactInfo("test", "1010101010101");
        ContactPerson martina = new ContactPerson("Martina", "Førre", martinasInfo);
        System.out.println(martina.toString());

        martinasInfo.setEmail("Heipådeg");
        martina.setFirstName("IkkeMartina");
        System.out.println(martina.toString());


        //Testing the buy tickets method
        try{
            maxManus.BuyTicket(5,4,"90862870");
        }
        catch (IllegalArgumentException e){
            antallFeil++;
            System.out.println(e);
        }

        try{
            maxManus.BuyTicket(5,4,"90862870");
            antallFeil++;
        }
        catch (IllegalArgumentException e){
        }

        try{
            maxManus.BuyTicket(-1 ,5,"90862870");
            antallFeil++;
        }catch (IllegalArgumentException e){
        }
        try{
            maxManus.BuyTicket(5, -4, "90862870");
            antallFeil++;
        }catch (IllegalArgumentException e){
        }
        try{
            maxManus.BuyTicket(15,4, "90862870");
            antallFeil++;
        }catch (IllegalArgumentException e){
        }
        try{
            maxManus.BuyTicket(10,14, "90862870");
            antallFeil++;
        }
        catch (IllegalArgumentException e){
        }

        //Testing the delete ticket method
        try{
            maxManus.BuyTicket(8,5,"90862870");
        }catch (IllegalArgumentException e){
            antallFeil++;
            System.out.println(e);
        }
        try{
            maxManus.DeleteTicket("90862870");
        }catch (NoSuchElementException e){
            antallFeil++;
            System.out.println(e);
        }

        try{
            maxManus.DeleteTicket("90862870");
            antallFeil++;
        }catch (NoSuchElementException e){
        }
        try{
            maxManus.BuyTicket(8,5,"90862870");
        }catch (IllegalArgumentException e){
            antallFeil++;
            System.out.println(e);
        }
        try{
            maxManus.DeleteTicket(8, 5);
        }catch (NoSuchElementException e){
            System.out.println(e);
            antallFeil++;
        }
        try{
            maxManus.DeleteTicket(8, 5);
            antallFeil++;
        }catch (NoSuchElementException e){
        }

        //testing of setmethod for phonenumber
        maxManus.BuyTicket(4, 2, "90862870");
        Ticket ticket = maxManus.FindTicket(4,2);
        try{
            ticket.setPhonenumber("8734");
            antallFeil++;
        }catch (IllegalArgumentException e){
        }
        try{
            ticket.setPhonenumber("absofhrt");
            antallFeil++;
        }catch (IllegalArgumentException e){
        }
        try{
            ticket.setPhonenumber("23541234");
        }
        catch (IllegalArgumentException e){
            System.out.println(e);
            antallFeil++;
        }

        //testing av setdate metode
        try{
            maxManus.BuyTicket(2, 4, "90862870");
        }
        catch (IllegalArgumentException e){
            System.out.println(e);
            antallFeil++;
        }

        //Testing setTicketPrice method
        maxManus.setTicketPrice(200);
        String setPriceTest= maxManus.getTicketPrice()+"";
        String setPriceTest2= maxManus.FindTicket(2, 4).getPrice()+"";
        if(!"200.0".equals(setPriceTest)){
            antallFeil++;
            System.out.println("Feil i endring av pris i eventklassen");
        }
        if(!"200.0".equals(setPriceTest2)){
            antallFeil++;
            System.out.println("Feil i endring av pris i billettklassen");
        }

        try{
            maxManus.BuyTicket(9, 8, "90241020");
        }catch (IllegalArgumentException i){
            System.out.println(i);
            antallFeil++;
        }
        //System.out.println(maxManus.printTicket(9, 8));
        maxManus.EditSeat(9,8, 3,4);
        try{
            maxManus.printTicket(9,8);
            antallFeil++;
        }
        catch (NullPointerException billettFinnesikke){
        }
        try{
            maxManus.printTicket(3, 4);
        }catch (NullPointerException i){
            System.out.println("Finnes ingen billett her");
            antallFeil++;
        }

        System.out.println("\nAntall feil i enhetstesting: "+antallFeil);

        ContactInfo thorasInfo = new ContactInfo("Thora.marie@outlook.com", "12345");
        ContactPerson contactPerson = new ContactPerson("Thora", "Mothes", thorasInfo);
        Facility facility2 = new Facility("Sal 1", "Kinosal", 10, 20);
        LocalDate d1 = LocalDate.of(2019, Month.MAY, 10);
        LocalTime t1 = LocalTime.of(18,00);
        EventInfo eventInfo2 = new EventInfo("Karpe", "konsert med karpe","Megdi ++", d1, t1);
        EventFreeSeating test = new EventFreeSeating(contactPerson, facility2, 150, eventInfo2);
        EventNumberedSeating en = new EventNumberedSeating(contactPerson, facility2, 150, eventInfo2);

        List<Object> events = new ArrayList<>();
        events.add(0,(new EventNumberedSeating(contactPerson, facility2, 150, eventInfo2)));
        events.add(1, new EventNumberedSeating(contactPerson, facility2, 260, eventInfo2));
        events.add(2, test);
        System.out.println("hei" + events.get(0) + events.get(1) + events.get(2));


        SaveCsvInterface save = new SaveCsvInterface();

        SaveJobj jo = new SaveJobj();
        jo.writeEvent(en);

        SaveCsvInterface.writeObject(en);

    }
}

package org.group38;

import org.group38.model.ContactPerson.ContactPerson;
import org.group38.model.ContactPerson.ContactInfo;
import org.group38.model.Event.*;
import org.group38.model.Facility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EnhetsTesting {
    public static void main(String[] args) {
        int antallFeil=0;
        Facility facility=new Facility("Sal1","kino", 10,10);


        Calendar d= new GregorianCalendar(2019, 10,10, 22,00);
        ContactPerson contactPerson = new ContactPerson("Martina", "Førre", new ContactInfo("martina@gmail.com","11223344"));
        EventInfo eventInfo = new EventInfo("Max Manus", "film",d);
        ArrayList<Performer> performers= new ArrayList <Performer>();
        performers.add(new Performer("Hans", "Langus"));
        performers.add(new Performer("Kine", "Larsen"));

        EventNumberedSeating maxManus=new EventNumberedSeating(contactPerson, facility, performers, 100, eventInfo);


        ContactInfo martinasInfo = new ContactInfo("test", "1010101010101");
        ContactPerson martina = new ContactPerson("Martina", "Førre", martinasInfo);
        System.out.println(martina.toString());

        martinasInfo.setEmail("Heipådeg");
        martina.setFirstName("IkkeMartina");
        System.out.println(martina.toString());





        //Testing the buy tickets method
        String buy= maxManus.buyTicket(5,4,"90862870");
        if(!"Billett er reservert på plass: 4,5".equals(buy)){
            System.out.println("Feil i kjøp av billett");
            antallFeil++;
        }

        String buy2=maxManus.buyTicket(5,4,"90862870");
        if(!"Setet er opptatt".equals(buy2)){
            System.out.println("Feil i opprettelse av billett der det er opptatt");
            antallFeil++;
        }

        String buyNegative = maxManus.buyTicket(-1 ,5,"90862870");
        String buyNegative2 = maxManus.buyTicket(5, -4, "90862870");
        String buyOverMax = maxManus.buyTicket(15,4, "90862870");
        String buyOverMax2 = maxManus.buyTicket(10,14, "90862870");
        if(!"Plassen du valgte er utenfor registeret, velg et radnummer mellom 0 og 10".equals(buyNegative)){
            System.out.println("Feil, skal ikke kunne ta inn negative verdier");
            antallFeil++;
        }
        if (!"Plassen du valgte er utenfor registeret, velg et setenummer mellom 0 og 10".equals(buyNegative2)) {
            System.out.println("Feil, skal ikke kunne ta inn negative verdier");
            antallFeil++;
        }
        if(!"Plassen du valgte er utenfor registeret, velg et radnummer mellom 0 og 10".equals(buyOverMax)){
            System.out.println("Feil, skal ikke kunne velge setenummer høyere enn maxverdi");
            antallFeil++;
        }
        if(!"Plassen du valgte er utenfor registeret, velg et setenummer mellom 0 og 10".equals(buyOverMax2)){
            System.out.println("Feil, skal ikke kunne velge radnummer høyere enn maxverdi");
            antallFeil++;
        }

        //Testing the delete ticket method
        maxManus.buyTicket(8,5,"90862870");
        String slett = maxManus.deleteTicket("90862870");
        if(!"2 billetter er slettet på 90862870".equals(slett)){
            System.out.println("Feil i sletting av flere billetter på telefonnummer");
            antallFeil++;
        }

        String slett2 =maxManus.deleteTicket("90862870");
        if(!"Billetten eksisterer ikke".equals(slett2)){
            System.out.println("Feil i sletting av billetter som ikke finnes på telefonnummer");
            antallFeil++;
        }
        maxManus.buyTicket(8,5,"90862870");
        boolean slett3 = maxManus.deleteTicket(8, 5);
        boolean slett4 = maxManus.deleteTicket(8, 5);

        if(!slett3){
            antallFeil++;
            System.out.println("Feil i sletting av billett med seteplassering som input");
        }
        if(slett4){
            antallFeil++;
            System.out.println("Feil i sletting av billett som ikke finnes med seteplass som input");
        }

        //testing of setmethod for phonenumber
        maxManus.buyTicket(4, 2, "90862870");
        Ticket ticket = maxManus.findTicket(4,2);
        String testsettelefonnummer = ticket.setPhonenumber("8734");
        String testsettelefonnummer2 = ticket.setPhonenumber("absofhrt");
        String testsettelefonnummer3 = ticket.setPhonenumber("23541234");
        if(!"Telefonnummeret inneholder ikke 8 symboler".equals(testsettelefonnummer)){
            antallFeil++;
            System.out.println("Feil i sjekking av lengde av telefonnummer");
        }
        if(!"Telefonnummer er på feil format, må inneholde bare tall".equals(testsettelefonnummer2)){
            antallFeil++;
            System.out.println("Feil ved sjekking om telefonnummer bare er tall");
        }
        if(!"Telefonnummer er oppdatert".equals(testsettelefonnummer3)){
            antallFeil++;
            System.out.println("Feil ved oppdatering av telefonnummer");
        }

        //testing av setdate metode
        maxManus.buyTicket(2, 4, "90862870");
        Calendar e= new GregorianCalendar(2019, 10,8, 20,00);
        maxManus.setDate(e);
        String changedate= eventInfo.getDate().getTime().toString();
        String changedate2= maxManus.findTicket(2,4).getDate().getTime().toString();

        if(!"Fri Nov 08 20:00:00 CET 2019".equals(changedate)){
            antallFeil++;
            System.out.println("Feil i redigering av dato i eventinfoobjektet");
        }
        if(!"Fri Nov 08 20:00:00 CET 2019".equals(changedate2)){
            antallFeil++;
            System.out.println("Feil i redigering av dato i billettobjektet");
        }

        //Testing setTicketPrice method
        maxManus.buyTicket(2, 4, "90862870");
        maxManus.setTicketPrice(200);
        String setPriceTest= maxManus.getTicketPrice()+"";
        String setPriceTest2= maxManus.findTicket(2, 4).getPrice()+"";
        if(!"200.0".equals(setPriceTest)){
            antallFeil++;
            System.out.println("Feil i endring av pris i eventklassen");
        }
        if(!"200.0".equals(setPriceTest2)){
            antallFeil++;
            System.out.println("Feil i endring av pris i billettklassen");
        }

        maxManus.buyTicket(9, 8, "90241020");
        System.out.println(maxManus.findTicket(9, 8).toString());
        String editSeat= maxManus.editSeat(9,8, 3,4);
        try{
            System.out.println(maxManus.findTicket(9,8).toString());
        }
        catch (NullPointerException billettFinnesikke){
            System.out.println("Finnes ingen billett på plasseringen");
        }
        System.out.println(maxManus.findTicket(3, 4).toString());

        System.out.println("\nAntall feil i enhetstesting: "+antallFeil);

    }
}

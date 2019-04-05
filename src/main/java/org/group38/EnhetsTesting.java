//lese meg opp på hvordan testing burde være
package org.group38;

import org.group38.model.ContactPerson.ContactPerson;
import org.group38.model.ContactPerson.ContactInfo;
import org.group38.model.Event.Event;
import org.group38.model.Event.EventInfo;
import org.group38.model.Facility;
import org.group38.model.Performer;
import org.group38.model.Ticket;

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
        ArrayList<Performer> performers= new ArrayList();
        performers.add(new Performer("Hans", "Langus"));
        performers.add(new Performer("Kine", "Larsen"));

        Event maxManus=new Event(contactPerson, facility, performers, 100, eventInfo);


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

        //Testing the delete ticket method, must also check that the space in the matrix is cleared
        maxManus.buyTicket(8,5,"90862870");
        String slett = maxManus.deleteTicket("90862870");
        if(!"2 billetter er slettet på 90862870".equals(slett)){
            System.out.println("Feil i sletting av flere billetter");
            antallFeil++;
        }

        String slett2 =maxManus.deleteTicket("90862870");
        if(!"Billetten eksisterer ikke".equals(slett2)){
            System.out.println("Feil i sletting av billetter somj ikke finnes");
            antallFeil++;
        }

        maxManus.buyTicket(4, 2, "90862870");
        Ticket ticket = maxManus.findTicket("90862870");
        System.out.println(ticket.toString());

        //testing of setmethod for phonenumber
        String testsetelefonnummer = ticket.setPhonenumber("8734");
        String testsetelefonnummer2 = ticket.setPhonenumber("absofhrt");
        String testsettelefonnummer3 = ticket.setPhonenumber("23541234");
        if(!"Telefonnummeret inneholder ikke 8 symboler".equals(testsetelefonnummer)){
            antallFeil++;
            System.out.println("Feil i sjekking av lengde av telefonnummer");
        }
        if(!"Telefonnummer er på feil format".equals(testsetelefonnummer2)){
            antallFeil++;
            System.out.println("Feil ved sjekkin om telefonnummer bare er tall");
        }
        if(!"Telefonnummer er oppdatert".equals(testsettelefonnummer3)){
            antallFeil++;
            System.out.println("Feil ved oppdatering av telefonnummer");
        }

        System.out.println("\nAntall feil i enhetstesting testing: "+antallFeil);

    }
}

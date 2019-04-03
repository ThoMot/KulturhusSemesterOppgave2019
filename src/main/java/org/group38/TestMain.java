package org.group38;

import org.group38.model.Event;
import org.group38.model.Facility;
import org.group38.model.Kontaktperson;
import org.group38.model.Ticket;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestMain {
    public static void main(String[] args) {
        int antallFeil=0;
        Facility facility=new Facility("Sal1","kino", 10,10);
        Calendar d= new GregorianCalendar(2019, 10,10, 22,00);
        Kontaktperson kontaktperson = new Kontaktperson("Martina", "Førre", "martinarebekka@gmail.com", "90862870");
        Event maxManus=new Event(kontaktperson, facility, "MaxManus", 100, "MaxManus",d, 100.00);


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
//        System.out.println(maxManus.freeSeats());

        maxManus.buyTicket(4, 2, "90862870");
        Ticket ticket = maxManus.findTicket("90862870");
        System.out.println(ticket.toString());

        System.out.println("\nAntall feil i enhetstesting testing: "+antallFeil);









    }
}

package org.group38;

import org.group38.model.Event;
import org.group38.model.Facility;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestMain {
    public static void main(String[] args) {
        int antallFeil=0;
        Facility facility=new Facility("Sal1","kino", 10,10);
        Calendar d= new GregorianCalendar(2019, 10,10);
        Event maxManus=new Event(facility, "MaxManus", 100, "MaxManus",d, 100.00);


        //Testing the buy tickets method, need to add test for wrong input too
        String s= maxManus.buyTicket(5,4,"90862870");
        if(!"Billett er reservert på plass: 4,5".equals(s)){
            System.out.println("Feil i kjøp av billett");
            antallFeil++;
        }

        String t=maxManus.buyTicket(5,4,"90862870");
        if(!"Setet er opptatt".equals(t)){
            System.out.println("Feil i opprettelse av billett der det er opptatt");
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


        System.out.println("Antall feil i enhetstesting testing: "+antallFeil);






    }
}

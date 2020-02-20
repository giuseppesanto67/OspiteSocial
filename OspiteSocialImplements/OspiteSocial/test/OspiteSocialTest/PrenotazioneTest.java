package OspiteSocialTest;

import Classes.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrenotazioneTest {
    
    private static OspiteSocial os;
    
    @BeforeClass 
    public static void setUp() throws ParseException{
        os=OspiteSocial.getInstance();
        Utente u= new Utente("DBLKS", "Kristian", "Di blasi", 23, 'M', "Floridia", "12", "KRI",20);
        os.setUtente(u);
        os.eliminaUtente();
        os.inserisciUtente("DBLKS", "Kristian", "Di blasi", 23, 'M', "Floridia", "12", "KRI");
    }
   
    @Test
    public void BtestRicercaAlloggio() {  
        System.out.println("ricercaAlloggio");
        String citta = "1";
        Date dataInizio = Date.valueOf("2020-02-13");
        Date dataFine = Date.valueOf("2020-02-15");
        Integer posti = 1;
        List<Abitazione> result = os.ricercaAlloggio(citta, dataInizio, dataFine, posti);
        assertNotEquals(0,result.size());
    }
    
    @Test
    public void AtestRichiestaPrenotazione() {
        System.out.println("richiestaPrenotazione");
        Integer codiceAbitazione = 145;
        Date dataInizio = Date.valueOf("2020-02-2");
        Date dataFine = Date.valueOf("2020-02-5");
        Integer posti = 1;
        List<String> utentiScelti = new ArrayList();
        utentiScelti.add("DBLKS");
        List<Integer> stanzeScelte = new ArrayList();
        stanzeScelte.add(67);      
        boolean result = os.richiestaPrenotazione(codiceAbitazione, dataInizio, dataFine, posti, utentiScelti, stanzeScelte);
        
        assertTrue(result);
    }

    @Test
    public void CtestVisualizzaPrenotazioni() {
        System.out.println("visualizzaPrenotazioni");
        String cf = "DBLKS";
        List<Prenotazione> result = os.visualizzaPrenotazioni(cf);
        assertNotNull(result);
    }
    
    
   @Test
    public void DtestVisualizzaRichieste() {
        System.out.println("visualizzaRichieste");
        String cf = "1";
        List<Prenotazione> result = os.visualizzaRichieste(cf);
        assertNotNull(result);
   }
     
    @Test
    public void ETestAccettaRichiesta() {
        System.out.println("accettaRichiesta");
        Integer codicePrenotazione=os.visualizzaPrenotazioni("DBLKS").get(0).getCodicePrenotazione();
        boolean result;       
        result=os.getInstance().accettaRichiesta(codicePrenotazione);
        assertTrue(result);
   }
   
      
    @Test
    public void FTestRifiutaRichiesta() {
        System.out.println("rifiutaRichiesta");
        Integer codicePrenotazione=os.visualizzaPrenotazioni("DBLKS").get(0).getCodicePrenotazione();;
        String motivazione="Feedback negativo";
        boolean result;      
        result=os.getInstance().rifiutaRichiesta(codicePrenotazione,motivazione);
        assertTrue(result);
   }
   
    @Test
    public void GTestCheckIn() {
        System.out.println("checkIn");
        Integer codicePrenotazione=os.visualizzaPrenotazioni("DBLKS").get(0).getCodicePrenotazione();;
        Date data=Date.valueOf("2020-02-2");
        boolean result=OspiteSocial.getInstance().checkIn(codicePrenotazione,data);
        assertTrue(result);
   }
    
    @Test
    public void HTestCheckOut() {
        System.out.println("checkOut");
        Integer codicePrenotazione=os.visualizzaPrenotazioni("DBLKS").get(0).getCodicePrenotazione();
        Date data=Date.valueOf("2020-02-3");
        boolean result=OspiteSocial.getInstance().checkOut(codicePrenotazione,data);
        assertTrue(result);
   }
 
}

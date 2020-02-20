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
public class AbitazioneTest {
    
    private static OspiteSocial os;
    
    @BeforeClass
    public static void setUp() throws ParseException{
        os=OspiteSocial.getInstance();    
        Utente u= new Utente("DBLKS", "Kristian", "Di blasi", 23, 'M', "Floridia", "12", "Kri");
        os.setUtente(u);
        os.inserisciUtente("DBLKS", "Kristian", "Di blasi", 23, 'M', "Floridia", "12", "KRI");
    }
     
    @Test
    public void AtestInserimentoAlloggio() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Integer expResult;
         
        System.out.println("inserimentoAlloggio");
        String cf = "DBLKS";
        String indirizzo = "Via romagnoli";
        String citta = "Floridia";
        Float distanzaCentro = (float)1.0;
        Float distanzaStazione = (float)1.0;
        Integer tempoTermineModifiche = 1;
        Date dataInizioDisponibilita = Date.valueOf("2015-03-31");
        Date dataFineDisponibilita = Date.valueOf("2015-03-31");
        Integer tariffaGiornaliera=3;
        
        Integer result = os.inserimentoAlloggio(cf, indirizzo, citta, distanzaCentro, distanzaStazione, tempoTermineModifiche, dataInizioDisponibilita, dataFineDisponibilita,tariffaGiornaliera);
        if(result!=0 && result!=-1)result=1;
        assertEquals(1,result);
    }

    @Test
    public void FtestModificaDatiAlloggio() {
        System.out.println("modificaDatiAlloggio");
        Integer codiceAbitazione = os.visualizzaAlloggiUtente(os.getCf()).get(0).getId();;
        String cf = "DBLKS";
        String indirizzo = "via roncoscilla";
        String citta = "Siracusa";
        Float distanzaCentro = (float)1.0;
        Float distanzaStazione = (float)1.0;
        Integer tempoTermineModifiche = 1;
        Date dataInizioDisponibilita = Date.valueOf("2015-03-31");
        Date dataFineDisponibilita = Date.valueOf("2015-03-31");
        Integer tariffaGiornaliera=5;
       
        boolean result = os.modificaDatiAlloggio(codiceAbitazione, cf, indirizzo, citta, distanzaCentro, distanzaStazione, tempoTermineModifiche, dataInizioDisponibilita, dataFineDisponibilita, tariffaGiornaliera);
        assertTrue(result);
    }
 
    @Test
    public void GtestEliminaAlloggio() {
        System.out.println("eliminaAlloggio");
        Integer codiceAbitazione=os.visualizzaAlloggiUtente(os.getCf()).get(0).getId();;
        String cf = "DBLKS";
        String indirizzo = "Via romagnoli";
        String citta = "Floridia";
        Float distanzaCentro = (float)1.0;
        Float distanzaStazione = (float)1.0;
        Integer tempoTermineModifiche = 1;
        Date dataInizioDisponibilita = Date.valueOf("2015-03-31");
        Date dataFineDisponibilita = Date.valueOf("2015-03-31");
        
        boolean result = os.eliminaAlloggio(codiceAbitazione, cf, indirizzo, citta, distanzaCentro, distanzaStazione, tempoTermineModifiche, dataInizioDisponibilita, dataFineDisponibilita);
        assertTrue(result);
    }
   
    @Test
    public void CtestVisualizzaAlloggio() {
        System.out.println("visualizzaAlloggio");
        Integer codiceAbitazione = os.visualizzaAlloggiUtente(os.getCf()).get(0).getId();
        
        Abitazione result = os.visualizzaAlloggio(codiceAbitazione);
       
        assertNotNull(result);
    }
  
    @Test
    public void DtestVisualizzaAlloggiUtente() {
        System.out.println("visualizzaAlloggiUtente");
        String cf = "DBLKS";
        List<Abitazione> result = os.visualizzaAlloggiUtente(cf);
        assertNotNull(result);
    }
   
    @Test
    public void BtestInserimentoDatiStanza() {
        System.out.println("inserimentoDatiStanza");
        String tipologiaStanza = "1";
        String tipologiaPostoLetto = "1";
        Integer numPostiLetto = 1;
        Integer codiceAbitazione = os.visualizzaAlloggiUtente(os.getCf()).get(0).getId();
        int result = os.inserimentoDatiStanza(tipologiaStanza, tipologiaPostoLetto, numPostiLetto, codiceAbitazione);
        if(result>0)result=1;
        assertEquals(1,result);
    }

  
    @Test
    public void EtestModificaDatiStanza() {
        System.out.println("modificaDatiStanza");
        Integer codiceStanza = os.visualizzaStanzeAbitazione(os.visualizzaAlloggiUtente(os.getCf()).get(0).getId()).get(0).getId();
        
        String tipologiaStanza = "1";
        String tipologiaPostoLetto = "1";
        Integer numPostiLetto = 2;
        boolean result = os.modificaDatiStanza(codiceStanza, tipologiaStanza, tipologiaPostoLetto, numPostiLetto);
        assertTrue(result);
    }
     
}

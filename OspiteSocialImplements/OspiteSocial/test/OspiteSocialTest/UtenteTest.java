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
import org.junit.jupiter.api.Order;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UtenteTest {
    private static OspiteSocial os;
    
    @BeforeClass
    public static void setUp() throws ParseException{
        os=OspiteSocial.getInstance();
        Utente u= new Utente("DBLKS", "Kristian", "Di blasi", 23, 'M', "Floridia", "12", "KRI");
        os.setUtente(u);
    }
  
    @Test
    public void AtestInserisciUtente() {
        os.eliminaUtente();
        System.out.println("inserisciUtente");
        String cf = "DBLKS";
        String nome = "Kristian";
        String cognome = "Di Blasi";
        int eta = 23;
        char sesso = 'M';
        String cittaResidenza = "Floridia";
        String cellulare = "3233333";
        String password = "KRI";
        
        Utente result = os.inserisciUtente(cf, nome, cognome, eta, sesso, cittaResidenza, cellulare, password);
        assertNotNull(result);
        
    }
    
    
    @Test
    public void BtestVisualizzaInformazioniUtente_String() {
        System.out.println("visualizzaInformazioniUtente");
        String cf = "DBLKS";
        Utente result = os.visualizzaInformazioniUtente(cf);
        assertNotNull(result);
    }

    @Test
    public void CtestModificaDatiUtente() {
        System.out.println("modificaDatiUtente");
        String cf = "DBLKS";
        String nome = "K";
        String cognome = "D";
        int eta = 24;
        char sesso = 'M';
        String cittaResidenza = "Siracusa";
        String cellulare = "32323232";
        String password = "KRI";
        
        Utente u = new Utente(cf, nome, cognome, eta, sesso, cittaResidenza, cellulare, password);
        boolean result = os.modificaDatiUtente(cf, nome, cognome, eta, sesso, cittaResidenza, cellulare, password);
        assertTrue(result);
    }
    
    @Test
    public void testEliminaUtente() {
        System.out.println("eliminaUtente");
        boolean result = os.eliminaUtente();
        assertTrue(result);
    }
    
}

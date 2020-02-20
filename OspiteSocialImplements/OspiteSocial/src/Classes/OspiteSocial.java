package Classes;

import DAO.MySQLUtenteDAOImpl;
import DAO.MySQLPrenotazioneDAOImpl;
import DAO.MySQLAbitazioneDAOImpl;
import DAO.MySQLStanzaDAOImpl;
import DAO.PrenotazioneDAO;
import DAO.StanzaDAO;
import DAO.AbitazioneDAO;
import DAO.UtenteDAO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class OspiteSocial {
    
 private static OspiteSocial instance;
 private static Utente utente;
 
    private OspiteSocial() {}
    public static OspiteSocial getInstance() {
        if (instance==null){
            System.err.println("Istanza creata");
            instance = new OspiteSocial();
        }
        return instance;
    }
    
    private Integer getCreditiUtente(){
        return this.utente.getCredito();
    }
    
    private void setCreditiUtente(Integer crediti){
        this.utente.setCredito(crediti);
    }
    
    public   Utente visualizzaInformazioniUtente(String cf,String password) {
        UtenteDAO utenteDAO = new MySQLUtenteDAOImpl();  
        Utente u = utenteDAO.getUtente(cf,password);
      
        utente=u;
        return u;
    }
    
     public   Utente visualizzaInformazioniUtente(String cf) {
        UtenteDAO utenteDAO = new MySQLUtenteDAOImpl();  
        Utente u = utenteDAO.getUtente(cf);
        return u;
    }
     
    public   Utente inserisciUtente(String cf,String nome,String cognome,int eta,char sesso,String cittaResidenza,String cellulare,String password) {
        Utente u=new Utente(cf,nome,cognome,eta,sesso,cittaResidenza,cellulare,password);
        UtenteDAO utenteDAO = new MySQLUtenteDAOImpl();
        Integer res=0;
        res=utenteDAO.createUtente(u);
            if(res==-1)
                return null;
            else
                return u;
    }
    
    public   boolean eliminaUtente() {
        UtenteDAO utenteDAO = new MySQLUtenteDAOImpl();  
        boolean res=utenteDAO.deleteUtente(utente);
        return res;
    }
    
    public String getCf(){
        return utente.getCf();
    }
    
    public   boolean modificaDatiUtente(String cf,String nome,String cognome,int eta,char sesso,String cittaResidenza,String cellulare,String password) {
        Utente u=new Utente(cf,nome,cognome,eta,sesso,cittaResidenza,cellulare,password);
        UtenteDAO utenteDAO = new MySQLUtenteDAOImpl();  
        boolean res=utenteDAO.updateUtente(u,utente.getCf());
            if(res==true)utente=u;
        return res;
    }
    
    public  Integer inserimentoAlloggio(String cf ,String indirizzo,String citta,Float distanzaCentro,Float distanzaStazione,Integer tempoTermineModifiche,Date dataInizioDisponibilita,Date dataFineDisponibilita,Integer tariffaGiornaliera){
        Abitazione abitazione=new Abitazione(21,  cf ,indirizzo,citta,distanzaCentro,distanzaStazione,tempoTermineModifiche,dataInizioDisponibilita,dataFineDisponibilita,tariffaGiornaliera);
        Integer codiceAbitazione;
        AbitazioneDAO abitazioneDAO = new MySQLAbitazioneDAOImpl();
        codiceAbitazione=abitazioneDAO.createAbitazione(abitazione);
        return codiceAbitazione;
    }
    
    public  boolean modificaDatiAlloggio(Integer codiceAbitazione,String cf ,String indirizzo,String citta,Float distanzaCentro,Float distanzaStazione,Integer tempoTermineModifiche,Date dataInizioDisponibilita,Date dataFineDisponibilita,Integer tariffaGiornaliera){
        boolean res;
        Abitazione abitazione=new Abitazione(codiceAbitazione, cf , indirizzo, citta, distanzaCentro, distanzaStazione, tempoTermineModifiche, dataInizioDisponibilita, dataFineDisponibilita,tariffaGiornaliera);
        AbitazioneDAO abitazioneDAO = new MySQLAbitazioneDAOImpl();
        res=abitazioneDAO.updateAbitazione(abitazione);
        return res;
    }
    
     public  boolean eliminaAlloggio(Integer codiceAbitazione,String cf ,String indirizzo,String citta,Float distanzaCentro,Float distanzaStazione,Integer tempoTermineModifiche,Date dataInizioDisponibilita,Date dataFineDisponibilita){
        boolean res;
        Abitazione abitazione=new Abitazione(codiceAbitazione, cf , indirizzo, citta, distanzaCentro, distanzaStazione, tempoTermineModifiche, dataInizioDisponibilita, dataFineDisponibilita);
        AbitazioneDAO abitazioneDAO = new MySQLAbitazioneDAOImpl();
        res=abitazioneDAO.deleteAbitazione(abitazione);
        return res;
    }
    
    public  Abitazione visualizzaAlloggio(Integer codiceAbitazione){
        AbitazioneDAO abitazioneDAO = new MySQLAbitazioneDAOImpl();  
        Abitazione a = abitazioneDAO.getAbitazione(codiceAbitazione);
        return a;
    }

    public  void setUtente(Utente utente) {
        OspiteSocial.utente = utente;
    }
    
    
    public  List<Abitazione>  visualizzaAlloggiUtente(String cf){
        List<Abitazione> abitazioni=null;
        AbitazioneDAO abitazioneDAO = new MySQLAbitazioneDAOImpl();
        abitazioni=abitazioneDAO.getAllAbitazioni(cf);
        if(abitazioni.size()==0)return null;
        else return abitazioni;
    }
        
    public  List<Stanza>  visualizzaStanzeAbitazione(Integer  codiceAbitazione){
        List<Stanza> stanze=null;
        StanzaDAO stanzaDAO = new MySQLStanzaDAOImpl();
        stanze=stanzaDAO.getAllStanze(codiceAbitazione);
        if(stanze.size()==0)return null;
        else return stanze;
    }
     
    public  int inserimentoDatiStanza(String tipologiaStanza,String tipologiaPostoLetto,Integer numPostiLetto,Integer codiceAbitazione){
        int res;
        Stanza stanza=new Stanza(1,tipologiaStanza,tipologiaPostoLetto,numPostiLetto,codiceAbitazione);
        StanzaDAO stanzaDAO = new MySQLStanzaDAOImpl();
        res=stanzaDAO.createStanza(stanza);
        return res; 
    }
    
   public  boolean modificaDatiStanza(Integer codiceStanza,String tipologiaStanza,String tipologiaPostoLetto,Integer numPostiLetto){
        boolean res;
        Stanza stanza=new Stanza(codiceStanza,tipologiaStanza,tipologiaPostoLetto,numPostiLetto,1);
        StanzaDAO stanzaDAO = new MySQLStanzaDAOImpl();
        res=stanzaDAO.updateStanza(stanza);
        return res; 
    }
   
   public  List<Abitazione> ricercaAlloggio(String citta,Date dataInizio,Date dataFine,Integer  posti){
        List<Abitazione> abitazioni=new ArrayList();
        PrenotazioneDAO prenotazioneDAO = new MySQLPrenotazioneDAOImpl();
            if(dataInizio.compareTo(dataFine)<0)abitazioni=prenotazioneDAO.searchAlloggio(OspiteSocial.getInstance().getCf(),citta,dataInizio,dataFine,posti);
       
            for(int i=0;i<abitazioni.size();i++){
                StanzaDAO stanzaDAO = new MySQLStanzaDAOImpl();
                abitazioni.get(i).setStanze(stanzaDAO.getAllStanze(abitazioni.get(i).getId(),dataInizio,dataFine,posti)); 
            }
        return abitazioni;
   }
   public  boolean richiestaPrenotazione(Integer codiceAbitazione, Date dataInizio,Date dataFine,Integer  posti,List<String> utentiScelti,List<Integer> stanzeScelte){
        boolean res;
        if(OspiteSocial.getInstance().getCreditiUtente()<0){
            JOptionPane.showMessageDialog(null, "Crediti insufficienti!", "", 2);
            return false;
        }
        if(posti!=utentiScelti.size()){
            JOptionPane.showMessageDialog(null, "Immetti numero di utenti pari a posti!", "", 2);
            return false;
        }
            if(utentiScelti.isEmpty() || stanzeScelte.isEmpty())return false;
        PrenotazioneDAO prenotazioneDAO = new MySQLPrenotazioneDAOImpl();
        res=prenotazioneDAO.makePrenotazione(codiceAbitazione,dataInizio,dataFine,posti,utentiScelti,stanzeScelte);
        return res; 
   }
   
   public  List<Prenotazione>  visualizzaPrenotazioni(String cf){
        List<Prenotazione> prenotazioni=null;
        PrenotazioneDAO prenotazioneDAO = new MySQLPrenotazioneDAOImpl();
        prenotazioni=prenotazioneDAO.showPrenotazioni(cf);
        if(prenotazioni.size()==0)return null;
        else return prenotazioni;
    }
   
   public  List<Prenotazione>  visualizzaRichieste(String cf){
        List<Prenotazione> prenotazioni=null;
        PrenotazioneDAO prenotazioneDAO = new MySQLPrenotazioneDAOImpl();
        prenotazioni=prenotazioneDAO.showRichieste(cf);
        if(prenotazioni.size()==0)return null;
        else return prenotazioni;
   }
   
   public boolean accettaRichiesta(Integer codicePrenotazione){
       boolean res;
       PrenotazioneDAO prenotazioneDAO = new MySQLPrenotazioneDAOImpl();
       res=prenotazioneDAO.setStato("Accettata",codicePrenotazione);
       return res;
   }
   
    public boolean rifiutaRichiesta(Integer codicePrenotazione,String motivazione){
        boolean res;
        PrenotazioneDAO prenotazioneDAO = new MySQLPrenotazioneDAOImpl();
        res=prenotazioneDAO.setStato("Rifiutata",codicePrenotazione);
            if(res==true){
                res=prenotazioneDAO.setMotivazione(codicePrenotazione,motivazione);
                return res;
            }else{
                return false;
            }
   }
    
    public boolean checkIn(Integer codicePrenotazione,Date data){
        boolean res;
        PrenotazioneDAO prenotazioneDAO = new MySQLPrenotazioneDAOImpl();
        res=prenotazioneDAO.setCheckIn(codicePrenotazione,data);
        
            if(res){
                Integer crediti=prenotazioneDAO.makeCrediti(codicePrenotazione);
                    if(crediti==0){
                        return false;
                    }else{
                        String ospitante;
                        List<String > ospiti;
                        
                        ospitante=prenotazioneDAO.getOspitante(codicePrenotazione);
                        ospiti=prenotazioneDAO.getOspiti(codicePrenotazione);
                        
                        this.setCreditiUtente(crediti);
                        
                        UtenteDAO utenteDAO = new MySQLUtenteDAOImpl();  
                        utenteDAO.addCrediti(ospitante,crediti *ospiti.size());
                            for(int i=0;i<ospiti.size();i++){
                                utenteDAO.removeCrediti(ospiti.get(i),crediti);
                            }  
                    }
            }
        return res;
   }
    
    public boolean checkOut(Integer codicePrenotazione,Date data){
        boolean res;
        PrenotazioneDAO prenotazioneDAO = new MySQLPrenotazioneDAOImpl();
        res=prenotazioneDAO.setCheckOut(codicePrenotazione,data);
        prenotazioneDAO.setStato("Terminato",codicePrenotazione);
        return res;
   }
   
}

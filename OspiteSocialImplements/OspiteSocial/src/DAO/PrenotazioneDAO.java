package DAO;

import java.sql.Date;
import java.util.List;
import Classes.Abitazione;
import Classes.Prenotazione;

public interface PrenotazioneDAO {
    
        public List<Prenotazione> showPrenotazioni(String cf);
        
        public List<Prenotazione> showRichieste(String cf);
	
        public List<Abitazione> searchAlloggio(String cf,String citta,Date dataInizio,Date dataFine,Integer  posti);
        
        public boolean makePrenotazione(Integer codiceAbitazione,Date dataInizio,Date dataFine,Integer  posti,List<String> utentiScelti,List<Integer> stanzeScelte);

        public boolean setStato(String stato,Integer codicePrenotazione);
        
        public boolean setMotivazione(Integer codicePrenotazione,String motivazione);
        
        public boolean setCheckIn(Integer codicePrenotazione,Date data);
        
        public boolean setCheckOut(Integer codicePrenotazione,Date data);
        
        public Integer makeCrediti(Integer codicePrenotazione);
        
        public String getOspitante(Integer codicePrenotazione);
        
        public List<String> getOspiti(Integer codicePrenotazione);
        
        
}

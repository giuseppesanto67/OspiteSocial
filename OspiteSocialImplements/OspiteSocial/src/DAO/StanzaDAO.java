package DAO;

import java.sql.Date;
import java.util.List;
import Classes.Stanza;

public interface StanzaDAO {
    
	public List getAllStanze(Integer codiceAbitazione);
        
        public List getAllStanze(Integer codiceAbitazione,Date dataInizio,Date dataFine,Integer  posti);
       
        public Stanza getStanza(Integer id);
	
	public int createStanza(Stanza stanza);
	
        public boolean updateStanza(Stanza stanza);
}

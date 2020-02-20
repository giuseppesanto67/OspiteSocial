package DAO;

import java.util.List;
import Classes.Abitazione;


public interface AbitazioneDAO {
    
	public List getAllAbitazioni(String cf);
	 
        public Abitazione getAbitazione(Integer id);
	
	public int createAbitazione(Abitazione abitazione);
	
        public boolean updateAbitazione(Abitazione abitazione);
	
	public boolean deleteAbitazione(Abitazione abitazione);
}

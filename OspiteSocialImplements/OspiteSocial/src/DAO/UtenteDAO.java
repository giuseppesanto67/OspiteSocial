package DAO;

import java.util.List;
import Classes.Utente;

public interface UtenteDAO {

	public List getAllUtenti();
	
	public Utente getUtente(String cf,String password);
        
        public Utente getUtente(String cf);
	
	public int createUtente(Utente utente);
	
	public boolean updateUtente(Utente utente,String cf);
	
	public boolean deleteUtente(Utente utente);
        
        public boolean addCrediti(String cf,Integer crediti);
        
        public boolean removeCrediti(String cf,Integer crediti);
}
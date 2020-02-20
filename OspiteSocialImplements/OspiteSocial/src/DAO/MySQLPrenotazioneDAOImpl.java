package DAO;

import Classes.*;
import Classes.Prenotazione;
import DAO.PrenotazioneDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import Classes.Prenotazione;

public class MySQLPrenotazioneDAOImpl implements PrenotazioneDAO{
   
    private static final String READ_ALL_ABITAZIONI= "Select * from abitazioni a where citta=? and ( select sum(s.numeroPostiLetto) from stanze s where s.codiceAbitazione=a.id and ( select count(*) from stanzeoccupate st where st.codiceStanza=s.id and ( select count(*) from prenotazioni p where p.id=st.codicePrenotazione and ((?>p.dataInizio and ?>p.dataInizio) and (?<p.dataFine and ?<p.dataFine)) or (stato <> 'Accettata' and stato <> 'Da confermare'))=0)=0)>=? and proprietario<>? and ? >= a.dataInizioDisponibilita and ? <= a.dataFineDisponibilita";
    
    private static final String READ_ALL_PRENOTAZIONI="SELECT * FROM `prenotazioni` WHERE id in (select o.codicePrenotazione from ospiti o where o.cf=?)";
    
    private static final String READ_ALL_RICHIESTE="SELECT * FROM `prenotazioni` WHERE ospitante=?";
    
    private static final String READ_ALL_PRENOTAZIONI_UTENTE="SELECT cf FROM `ospiti` WHERE codicePrenotazione=?";
    
    private static final String READ_ALL_PRENOTAZIONI_STANZA="SELECT codiceStanza FROM `stanzeoccupate` WHERE codicePrenotazione=?";
    
    private static final String INSERT_PRENOTAZIONE="INSERT INTO `prenotazioni`(`codiceAbitazione`, `ospitante`, `dataInizio`, `dataFine`) VALUES (?,?,?,?)";
    
    private static final String INSERT_PRENOTAZIONE_UTENTE="INSERT INTO `ospiti`(`codiceprenotazione`, `cf`) VALUES (?,?)";
            
    private static final String INSERT_PRENOTAZIONE_STANZA="INSERT INTO `stanzeoccupate`(`codicePrenotazione`, `codiceStanza`) VALUES (?,?)";
    
    private static final String SET_STATO="UPDATE `prenotazioni` SET `stato`=? WHERE `id`=?";
    
    private static final String SET_MOTIVAZIONE="UPDATE `prenotazioni` SET `motivazioni`=? WHERE `id`=?";
    
    private static final String SET_CHECKIN="UPDATE `prenotazioni` SET `checkin`=? WHERE `id`=? and ?>=dataInizio and ?<dataFine";
    
    private static final String SET_CHECKOUT="UPDATE `prenotazioni` SET `checkout`=? WHERE `id`=? and ?>dataInizio and ?<=dataFine and checkin<?";
    
    private static final String MAKE_CREDITI="SELECT (p.dataFine-p.dataInizio)*a.tariffaGiornaliera FROM prenotazioni p JOIN abitazioni a ON p.codiceAbitazione=a.id  and p.id=?";
    
    private static final String GET_OSPITANTE="SELECT ospitante FROM `prenotazioni` WHERE id=?";
    
    private static final String GET_OSPITI="SELECT cf FROM `prenotazioni` join ospiti on id=codicePrenotazione WHERE id = ?";
      
    private static final String DELETE_PRENOTAZIONE="DELETE FROM `prenotazioni` WHERE ID=? ";
    
        public List<Prenotazione> showPrenotazioni(String cf) {
            List<Prenotazione> prenotazioni = new ArrayList();
            Prenotazione prenotazione = null;
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            ResultSet resultPrenotazione=null;
            
                try {
                    conn = MySQLDAOFactory.createConnection();            
                    preparedStatement = conn.prepareStatement(READ_ALL_PRENOTAZIONI);
                    preparedStatement.setString(1, cf);
                    preparedStatement.execute();
                    resultPrenotazione = preparedStatement.getResultSet();
                    result = preparedStatement.getResultSet();
                    Integer codicePrenotazione;

                        while (resultPrenotazione.next() && resultPrenotazione!=null) {       

                            prenotazione = new Prenotazione(resultPrenotazione.getInt(1),resultPrenotazione.getInt(2), resultPrenotazione.getString(3),resultPrenotazione.getDate(4), resultPrenotazione.getDate(5), resultPrenotazione.getDate(6), resultPrenotazione.getDate(7),resultPrenotazione.getString(8),resultPrenotazione.getString(8));

                            codicePrenotazione=resultPrenotazione.getInt(1);
                            //inserimento utenti
                            preparedStatement = conn.prepareStatement(READ_ALL_PRENOTAZIONI_UTENTE);
                            preparedStatement.setInt(1, codicePrenotazione);
                            preparedStatement.execute();
                            result = preparedStatement.getResultSet();
                            List<String> utenti=new ArrayList();

                                while (result.next() && result!=null) {
                                        utenti.add(result.getString(1));
                                }
                            prenotazione.setOspiti(utenti);

                            preparedStatement = conn.prepareStatement(READ_ALL_PRENOTAZIONI_STANZA);
                            preparedStatement.setInt(1, codicePrenotazione);
                            preparedStatement.execute();
                            result = preparedStatement.getResultSet();
                            List<Integer> stanze=new ArrayList();

                                while (result.next() && result!=null) {
                                        stanze.add(result.getInt(1));
                                }
                            prenotazione.setStanze(stanze);

                            prenotazioni.add(prenotazione);
                        }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        result.close();
                    } catch (Exception rse) {
                        rse.printStackTrace();
                    }
                    try {
                        preparedStatement.close();
                    } catch (Exception sse) {
                        sse.printStackTrace();
                    }
                    try {
                        conn.close();
                    } catch (Exception cse) {
                        cse.printStackTrace();
                    }
                }
            return prenotazioni;
        }
        
        public List<Prenotazione> showRichieste(String cf) {
            List<Prenotazione> prenotazioni = new ArrayList();
            Prenotazione prenotazione = null;
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            ResultSet resultPrenotazione=null;
                try {
                    conn = MySQLDAOFactory.createConnection();            
                    preparedStatement = conn.prepareStatement(READ_ALL_RICHIESTE);
                    preparedStatement.setString(1, cf);
                    preparedStatement.execute();
                    resultPrenotazione = preparedStatement.getResultSet();
                    result = preparedStatement.getResultSet();
                    Integer codicePrenotazione;
                    
                    while (resultPrenotazione.next() && resultPrenotazione!=null) {       
                        prenotazione = new Prenotazione(resultPrenotazione.getInt(1),resultPrenotazione.getInt(2), resultPrenotazione.getString(3),resultPrenotazione.getDate(4), resultPrenotazione.getDate(5), resultPrenotazione.getDate(6), resultPrenotazione.getDate(7),resultPrenotazione.getString(8),resultPrenotazione.getString(8));
                        codicePrenotazione=resultPrenotazione.getInt(1); 
                        preparedStatement = conn.prepareStatement(READ_ALL_PRENOTAZIONI_UTENTE);
                        preparedStatement.setInt(1, codicePrenotazione);
                        preparedStatement.execute();
                        result = preparedStatement.getResultSet();

                        List<String> utenti=new ArrayList();
                            while (result.next() && result!=null) {
                                    utenti.add(result.getString(1));
                            }
                        prenotazione.setOspiti(utenti);

                        preparedStatement = conn.prepareStatement(READ_ALL_PRENOTAZIONI_STANZA);
                        preparedStatement.setInt(1, codicePrenotazione);
                        preparedStatement.execute();
                        result = preparedStatement.getResultSet();

                        List<Integer> stanze=new ArrayList();
                            while (result.next() && result!=null) {
                                    stanze.add(result.getInt(1));
                            }
                        prenotazione.setStanze(stanze);
                        prenotazioni.add(prenotazione);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        result.close();
                    } catch (Exception rse) {
                        rse.printStackTrace();
                    }
                    try {
                        preparedStatement.close();
                    } catch (Exception sse) {
                        sse.printStackTrace();
                    }
                    try {
                        conn.close();
                    } catch (Exception cse) {
                        cse.printStackTrace();
                    }
                }
            return prenotazioni;
        }
    
    
    public boolean makePrenotazione(Integer codiceAbitazione,Date dataInizio,Date dataFine,Integer  posti,List<String> utentiScelti,List<Integer> stanzeScelte){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        Integer duplicated=0;
         
            try {
                conn = MySQLDAOFactory.createConnection();         
                    for(int i=0;i<utentiScelti.size();i++){
                            preparedStatement = conn.prepareStatement("SELECT count(*) FROM `utenti` WHERE cf=?");
                            preparedStatement.setString(1, utentiScelti.get(i));
                            preparedStatement.execute();
                            result = preparedStatement.getResultSet();
                                if(result.next())if(result.getInt(1)==0)return false;

                            preparedStatement = conn.prepareStatement("select count(*) from prenotazioni p join ospiti o on p.id=o.codiceprenotazione where cf=? and p.dataInizio=? and p.dataFine=? and p.codiceAbitazione=?");
                            preparedStatement.setString(1, utentiScelti.get(i));
                            preparedStatement.setDate(2, dataInizio);
                            preparedStatement.setDate(3, dataFine);
                            preparedStatement.setInt(4,codiceAbitazione);

                            preparedStatement.execute();
                            result = preparedStatement.getResultSet();

                                if(result.next()) {            	
                                    duplicated+=result.getInt(1);
                                }  
                    }

                    if(duplicated!=0)return false;
                preparedStatement = conn.prepareStatement("select proprietario from abitazioni a where a.id=?");
                preparedStatement.setInt(1, codiceAbitazione);
                preparedStatement.execute();
                result = preparedStatement.getResultSet();
                String ospitante=null;
                    if(result.next()==true)ospitante=result.getString(1);
                    if(utentiScelti.contains(ospitante))return false;  

                preparedStatement = conn.prepareStatement(INSERT_PRENOTAZIONE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, codiceAbitazione);
                preparedStatement.setString(2, ospitante);
                preparedStatement.setDate(3, dataInizio);
                preparedStatement.setDate(4, dataFine);

                preparedStatement.executeUpdate();
                result = preparedStatement.getGeneratedKeys();

                    if (result.next() && result!=null) {
                        for(int i=0;i<utentiScelti.size();i++){
                            preparedStatement = conn.prepareStatement(INSERT_PRENOTAZIONE_UTENTE);
                            preparedStatement.setInt(1, result.getInt(1));
                            preparedStatement.setString(2, utentiScelti.get(i)); 
                            preparedStatement.execute();
                        }

                    for(int i=0;i<stanzeScelte.size();i++){
                        preparedStatement = conn.prepareStatement(INSERT_PRENOTAZIONE_STANZA);
                        preparedStatement.setInt(1, result.getInt(1));
                        preparedStatement.setInt(2, stanzeScelte.get(i)); 
                        preparedStatement.execute();
                    }

                } else {
                    return false;
                }    
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    result.close();
                } catch (Exception rse) {
                    rse.printStackTrace();
                    return false;
                }
                try {
                    preparedStatement.close();
                } catch (Exception sse) {
                    sse.printStackTrace();
                    return false;
                }
                try {
                    conn.close();
                } catch (Exception cse) {
                    cse.printStackTrace();
                    return false;
                }
            }
        return true;
      }
  
	public List<Abitazione> searchAlloggio(String cf,String citta,Date dataInizio,Date dataFine,Integer  posti) {
            List abitazioni = new ArrayList();
            Abitazione abitazione = null;
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            
                try {
                    conn = MySQLDAOFactory.createConnection();            
                    preparedStatement = conn.prepareStatement(READ_ALL_ABITAZIONI);
                    preparedStatement.setString(1, citta);
                    preparedStatement.setDate(2, dataInizio);
                    preparedStatement.setDate(3, dataFine);
                    preparedStatement.setDate(4, dataInizio);
                    preparedStatement.setDate(5, dataFine);
                    preparedStatement.setInt(6, posti);
                    preparedStatement.setString(7, cf);
                    preparedStatement.setDate(8, dataInizio);
                    preparedStatement.setDate(9, dataFine);

                    preparedStatement.execute();
                    result = preparedStatement.getResultSet();

                        while (result.next()) {            	
                            abitazione = new Abitazione(result.getInt(1),result.getString(2), result.getString(3),result.getString(4), result.getFloat(5), result.getFloat(6), result.getInt(7),result.getDate(8),result.getDate(9),result.getInt(10));
                            abitazioni.add(abitazione);
                        }     
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        result.close();
                    } catch (Exception rse) {
                        rse.printStackTrace();
                    }
                    try {
                        preparedStatement.close();
                    } catch (Exception sse) {
                        sse.printStackTrace();
                    }
                    try {
                        conn.close();
                    } catch (Exception cse) {
                        cse.printStackTrace();
                    }
                }
            return abitazioni;
	}
        
        public boolean setStato(String stato,Integer codicePrenotazione){
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            Integer res;
            
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(SET_STATO);
                    preparedStatement.setInt(2, codicePrenotazione);
                    preparedStatement.setString(1, stato);
                    res=preparedStatement.executeUpdate();

                    if(res>0)return true;
                    else return false; 
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    try {
                        preparedStatement.close();
                    } catch (Exception sse) {
                        sse.printStackTrace();
                        return false;
                    }
                    try {
                        conn.close();
                    } catch (Exception cse) {
                        cse.printStackTrace();
                        return false;
                    }
                }
           
        }
        
        public boolean setMotivazione(Integer codicePrenotazione,String motivazione){
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            Integer res; 
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(SET_MOTIVAZIONE);
                    preparedStatement.setInt(2, codicePrenotazione);
                    preparedStatement.setString(1, motivazione);
                    res=preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    try {
                        preparedStatement.close();
                    } catch (Exception sse) {
                        sse.printStackTrace();
                        return false;
                    }
                    try {
                        conn.close();
                    } catch (Exception cse) {
                        cse.printStackTrace();
                        return false;
                    }
                }
                if(res>0)return true;
            return false;
        }
        
       public boolean setCheckIn(Integer codicePrenotazione,Date data){
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            Integer res;
                    
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(SET_CHECKIN);
                    preparedStatement.setDate(1, data);
                    preparedStatement.setInt(2, codicePrenotazione);
                    preparedStatement.setDate(3, data);
                    preparedStatement.setDate(4, data);
                    
                    res=preparedStatement.executeUpdate();
                    
                        if(res>0)return true;
                        else return false; 
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    try {
                        preparedStatement.close();
                    } catch (Exception sse) {
                        sse.printStackTrace();
                        return false;
                    }
                    try {
                        conn.close();
                    } catch (Exception cse) {
                        cse.printStackTrace();
                        return false;
                    }
                } 
       }
       
        public boolean setCheckOut(Integer codicePrenotazione,Date data){
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            Integer res;
                    
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(SET_CHECKOUT);
                    preparedStatement.setDate(1, data);
                    preparedStatement.setInt(2, codicePrenotazione);
                    preparedStatement.setDate(3, data);
                    preparedStatement.setDate(4, data);
                    preparedStatement.setDate(5, data);
                    res=preparedStatement.executeUpdate();

                        if(res>0)return true;
                        else return false; 
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    try {
                        preparedStatement.close();
                    } catch (Exception sse) {
                        sse.printStackTrace();
                        return false;
                    }
                    try {
                        conn.close();
                    } catch (Exception cse) {
                        cse.printStackTrace();
                        return false;
                    }
                }
            }
        
        public Integer makeCrediti(Integer codicePrenotazione){
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            
                try {
                    conn = MySQLDAOFactory.createConnection();            
                    preparedStatement = conn.prepareStatement(MAKE_CREDITI);
                    preparedStatement.setInt(1, codicePrenotazione);

                    preparedStatement.execute();
                    result = preparedStatement.getResultSet();

                        if (result.next()) {            	
                           if(result.getInt(1)>0)return result.getInt(1);
                           else return 0;
                        }     
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        result.close();
                    } catch (Exception rse) {
                        rse.printStackTrace();
                        return 0;
                    }
                    try {
                        preparedStatement.close();
                    } catch (Exception sse) {
                        sse.printStackTrace();
                        return 0;
                    }
                    try {
                        conn.close();
                    } catch (Exception cse) {
                        cse.printStackTrace();
                        return 0;
                    }
                }
            return 0;
        }
        
        public String getOspitante(Integer codicePrenotazione){
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            
                try {
                    conn = MySQLDAOFactory.createConnection();            
                    preparedStatement = conn.prepareStatement(GET_OSPITANTE);
                    preparedStatement.setInt(1, codicePrenotazione);

                    preparedStatement.execute();
                    result = preparedStatement.getResultSet();

                        if (result.next()) {            	
                          return result.getString(1);
                        }else return null;
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        result.close();
                    } catch (Exception rse) {
                        rse.printStackTrace();
                        return null;
                    }
                    try {
                        preparedStatement.close();
                    } catch (Exception sse) {
                        sse.printStackTrace();
                        return null;
                    }
                    try {
                        conn.close();
                    } catch (Exception cse) {
                        cse.printStackTrace();
                        return null;
                    }
                }
            return null;
        }
        
        
        public List<String> getOspiti(Integer codicePrenotazione){
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            List<String> utenti=new ArrayList<String>();
            
                try {
                    conn = MySQLDAOFactory.createConnection();            
                    preparedStatement = conn.prepareStatement(GET_OSPITI);
                    preparedStatement.setInt(1, codicePrenotazione);

                    preparedStatement.execute();
                    result = preparedStatement.getResultSet();

                        while (result.next() && result!=null) {
                                    utenti.add(result.getString(1));
                        }
                    return utenti;
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        result.close();
                    } catch (Exception rse) {
                        rse.printStackTrace();
                        return null;
                    }
                    try {
                        preparedStatement.close();
                    } catch (Exception sse) {
                        sse.printStackTrace();
                        return null;
                    }
                    try {
                        conn.close();
                    } catch (Exception cse) {
                        cse.printStackTrace();
                        return null;
                    }
                }
            return null;
        }
        
        
}

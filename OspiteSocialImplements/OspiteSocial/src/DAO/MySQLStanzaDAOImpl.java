package DAO;

import Classes.*;
import DAO.StanzaDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MySQLStanzaDAOImpl implements StanzaDAO{
    	
    private static final String CREATE_QUERY = "INSERT INTO `stanze`( `tipologiaStanza`, `tipologiaPostoLetto`,`numeroPostiLetto`, `codiceAbitazione`) VALUES (?,?,?,?)";
    
    private static final String READ_QUERY = "SELECT * FROM stanze WHERE id = ? ";
    
    private static final String READ_ALL_QUERY = "SELECT * FROM stanze where codiceAbitazione = ?";
    
    private static final String UPDATE_QUERY = "UPDATE `stanze` SET  `tipologiaStanza`=?,`tipologiaPostoLetto`=?,`numeroPostiLetto`=? WHERE id=?";
    
    private static final String READ_ALL_STANZE= "select * from stanze s where   (select count(*) from stanzeoccupate st where st.codiceStanza=s.id and (select count(*) from prenotazioni p where p.id=st.codicePrenotazione and ((?<p.dataInizio and ?<p.dataFine) or (?>p.dataInizio and ?>p.dataFine)) or (stato <> 'Accettata' and stato <> 'Da confermare') )=0)=0 and s.codiceAbitazione = ?";

	public List getAllStanze(Integer codiceAbitazione) {
            List stanze = new ArrayList();
            Stanza stanza = null;
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            
                try {
                    conn = MySQLDAOFactory.createConnection();            
                    preparedStatement = conn.prepareStatement(READ_ALL_QUERY);
                    preparedStatement.setInt(1, codiceAbitazione);
                    preparedStatement.execute();
                    result = preparedStatement.getResultSet();

                        while (result.next()) {            	
                            stanza = new Stanza(result.getInt(1),result.getString(2), result.getString(3),result.getInt(4), result.getInt(5));
                            stanze.add(stanza);
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
            return stanze;
	}
        
        public List<Stanza> getAllStanze(Integer codiceAbitazione,Date dataInizio,Date dataFine,Integer  posti) {
            List stanze = new ArrayList();
            Stanza stanza = null;
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
                try {
                    conn = MySQLDAOFactory.createConnection();            
                    preparedStatement = conn.prepareStatement(READ_ALL_STANZE);

                    preparedStatement.setDate(1, dataInizio);
                    preparedStatement.setDate(2, dataFine);
                    preparedStatement.setDate(3, dataInizio);
                    preparedStatement.setDate(4, dataFine);
                    preparedStatement.setInt(5, codiceAbitazione);
                    preparedStatement.execute();
                    result = preparedStatement.getResultSet();

                    Integer counter=0;
                        while (result.next()) {            	
                            stanza = new Stanza(result.getInt(1),result.getString(2), result.getString(3),result.getInt(4), result.getInt(5));
                                if(counter!=posti && !(posti==1 && stanza.getNumeroPotiLetto()==2) ){
                                    stanze.add(stanza);
                                    counter+=stanza.getNumeroPotiLetto();
                                }else if(counter==posti){
                                    break;
                                }
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
            return stanze;
	}
        
        public boolean updateStanza(Stanza stanza) {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            Integer res;
            
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(UPDATE_QUERY);
                    preparedStatement.setString(1, stanza.getTipologiaStanza());
                    preparedStatement.setString(2, stanza.getTipologiaPostoLetto() );
                    preparedStatement.setInt(3, stanza.getNumeroPotiLetto());
                    preparedStatement.setInt(4, stanza.getId());

                    res=preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    try {
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
                if(res>0)return true;
            return false;
	}

        public Stanza getStanza(Integer id) {
            Stanza stanza= null;
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(READ_QUERY);
                    preparedStatement.setInt(1,id);
                    preparedStatement.execute();
                    result = preparedStatement.getResultSet();

                        if (result.next() && result != null) {
                             stanza = new Stanza(result.getInt(1),result.getString(2), result.getString(3), result.getInt(4), result.getInt(5));
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
            return stanza;
	}
        
      
	
	public int createStanza(Stanza stanza) {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(CREATE_QUERY,Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, stanza.getTipologiaStanza());
                    preparedStatement.setString(2, stanza.getTipologiaPostoLetto() );
                    preparedStatement.setInt(3, stanza.getNumeroPotiLetto());
                    preparedStatement.setInt(4, stanza.getCodiceAbitazione() );

                    preparedStatement.executeUpdate();
                    result = preparedStatement.getGeneratedKeys();

                    if (result.next() && result != null) {
                        return result.getInt(1);
                    } else {
                        return -1;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                     return -1;
                } finally {
                    try {
                        result.close();
                    } catch (Exception rse) {
                        rse.printStackTrace();
                         return -1;
                    }
                    try {
                        preparedStatement.close();
                    } catch (Exception sse) {
                        sse.printStackTrace();
                         return -1;
                    }
                    try {
                        conn.close();
                    } catch (Exception cse) {
                        cse.printStackTrace();
                         return -1;
                    }
                }
        }
    	
}

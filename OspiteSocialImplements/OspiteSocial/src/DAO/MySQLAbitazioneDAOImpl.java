package DAO;

import Classes.Abitazione;
import DAO.AbitazioneDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLAbitazioneDAOImpl implements AbitazioneDAO{
   
    private static final String CREATE_QUERY = "INSERT INTO `abitazioni`(`proprietario`, `indirizzo`, `citta`, `distanzaCentro`, `distanzaStazione`, `tempoTermineModifiche`, `dataInizioDisponibilita`, `dataFineDisponibilita`) VALUES (?,?,?,?,?,?,?,?)";
    
    private static final String READ_QUERY = "SELECT * FROM abitazioni WHERE id = ? ";
   
    private static final String READ_ALL_QUERY = "SELECT * FROM abitazioni where proprietario = ?";
  
    private static final String UPDATE_QUERY = "UPDATE `abitazioni` SET `indirizzo`=?,`citta`=?,`distanzaCentro`=?,`distanzaStazione`=?,`tempoTermineModifiche`=?,`dataInizioDisponibilita`=?,`dataFineDisponibilita`=?, tariffaGiornaliera = ? WHERE id=? and proprietario=?";
    
    private static final String DELETE_QUERY = "DELETE FROM abitazioni WHERE id = ?";

	public List<Abitazione> getAllAbitazioni(String cf) {
            List abitazioni = new ArrayList();
            Abitazione abitazione = null;
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
                try {
                    conn = MySQLDAOFactory.createConnection();            
                    preparedStatement = conn.prepareStatement(READ_ALL_QUERY);
                    preparedStatement.setString(1, cf);
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

        public Abitazione getAbitazione(Integer id) {
            Abitazione abitazione= null;
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
                            abitazione = new Abitazione(result.getInt(1),result.getString(2), result.getString(3),result.getString(4), result.getFloat(5), result.getFloat(6), result.getInt(7),result.getDate(8),result.getDate(9),result.getInt(10));
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
            return abitazione;
	}
        
      
	
	public int createAbitazione(Abitazione abitazione) {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            Integer res;

            try {
                conn = MySQLDAOFactory.createConnection();
                preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, abitazione.getProprietario());
                preparedStatement.setString(2, abitazione.getIndirizzo() );
                preparedStatement.setString(3, abitazione.getCitta() );
                preparedStatement.setFloat(4, abitazione.getDistanzaCentro());
                preparedStatement.setFloat(5, abitazione.getDistanzaStazione());            
                preparedStatement.setInt(6, abitazione.getTempoTermineModifiche());
                preparedStatement.setDate(7, abitazione.getDataInizioDisponibilita());
                preparedStatement.setDate(8, abitazione.getDataFineDisponibilita());

                res=preparedStatement.executeUpdate();
                result = preparedStatement.getGeneratedKeys();

                    if(res==0 || res==-1)return 0;

                    if (result.next() && result != null) return result.getInt(1);
                    else return -1;
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
		

        public boolean updateAbitazione(Abitazione abitazione) {	
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            Integer res;

                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(UPDATE_QUERY);
                    preparedStatement.setString(1, abitazione.getIndirizzo() );
                    preparedStatement.setString(2, abitazione.getCitta() );
                    preparedStatement.setFloat(3, abitazione.getDistanzaCentro());
                    preparedStatement.setFloat(4, abitazione.getDistanzaStazione());
                    preparedStatement.setInt(5, abitazione.getTempoTermineModifiche());
                    preparedStatement.setDate(6, abitazione.getDataInizioDisponibilita());
                    preparedStatement.setDate(7, abitazione.getDataFineDisponibilita());
                    preparedStatement.setInt(8, abitazione.getTariffaGiornaliera());

                    preparedStatement.setInt(9, abitazione.getId());
                    preparedStatement.setString(10, abitazione.getProprietario());

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

        public boolean deleteAbitazione(Abitazione abitazione) {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            Integer res;
            
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(DELETE_QUERY);
                    preparedStatement.setInt(1, abitazione.getId());
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
}

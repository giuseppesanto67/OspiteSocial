
package DAO;

import Classes.*;
import DAO.UtenteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class MySQLUtenteDAOImpl implements UtenteDAO {

    private static final String CREATE_QUERY = "INSERT INTO `utenti`(`cf`, `nome`, `cognome`, `eta`, `sesso`, `cittaResidenza`, `cellulare`, `password`) VALUES (?,?,?,?,?,?,?,?)";
    
    private static final String READ_QUERY_LOGIN = "SELECT * FROM utenti WHERE cf = ? and password = ?";
    
    private static final String READ_QUERY = "SELECT * FROM utenti WHERE cf = ? ";
    
    private static final String READ_ALL_QUERY = "SELECT id, first_name, last_name FROM utente";
    
    private static final String UPDATE_QUERY = "UPDATE `utenti` SET `cf`=?,`nome`=?,`cognome`=?,`eta`=?,`sesso`=?,`cittaResidenza`=?,`cellulare`=?,`password`=? WHERE cf=?";
    
    private static final String DELETE_QUERY = "DELETE FROM utenti WHERE cf = ?";
    
    private static final String ADD_CREDITI = "UPDATE `utenti` SET credito = credito + ? WHERE cf=?";
     
    private static final String REMOVE_CREDITI = "UPDATE `utenti` SET credito = credito - ? WHERE cf=?";

	public List getAllUtenti() {
            List customers = new ArrayList();
            Utente customer = null;
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            
                try {
                    conn = MySQLDAOFactory.createConnection();            
                    preparedStatement = conn.prepareStatement(READ_ALL_QUERY);
                    preparedStatement.execute();
                    result = preparedStatement.getResultSet();

                    while (result.next()) {            	
                        customers.add(customer);
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
            return customers;
	}

        public Utente getUtente(String cf) {	
            Utente utente= null;
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(READ_QUERY);
                    preparedStatement.setString(1, cf);
                    preparedStatement.execute();
                    result = preparedStatement.getResultSet();
                   
                    if (result.next()) {
                       utente = new Utente(result.getString(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5).charAt(0),result.getString(6),result.getString(7),result.getString(8),result.getInt(9));
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
            return utente;
	}
        
        public Utente getUtente(String cf,String password) {
            Utente utente= null;
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
    
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(READ_QUERY_LOGIN);
                    preparedStatement.setString(1, cf);
                    preparedStatement.setString(2, password);
                    preparedStatement.execute();
                    result = preparedStatement.getResultSet();

                    if (result.next() && result != null) {
                        utente = new Utente(result.getString(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5).charAt(0),result.getString(6),result.getString(7),result.getString(8),result.getInt(9));
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
            return utente;
	}
	
	public int createUtente(Utente utente) {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, utente.getCf());
                    preparedStatement.setString(2, utente.getNome());
                    preparedStatement.setString(3, utente.getCognome());
                    preparedStatement.setInt(4, utente.getEta());
                    preparedStatement.setString(5, utente.getSesso() + "");
                    preparedStatement.setString(6, utente.getCittaResidenza());
                    preparedStatement.setString(7, utente.getCellulare());
                    preparedStatement.setString(8, utente.getPassword());

                    preparedStatement.executeUpdate();
                    result = preparedStatement.getGeneratedKeys();

                    if (result.next()) {
                        return result.getInt(1);
                    } 

                } catch (SQLException e) {
                    e.printStackTrace();
                    return -1;
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
        return 1;
    }
		

	public boolean updateUtente(Utente utente,String cf) {	
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            Integer res;
        
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(UPDATE_QUERY);
                    preparedStatement.setString(1, utente.getCf());
                    preparedStatement.setString(2, utente.getNome());
                    preparedStatement.setString(3, utente.getCognome());
                    preparedStatement.setInt(4, utente.getEta());
                    preparedStatement.setString(5, utente.getSesso() + "");
                    preparedStatement.setString(6, utente.getCittaResidenza());
                    preparedStatement.setString(7, utente.getCellulare());
                    preparedStatement.setString(8, utente.getPassword());
                    preparedStatement.setString(9, cf);

                    res= preparedStatement.executeUpdate();
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
            if(res>0) return true;
            else return false;
    }

	public boolean deleteUtente(Utente utente) {
            Connection conn = null;
            Integer res;
            PreparedStatement preparedStatement = null;
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(DELETE_QUERY);
                    preparedStatement.setString(1, utente.getCf());
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
                if(res>0) return true;
                else return false;
        }
        
        public boolean addCrediti(String cf,Integer crediti){
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            Integer res;
        
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(ADD_CREDITI);
                    preparedStatement.setInt(1, crediti);
                    preparedStatement.setString(2, cf);

                    res= preparedStatement.executeUpdate();
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
            if(res>0) return true;
            else return false;
        }
        
        public boolean removeCrediti(String cf,Integer crediti){
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            Integer res;
        
                try {
                    conn = MySQLDAOFactory.createConnection();
                    preparedStatement = conn.prepareStatement(REMOVE_CREDITI);
                    preparedStatement.setInt(1, crediti);
                    preparedStatement.setString(2, cf);
                   
                    res= preparedStatement.executeUpdate();
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
            if(res>0) return true;
            else return false;
        }

}
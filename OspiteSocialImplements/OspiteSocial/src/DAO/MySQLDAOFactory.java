package DAO;

import DAO.UtenteDAO;
import DAO.DAOFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
   
    public static final String DBURL = "jdbc:mysql://localhost:3306/ospitesocial";
    
    public static final String USER = "root";
   
    public static final String PASS = "";
    
    
    public static Connection createConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
        return conn;
    }	
}

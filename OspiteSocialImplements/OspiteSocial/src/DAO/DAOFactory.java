package DAO;

public abstract class DAOFactory {
 
    public static final int MYSQL = 0;  
    
    public static final int ORACLE = 1;
 
    public static DAOFactory getDAOFactory(int database) {
        switch (database) {
        case MYSQL:
            return new MySQLDAOFactory();
        default:
            return null;
        }
    }
}
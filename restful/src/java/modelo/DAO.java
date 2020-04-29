package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gdragoni
 */
public class DAO {
    protected Connection con;
    
    public DAO() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lixo?useTimezone=true&serverTimezone=GMT", "root", "");
    }
}

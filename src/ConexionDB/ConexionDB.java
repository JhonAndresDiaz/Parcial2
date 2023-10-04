
package ConexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author diaza
 */
public class ConexionDB {
    
    private final String url = "jdbc:postgresql://db.ezttuoqxqluufomxdyql.supabase.co:5432/postgres?user=postgres&password=nWY8TstROit2ZZXB";
    private static ConexionDB INSTANCE;
    private static Connection conn;

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    private ConexionDB () {
        
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ConexionDB getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new ConexionDB();
        }
        return INSTANCE;
    }
    public Connection getConnection(){
        return conn;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
}

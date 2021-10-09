package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Cnx {
    private String url="jdbc:mysql://localhost:3306/dbnews";
    private static Cnx instance=null;
    private Connection cn ;
    public static Cnx getInstance()
    { if (instance==null)
        synchronized(Cnx.class)
        {if  (instance==null)
            instance=new Cnx(); }
        return instance ; }
    public Connection connect()
    { try {
       // Class.forName("com.mysql.jdbc.Driver");
        cn=(Connection) DriverManager.getConnection(url,"root",null);
        // System.out.println("avec succés");

    }
    catch(SQLException e)
    {e.printStackTrace();}
        return cn; }

    public Cnx() {
    }
}

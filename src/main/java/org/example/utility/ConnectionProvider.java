package org.example.utility;
import java.sql.* ;
public class ConnectionProvider {
    static public Connection getConnection() throws SQLException
    {
        String url="jdbc:mysql://localhost:3306/anudip";
        return DriverManager.getConnection(url,"root","tiger");
    }
}

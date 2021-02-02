package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    Connection connection;
    //JDBC driver name & URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/testbase?useSSL=false&serverTimezone=UTC";
    static final String LOGIN = "root";
    static final String PASSWORD = "root";
    public Connection getConnection(){
        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
            }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}






package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "test";
        String userName = "root";
        String password = "root";

        return getConnection(hostName, dbName, userName, password);
    }

    public static Connection getConnection(String hostName, String dbName, String userName, String password)
            throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3307/"
                + dbName;

        return DriverManager.getConnection(connectionURL, userName, password);
    }

}

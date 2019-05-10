package dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static String url = "jdbc:postgresql://localhost:5432/InternetMarket";
    private static String user = "postgres";
    private static String password = "root";
    private static final Logger logger = Logger.getLogger(DBConnector.class);

    public static Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            logger.debug("Connection to the PostgreSQL server successfully");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("ConnectionDB error", e);
        } finally {
            return connection;
        }
    }

}

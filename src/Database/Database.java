package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    Connection connection = null;
    Statement statement = null;
    public ResultSet resultSet = null;

    public static Database db = null;

    public static Database getInstance() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    public Database() {

        // variables
        // Step 1: Loading or registering Oracle JDBC driver class
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        } catch (ClassNotFoundException cnfex) {
            System.out.println("Problem in loading or registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }

        // Step 2: Opening database connection
        try {

            String msAccessDBName = "Database2.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccessDBName;

            // Step 2.A: Create and get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL);

            // Step 2.B: Creating JDBC Statement 
            statement = connection.createStatement();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }

    public ResultSet query(String SQL) throws SQLException {
        statement = connection.createStatement();
        ResultSet result = statement.executeQuery(SQL);

        return result;
    }

    public int update(String SQL) throws SQLException {
        Statement stmt = connection.createStatement();
        int done = stmt.executeUpdate(SQL);
        return done;
    }
}

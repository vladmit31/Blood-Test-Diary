package seg.major.model;

import java.sql.*;

import seg.major.App;

public class DAOConnection {

    // Credentials
    private static final String URL = App.props.getProperty("db_url");
    private static final String USER = App.props.getProperty("db_user");
    private static final String PASSWORD = App.props.getProperty("db_password");
    private static final String DB_NAME = App.props.getProperty("db_name");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

package com.mh.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC简单操作
 * @author MH
 */
public class JDBCUtils {

    private static final String DEFAULT_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://123.206.255.2xx:3306/test?characterEncoding=UTF-8";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root123";

    static {
        try {
            Class.forName(DEFAULT_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        return createConnection(URL, USER_NAME, PASSWORD);
    }

    public static Connection createConnection(String url, String userName, String password) throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    public static Statement createStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    public static void close(Connection connection, Statement statement) throws SQLException {
        close(statement);
        close(connection);
    }

    public static void close(Statement statement) throws SQLException {
        if(statement != null){
            statement.close();
        }
    }

    public static void close(Connection connection) throws SQLException {
        if(connection != null){
            connection.close();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = JDBCUtils.createConnection();
        Statement statement = JDBCUtils.createStatement(connection);
//        boolean b = statement.execute("CREATE TABLE teacher (id int(11) PRIMARY KEY, name varchar(32));");
        boolean b  = statement.execute("DROP TABLE teacher");
        System.out.println("execute result:" + b);

    }
}

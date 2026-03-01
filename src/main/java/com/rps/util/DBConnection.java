package com.rps.util;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    private static String URL, USER, PASSWORD;
    static {
        try {
            Properties props = new Properties();
            InputStream in = DBConnection.class.getClassLoader()
                               .getResourceAsStream("db.properties");
            props.load(in);
            URL      = props.getProperty("db.url");
            USER     = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) { throw new ExceptionInInitializerError(e); }
    }
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void close(AutoCloseable... resources) {
        for (AutoCloseable r : resources)
            if (r != null) try { r.close(); } catch (Exception ignored) {}
    }
}

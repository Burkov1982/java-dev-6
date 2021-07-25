package ua.goit.config;

import ua.goit.view.ConsoleManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private final Properties properties = new Properties();
    private final ConsoleManager consoleManager = new ConsoleManager(System.in, System.out);
    public ConnectionManager(){
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/resources/database.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        try {
            consoleManager.write(properties.getProperty("db.host").toString() + "host");
            consoleManager.write(properties.getProperty("db.login").toString() + "login");
            consoleManager.write(properties.getProperty(properties.getProperty("db.password").toString()) + "pass");
            return DriverManager.getConnection(properties.getProperty("db.host"), properties.getProperty("db.login"),
                    properties.getProperty("db.password"));
        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
            throw new RuntimeException(throwables);
        }
    }
}

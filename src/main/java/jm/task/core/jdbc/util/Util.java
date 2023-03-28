package jm.task.core.jdbc.util;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://@localhost:3306/mydbtest";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_PASSWORD, DB_USERNAME);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection ERROR");
        }
        return connection;
    }

    private static SessionFactory sessionFactory = null;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            try {
                settings.put(Environment.DRIVER, DB_DRIVER);
                settings.put(Environment.URL, DB_URL);
                settings.put(Environment.USER, DB_USERNAME);
                settings.put(Environment.PASS, DB_PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL, "false");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

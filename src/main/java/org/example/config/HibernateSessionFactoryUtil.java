/*
package org.example.config;

import org.example.model.*;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class HibernateSessionFactoryUtil {
    @Bean
    @Scope("singleton")
    public SessionFactory getSessionFactory(){
        SessionFactory sessionFactory = null;
            try {
                sessionFactory = configuration().addProperties(properties()).buildSessionFactory();

                if (sessionFactory == null) System.out.println("Session factory is NULL!");
                else System.out.println("Session factory is not NULL!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        return sessionFactory;
    }


    private Properties properties(){
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.postgresql.Driver");
        settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
        settings.put(Environment.USER, "postgres");
        settings.put(Environment.PASS, "toor");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.POOL_SIZE, 10);
        settings.put(Environment.HBM2DDL_AUTO, "update");
        return settings;
    }

    private Configuration configuration(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(ExtraUserData.class);
        configuration.addAnnotatedClass(CarMark.class);
        configuration.addAnnotatedClass(CarModel.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(User.class);
        return configuration;
    }
}
*/

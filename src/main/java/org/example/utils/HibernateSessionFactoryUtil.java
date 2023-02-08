package org.example.utils;

import jakarta.persistence.criteria.Order;
import org.example.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import javax.management.relation.Role;
import java.util.Properties;

public enum HibernateSessionFactoryUtil {
    INSTANCE;

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
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

                //configuration.setProperties(settings);

                configuration.addAnnotatedClass(CarEntity.class);
                configuration.addAnnotatedClass(ExtraUserDataEntity.class);
                configuration.addAnnotatedClass(CarMarkEntity.class);
                configuration.addAnnotatedClass(CarModelEntity.class);
                configuration.addAnnotatedClass(OrderEntity.class);
                configuration.addAnnotatedClass(RoleEntity.class);
                configuration.addAnnotatedClass(UserEntity.class);


                /*ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                */
                sessionFactory = configuration.addProperties(settings).buildSessionFactory();
                //sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                if(sessionFactory==null) System.out.println("Session factory is NULL!");
                else System.out.println("Session factory is not NULL!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

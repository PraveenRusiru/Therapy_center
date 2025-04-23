package lk.ijse.therapy_center.Config;
import lk.ijse.therapy_center.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.geom.Area;

public class ConfigurationFactory {
    private static SessionFactory sessionFactory;
    private static ConfigurationFactory configurationFactory;

    private ConfigurationFactory() {
        try {

            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/therapy_center_database ?createDatabaseIfNotExist=true");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "12345678");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.setProperty("hibernate.show_sql", "true");


            configuration.addAnnotatedClass(Therapist.class);
//            configuration.addAnnotatedClass(Therapy_Session.class);
            configuration.addAnnotatedClass(Payment.class);
            configuration.addAnnotatedClass(Patient.class);
            configuration.addAnnotatedClass(Appointment.class);
            configuration.addAnnotatedClass(TherapySession.class);
            configuration.addAnnotatedClass(Therapy_Programme.class);
            configuration.addAnnotatedClass(Availabilty.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Patient_records.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static ConfigurationFactory getInstance() {
        if (configurationFactory == null) {
            configurationFactory = new ConfigurationFactory();
        }
        return configurationFactory;
    }

    public Session getSessionFactory() {
        return sessionFactory.openSession();
    }
}

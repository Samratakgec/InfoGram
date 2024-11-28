package org.example.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HiberUtil {
    public static Session getSession() {

        SessionFactory factory= new Configuration().configure("hibernate.config.xml").buildSessionFactory();
        return factory.openSession();
    }

}

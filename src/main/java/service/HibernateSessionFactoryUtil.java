package service;

import model.Good;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(HibernateSessionFactoryUtil.class);

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        logger.debug("Getting session factory");
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Good.class);
                configuration.addAnnotatedClass(Order.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                logger.error("Exception", e);
            }
        }
        return sessionFactory;
    }
}
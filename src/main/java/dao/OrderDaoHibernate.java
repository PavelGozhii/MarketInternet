package dao;

import model.Order;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.HibernateSessionFactoryUtil;

public class OrderDaoHibernate extends GenericDao<Order> {
    private static final Logger logger = Logger.getLogger(GoodDaoHibernate.class);

    @Override
    public boolean save(Order order) {
        logger.debug("Save");
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.saveOrUpdate(order);
            tx1.commit();
            logger.debug("Save success");
            return true;
        } catch (HibernateException e) {
            logger.warn(e);
            return false;
        }
    }
}

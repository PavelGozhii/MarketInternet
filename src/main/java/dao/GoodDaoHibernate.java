package dao;

import model.Good;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class GoodDaoHibernate extends GenericDao<Good> {

    private static final Logger logger = Logger.getLogger(GoodDaoHibernate.class);

    public List<Good> findByOwner(String owner) {
        String stringQuery = "FROM Good WHERE owner = :owner";
        List<Good> goods = new ArrayList<>();
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            logger.debug("Find by owner");
            Query query = session.createQuery(stringQuery);
            query.setParameter("owner", owner);
            goods = query.getResultList();
            return goods;
        } catch (HibernateException e) {
            logger.warn("Exception", e);
            return goods;
        }
    }

    public boolean deleteByOwner(String owner) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            logger.debug("Delete by Owner");
            Transaction transaction = session.beginTransaction();
            List<Good> goods = findByOwner(owner);
            for (int i = 0; i < goods.size(); i++) {
                delete(goods.get(i));
            }
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.warn("Exception", e);
            return false;
        }
    }
}

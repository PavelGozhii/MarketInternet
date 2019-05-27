package dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.HibernateSessionFactoryUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class GenericDao<T> implements GenericDaoInterface<T> {

    private static final Logger logger = Logger.getLogger(GenericDao.class);

    @Override
    public List<T> findAll(Class clazz) {
        logger.debug("Find all");
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
            Root<T> root = criteriaQuery.from(clazz);
            CriteriaQuery<T> all = criteriaQuery.select(root);
            TypedQuery<T> allQuery = session.createQuery(all);
            return allQuery.getResultList();
        } catch (HibernateException e) {
            logger.warn(e);
            return new ArrayList<>();
        }
    }

    @Override
    public T findById(Class clazz, String id) {
        logger.debug("Find by id");
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            return (T) session.get(clazz, id);
        } catch (HibernateException e) {
            logger.warn(e);
            return null;
        }
    }

    @Override
    public boolean save(T t) {
        logger.debug("Save");
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            logger.debug("Save success");
            return true;
        } catch (HibernateException e) {
            logger.warn(e);
            return false;
        }
    }

    @Override
    public boolean delete(T t) {
        logger.debug("Delete");
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.warn(e);
            return false;
        }
    }

    @Override
    public boolean update(T t) {
        logger.debug("Update");
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.warn(e);
            return false;
        }
    }

    @Override
    public boolean update(T t, String id) {
        logger.debug("Update");
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(id, t);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.warn(e);
            return false;
        }
    }
}

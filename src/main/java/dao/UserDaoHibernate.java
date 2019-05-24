package dao;

import model.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDaoHibernate {

    Logger logger = Logger.getLogger(UserDaoHibernate.class);

    public List<User> findAll(){
        logger.debug("Find all users");
        List<User> users = HibernateSessionFactoryUtil
                .getSessionFactory().openSession()
                .createQuery("FROM User").list();
        return users;
    }

    public User findById(String id){
        logger.debug("Find user by id");
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public boolean save(User user){
        try {
            logger.debug("Save user");
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            logger.warn("Exception", e);
            return false;
        }
    }

    public boolean update(User user, String id){
        try {
            logger.debug("Update user");
            String hql = "UPDATE User SET login = :login, email = :email, roleId = :roleId WHERE id = :id";
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction txl = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("login", user.getLogin());
            query.setParameter("email", user.getEmail());
            query.setParameter("roleId", user.getRoleId());
            query.setParameter("id", id);
            query.executeUpdate();
            txl.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            logger.warn("Exception", e);
            return false;
        }
    }

    public boolean delete(User user) {
        try {
            logger.debug("Delete user");
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(user);
            tx1.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            logger.warn("Exception", e);
            return false;
        }
    }
}

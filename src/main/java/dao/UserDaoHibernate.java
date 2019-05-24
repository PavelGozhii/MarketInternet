package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDaoHibernate {


    public List<User> findAll(){
        List<User> users = HibernateSessionFactoryUtil
                .getSessionFactory().openSession()
                .createQuery("FROM User").list();
        return users;
    }

    public User findById(String id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public boolean save(User user){
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    public boolean update(User user, String id){
        try {
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
        }catch (Exception e){
            return false;
        }
    }

    public boolean delete(User user) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(user);
            tx1.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }
}

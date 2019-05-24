package dao;

import model.Good;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class GoodDaoHibernate {

    private static final Logger logger = Logger.getLogger(GoodDaoHibernate.class);

    public List<Good> findAll() {
        List goods = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Good").list();
        return goods;
    }

    public List<Good> findByOwner(String owner){
        List<Good> goods = new ArrayList<>();
        try{
            String hq1 = "FROM Good WHERE owner = :owner";
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery(hq1);
            query.setParameter("owner", owner);
            goods = query.getResultList();
            return goods;
        }catch (HibernateException e) {
            return goods;
        }
    }

    public boolean saveGood(Good good) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction txl = session.beginTransaction();
            session.save(good);
            txl.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    public Good findById(String id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Good.class, id);
    }

    public boolean update(Good good, String id){
        try {
            String hq1 = "UPDATE Good SET id = :newId, owner = :owner, name = :name, price = :price, description = :description WHERE id = :id";
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction txl = session.beginTransaction();
            Query query = session.createQuery(hq1);
            query.setParameter("newId", good.getId());
            query.setParameter("owner", good.getOwner());
            query.setParameter("name", good.getName());
            query.setParameter("price", good.getPrice());
            query.setParameter("description", good.getDescription());
            query.setParameter("id", id);
            query.executeUpdate();
            txl.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    public boolean delete(Good good){
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction txl = session.beginTransaction();
            session.delete(good);
            txl.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    public boolean deleteByOwner(String owner){
        try {
            String hq1 = "DELETE FROM Good G WHERE G.owner = :owner";
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction txl = session.beginTransaction();
            Query query = session.createQuery(hq1);
            query.setParameter("owner", owner);
            List<Good> goods = query.list();
            for (int i = 0; i < goods.size(); i++) {
                delete(goods.get(i));
            }
            txl.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            logger.warn("Exception", e);
            return false;
        }
    }



}

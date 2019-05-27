package dao;

import model.User;
import org.apache.log4j.Logger;

public class UserDaoHibernate extends GenericDao<User> {
    Logger logger = Logger.getLogger(UserDaoHibernate.class);
}

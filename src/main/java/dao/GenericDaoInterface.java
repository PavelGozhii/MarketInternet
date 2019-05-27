package dao;

import java.util.List;

public interface GenericDaoInterface<T> {

    List<T> findAll(Class clazz);

    T findById(Class clazz, String id);

    boolean save(T t);

    boolean delete(T t);

    boolean update(T t);

    boolean update(T t, String id);
}

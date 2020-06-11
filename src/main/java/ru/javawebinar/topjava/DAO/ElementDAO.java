package ru.javawebinar.topjava.DAO;

import java.util.List;

public interface ElementDAO<E, K> {
    List<E> getAll();

    E getEntityById(K id);

    void  update(E entity);

    boolean delete(K id);

    boolean create(E entity);
}

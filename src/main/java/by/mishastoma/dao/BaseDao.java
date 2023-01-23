package by.mishastoma.dao;

import by.mishastoma.exception.DaoException;

import java.util.List;

public interface BaseDao<T> {


    void insert(T t) throws DaoException;

    void delete(T t) throws DaoException;

    List<T> findAll() throws DaoException;

    void update(T t) throws DaoException;
}

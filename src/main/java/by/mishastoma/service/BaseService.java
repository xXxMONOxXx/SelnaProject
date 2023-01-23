package by.mishastoma.service;

import by.mishastoma.exception.ServiceException;

import java.util.List;

public interface BaseService<M> {
    void insert(M m) throws ServiceException;

    void delete(M m) throws ServiceException;

    List<M> findAll() throws ServiceException;

    void update(M m) throws ServiceException;
}

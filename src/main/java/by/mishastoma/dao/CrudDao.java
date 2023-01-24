package by.mishastoma.dao;

import java.util.List;

public interface CrudDao<T> {


    void insert(T t);

    void delete(T t);

    List<T> findAll();

    void update(T t);
}

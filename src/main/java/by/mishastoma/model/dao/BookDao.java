package by.mishastoma.model.dao;

import by.mishastoma.model.entity.BookEntity;

import java.io.Serializable;

public interface BookDao {

    void save(BookEntity entity);

    BookEntity findById(Serializable id);

    void update(BookEntity t);

    void delete(BookEntity t);

    BookEntity findBookByIdJpql(Integer id);

    BookEntity findBookByIdEntityGraph(Integer id);

    BookEntity findBookByIdCriteria(Integer id);

    BookEntity findBookByIsbn(String isbn);

}

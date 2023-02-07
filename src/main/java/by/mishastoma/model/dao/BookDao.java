package by.mishastoma.model.dao;

import by.mishastoma.model.entity.AuthorEntity;
import by.mishastoma.model.entity.BookEntity;
import by.mishastoma.model.entity.GenreEntity;
import by.mishastoma.model.entity.GenreEntity_;

import java.io.Serializable;
import java.util.List;

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

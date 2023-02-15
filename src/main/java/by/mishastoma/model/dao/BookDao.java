package by.mishastoma.model.dao;

import by.mishastoma.model.entity.Book;

import java.io.Serializable;
import java.util.Optional;

public interface BookDao extends GenericDao<Book>{

    Optional<Book> findByIdJpql(Serializable id);

    Optional<Book> findByIdEntityGraph(Serializable id);

    Optional<Book> findByIdCriteria(Serializable id);

    Optional<Book> findByIsbn(String isbn);

}

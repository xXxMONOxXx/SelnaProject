package by.mishastoma.model.dao;

import by.mishastoma.model.entity.Author;
import by.mishastoma.model.entity.Book;
import by.mishastoma.model.entity.Genre;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookDao extends GenericDao<Book> {

    Optional<Book> findByIsbn(String isbn);

    Page<Book> findByGenres(List<Genre> genres, int pageNumber, int pageSize);

    Page<Book> findByAuthors(List<Author> authors, int pageNumber, int pageSize);

    Page<Book> findByTitle(String title, int pageNumber, int pageSize);

    Page<Book> fullSearch(List<Author> authors, List<Genre> genres, int pageNumber, int pageSize);
}

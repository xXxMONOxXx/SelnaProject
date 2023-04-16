package by.mishastoma.model.dao;


import by.mishastoma.model.entity.Author;
import org.springframework.data.domain.Page;

public interface AuthorDao extends GenericDao<Author> {
    Page<Author> search(Author author, int pageNumber, int pageSize);
}

package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.entity.Author;
import by.mishastoma.model.entity.Book;
import by.mishastoma.model.entity.Book_;
import by.mishastoma.model.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
@Repository
public class BookDaoImpl extends AbstractDao<Book> implements BookDao {

    private BookDaoImpl(EntityManager entityManager) {
        super(entityManager, Book.class);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Book_.ISBN), isbn));
            return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<Book> findByGenres(List<Genre> genres, int pageNumber, int pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        Join<Book, Genre> genreJoin = root.join(Book_.GENRES);
        Predicate genrePredicate = genreJoin.in(genres);
        query.where(genrePredicate);
        TypedQuery<Book> typedQuery = entityManager.createQuery(query);
        return PageableExecutionUtils.getPage(typedQuery.getResultList(), PageRequest.of(pageNumber - 1, pageSize),
                this::countEntities);
    }

    @Override
    public Page<Book> findByAuthors(List<Author> authors, int pageNumber, int pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        Join<Book, Author> authorJoin = root.join(Book_.AUTHORS);
        Predicate authorPredicate = authorJoin.in(authors);
        query.where(authorPredicate);
        TypedQuery<Book> typedQuery = entityManager.createQuery(query);
        return PageableExecutionUtils.getPage(typedQuery.getResultList(), PageRequest.of(pageNumber - 1, pageSize),
                this::countEntities);
    }

    @Override
    public Page<Book> findByTitle(String title, int pageNumber, int pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        Predicate titlePredicate = builder.like(root.get(Book_.TITLE), String.format("%s%s", title, "%"));
        query.where(titlePredicate);
        TypedQuery<Book> typedQuery = entityManager.createQuery(query);
        return PageableExecutionUtils.getPage(typedQuery.getResultList(), PageRequest.of(pageNumber - 1, pageSize),
                this::countEntities);
    }

    @Override
    public Page<Book> fullSearch(List<Author> authors, List<Genre> genres, int pageNumber, int pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        Join<Book, Author> authorJoin = root.join(Book_.AUTHORS);
        Join<Book, Genre> genreJoin = root.join(Book_.GENRES);
        Predicate authorsPredicate = authorJoin.in(authors);
        Predicate genrePredicate = genreJoin.in(genres);
        Predicate finalPredicate = builder.and(authorsPredicate, genrePredicate);
        query.where(finalPredicate);
        TypedQuery<Book> typedQuery = entityManager.createQuery(query);
        return PageableExecutionUtils.getPage(typedQuery.getResultList(), PageRequest.of(pageNumber - 1, pageSize),
                this::countEntities);
    }
}

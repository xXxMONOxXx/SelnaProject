package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.entity.Book;
import by.mishastoma.model.entity.Book_;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Repository
public class BookDaoImpl extends AbstractDao<Book> implements BookDao {

    private BookDaoImpl(EntityManager entityManager) {
        super(entityManager, Book.class);
    }

    @Override
    public Optional<Book> findByIdJpql(Serializable id) {
        Query query = entityManager.createQuery("select b from Book b join b.authors a join b.genres g join b.users i where b.id = :id");
        query.setParameter(Book_.ID, id);
        return Optional.ofNullable((Book) query.getSingleResult());
    }

    @Override
    public Optional<Book> findByIdEntityGraph(Serializable id) {
        EntityGraph<?> graph = entityManager.getEntityGraph("graph.Book.associations");
        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);
        return Optional.ofNullable(entityManager.find(Book.class, id, hints));
    }

    @Override
    public Optional<Book> findByIdCriteria(Serializable id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        root.fetch(Book_.GENRES, JoinType.INNER);
        root.fetch(Book_.AUTHORS, JoinType.INNER);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Book_.ID), id));
        return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
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
}

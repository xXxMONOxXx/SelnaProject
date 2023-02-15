package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.entity.Book;
import by.mishastoma.model.entity.Book_;
import org.springframework.stereotype.Component;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
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
public class BookDaoImpl extends AbstractDao<Book> implements BookDao {

    private BookDaoImpl(EntityManager entityManager) {
        super(entityManager, Book.class);
    }

    @Override
    public Optional<Book> findByIdJpql(Serializable id) {
        Query query = entityManager.createQuery("select b from Book b join b.authors a join b.genres g join b.users i where b.id = :id");
        query.setParameter("id", id);
        return Optional.ofNullable((Book) query.getSingleResult());
    }

    @Override
    public Optional<Book> findByIdEntityGraph(Serializable id) {
        EntityGraph graph = entityManager.getEntityGraph("graph.Book.assosiations");
        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);
        return Optional.ofNullable(entityManager.find(Book.class, id, hints));
    }

    @Override
    public Optional<Book> findByIdCriteria(Serializable id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(Book.class);
        Root o = q.from(Book.class);
        o.fetch("users", JoinType.INNER);
        o.fetch("genres", JoinType.INNER);
        o.fetch("authors", JoinType.INNER);
        q.select(o).where(cb.equal(o.get("id"), id));
        return Optional.ofNullable((Book) entityManager.createQuery(q).getSingleResult());
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery.select(root).where(cb.equal(root.get(Book_.isbn), isbn));
        return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
    }
}

package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.entity.BookEntity;
import by.mishastoma.model.entity.BookEntity_;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;

@Component
public class BookDaoImpl extends AbstractDao<BookEntity> implements BookDao {

    private BookDaoImpl(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        super(localContainerEntityManagerFactoryBean, BookEntity.class);
    }

    @Override
    public BookEntity findBookByIdJpql(Integer id) {
        EntityManager entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
        Query query = entityManager.createQuery("select b from BookEntity b join b.authors a join b.genres g join b.items i where b.id = :id");
        query.setParameter("id", id);
        return (BookEntity) query.getSingleResult();
    }

    @Override
    public BookEntity findBookByIdEntityGraph(Integer id) {
        EntityManager entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
        EntityGraph graph = entityManager.getEntityGraph("graph.Book.assosiations");
        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);
        return entityManager.find(BookEntity.class, id, hints);
    }

    @Override
    public BookEntity findBookByIdCriteria(Integer id) {
        EntityManager entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(BookEntity.class);
        Root o = q.from(BookEntity.class);
        o.fetch("items", JoinType.INNER);
        o.fetch("genres", JoinType.INNER);
        o.fetch("authors", JoinType.INNER);
        q.select(o).where(cb.equal(o.get("id"), id));
        return (BookEntity) entityManager.createQuery(q).getSingleResult();
    }

    @Override
    public BookEntity findBookByIsbn(String isbn) {
        EntityManager entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookEntity> criteriaQuery = cb.createQuery(BookEntity.class);
        Root<BookEntity> root = criteriaQuery.from(BookEntity.class);
        root.fetch("items", JoinType.INNER);
        root.fetch("genres", JoinType.INNER);
        root.fetch("authors", JoinType.INNER);
        criteriaQuery.select(root).where(cb.equal(root.get(BookEntity_.isbn), isbn));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}

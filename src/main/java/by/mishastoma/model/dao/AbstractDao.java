package by.mishastoma.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Optional;

public abstract class AbstractDao<T> {
    @PersistenceContext
    protected final EntityManager entityManager;
    private final Class<T> type;

    protected AbstractDao(EntityManager entityManager, Class<T> type) {
        this.type = type;
        this.entityManager = entityManager;
    }

    public T save(T t) {
        return entityManager.merge(t);
    }

    public Optional<T> findById(Serializable id) {
        T t = null;
        t = entityManager.find(type, id);
        return Optional.ofNullable(t);
    }

    public void update(T t) {
        entityManager.merge(t);
    }

    public void delete(T t) {
        entityManager.remove(t);
    }

    public Page<T> getAll(int pageNumber, int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);
        cq.select(root);
        TypedQuery<T> query = entityManager.createQuery(cq);
        return PageableExecutionUtils.getPage(query.getResultList(), PageRequest.of(pageNumber - 1, pageSize),
                this::countEntities);
    }

    private Long countEntities() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(type);
        cq.select(cb.count(root));
        return entityManager.createQuery(cq).getSingleResult();
    }

}

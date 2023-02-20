package by.mishastoma.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public void save(T t) {
        entityManager.persist(t);
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
}

package by.mishastoma.model.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Optional;

public abstract class AbstractDao<T> {
    protected final EntityManager entityManager;
    private final Class<T> type;

    protected AbstractDao(EntityManager entityManager, Class<T> type) {
        this.type = type;
        this.entityManager = entityManager;
    }

    public void save(T t) {
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public Optional<T> findById(Serializable id) {
        T t = null;
        t = entityManager.find(type, id);
        return Optional.ofNullable(t);
    }

    public void update(T t) {
        entityManager.getTransaction().begin();
        entityManager.merge(t);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public void delete(T t) {
        //entityManager.getTransaction().begin();
        //entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
        entityManager.remove(t);
        //entityManager.flush();
        //entityManager.getTransaction().commit();
    }

}

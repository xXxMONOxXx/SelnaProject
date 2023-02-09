package by.mishastoma.model.dao;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

public abstract class AbstractDao<T> {
    protected final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;
    private final Class<T> type;

    protected AbstractDao(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean, Class<T> type) {
        this.type = type;
        this.localContainerEntityManagerFactoryBean = localContainerEntityManagerFactoryBean;
    }

    public void save(T t) {
        EntityManager entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public T findById(Serializable id) {
        EntityManager entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
        T t = entityManager.find(type, id);
        return t;
    }

    public void update(T t) {
        EntityManager entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(t);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public void delete(T t) {
        EntityManager entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

}

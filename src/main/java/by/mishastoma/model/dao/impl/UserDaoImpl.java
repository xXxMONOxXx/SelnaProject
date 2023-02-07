package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.entity.*;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.List;

@Component
public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {

    private UserDaoImpl(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        super(localContainerEntityManagerFactoryBean, UserEntity.class);
    }

    @Override
    public UserEntity findByIdCriteria(Integer id) {
        EntityManager entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(UserEntity.class);
        Root o = q.from(UserEntity.class);
        o.fetch("items", JoinType.INNER);
        o.fetch("role", JoinType.INNER);
        o.fetch("profile", JoinType.INNER);
        q.select(o);
        q.where(cb.equal(o.get("id"), id));
        return (UserEntity) entityManager.createQuery(q).getSingleResult();
    }

    @Override
    public List<UserEntity> findUsersByRole(RoleEntity role) {
        EntityManager entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        criteriaQuery.select(root).where(cb.equal(root.get(UserEntity_.ROLE), role));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        EntityManager entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        root.fetch("items", JoinType.INNER);
        root.fetch("role", JoinType.INNER);
        root.fetch("profile", JoinType.INNER);
        criteriaQuery.select(root).where(cb.equal(root.get(UserEntity_.USERNAME), username));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}

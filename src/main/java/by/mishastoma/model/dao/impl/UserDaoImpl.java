package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.entity.Role;
import by.mishastoma.model.entity.User;
import by.mishastoma.model.entity.User_;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private UserDaoImpl(EntityManager entityManager) {
        super(entityManager, User.class);
    }

    @Override
    public Optional<User> findByIdCriteria(Serializable id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        root.fetch(User_.ROLE, JoinType.INNER);
        root.fetch(User_.PROFILE, JoinType.INNER);
        criteriaQuery.select(root).where(cb.equal(root.get(User_.ID), id));
        return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
    }

    @Override
    public List<User> findByRole(Role role) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        root.fetch(User_.ROLE, JoinType.INNER);
        root.fetch(User_.PROFILE, JoinType.INNER);
        criteriaQuery.select(root).where(cb.equal(root.get(User_.ROLE), role));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        root.fetch(User_.ROLE, JoinType.INNER);
        root.fetch(User_.PROFILE, JoinType.INNER);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(User_.USERNAME), username));
        return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
    }

}

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
        CriteriaQuery q = cb.createQuery(User.class);
        Root o = q.from(User.class);
        q.select(o);
        q.where(cb.equal(o.get("id"), id));
        return Optional.ofNullable((User) entityManager.createQuery(q).getSingleResult());
    }

    @Override
    public List<User> findByRole(Role role) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        root.fetch("role", JoinType.INNER);
        root.fetch("profile", JoinType.INNER);
        criteriaQuery.select(root).where(cb.equal(root.get(User_.ROLE), role));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        root.fetch("role", JoinType.INNER);
        root.fetch("profile", JoinType.INNER);
        criteriaQuery.select(root).where(cb.equal(root.get(User_.USERNAME), username));
        return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
    }
}

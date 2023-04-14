package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.entity.Item;
import by.mishastoma.model.entity.Item_;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Component
@Repository
public class ItemDaoImpl extends AbstractDao<Item> implements ItemDao {

    private ItemDaoImpl(EntityManager entityManager) {
        super(entityManager, Item.class);
    }

    @Override
    public Optional<Item> getFreeItemForBook(Long bookId) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
            Root<Item> root = criteriaQuery.from(Item.class);
            Predicate bookIdPredicate = criteriaBuilder.equal(root.get(Item_.BOOK_ID), bookId);
            Predicate statusPredicate = criteriaBuilder.isNull(root.get(Item_.USER_ID));
            criteriaQuery.select(root).where(criteriaBuilder.and(bookIdPredicate, statusPredicate));
            TypedQuery<Item> query = entityManager.createQuery(criteriaQuery);
            query.setMaxResults(1);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Long countUsersItems(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Item> root = criteriaQuery.from(Item.class);
        criteriaQuery.select(criteriaBuilder.count(root)).where(criteriaBuilder.equal(root.get(Item_.USER_ID), id));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}

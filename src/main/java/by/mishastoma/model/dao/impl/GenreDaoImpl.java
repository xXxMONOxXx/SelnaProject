package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.entity.Genre;
import by.mishastoma.model.entity.Genre_;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Component
@Repository
public class GenreDaoImpl extends AbstractDao<Genre> implements GenreDao {

    private GenreDaoImpl(EntityManager entityManager) {
        super(entityManager, Genre.class);
    }

    @Override
    public Optional<Genre> findByName(String name) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Genre> criteriaQuery = criteriaBuilder.createQuery(Genre.class);
            Root<Genre> root = criteriaQuery.from(Genre.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Genre_.NAME), name));
            return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}

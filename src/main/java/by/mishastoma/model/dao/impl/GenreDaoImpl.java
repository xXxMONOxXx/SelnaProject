package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.entity.GenreEntity;
import by.mishastoma.model.entity.GenreEntity_;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Component
public class GenreDaoImpl extends AbstractDao<GenreEntity> implements GenreDao {

    private GenreDaoImpl(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        super(localContainerEntityManagerFactoryBean, GenreEntity.class);
    }

    @Override
    public GenreEntity findByGenreName(String genre) {
        EntityManager entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GenreEntity> criteriaQuery = cb.createQuery(GenreEntity.class);
        Root<GenreEntity> root = criteriaQuery.from(GenreEntity.class);
        criteriaQuery.select(root).where(cb.equal(root.get(GenreEntity_.GENRE), genre));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}

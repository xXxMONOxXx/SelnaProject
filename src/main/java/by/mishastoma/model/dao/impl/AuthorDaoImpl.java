package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.entity.Author;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Component
@Repository
public class AuthorDaoImpl extends AbstractDao<Author> implements AuthorDao {
    private AuthorDaoImpl(EntityManager entityManager) {
        super(entityManager, Author.class);
    }
}

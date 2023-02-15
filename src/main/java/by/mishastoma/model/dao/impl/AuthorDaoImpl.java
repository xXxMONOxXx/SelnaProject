package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.entity.Author;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AuthorDaoImpl extends AbstractDao<Author> implements AuthorDao {
    private AuthorDaoImpl(EntityManager entityManager) {
        super(entityManager, Author.class);
    }
}

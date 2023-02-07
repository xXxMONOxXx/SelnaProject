package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.entity.AuthorEntity;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl extends AbstractDao<AuthorEntity> implements AuthorDao {
    private AuthorDaoImpl(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        super(localContainerEntityManagerFactoryBean, AuthorEntity.class);
    }
}

package by.mishastoma.model.dao;


import by.mishastoma.model.entity.AuthorEntity;

import java.io.Serializable;

public interface AuthorDao {

    void save(AuthorEntity entity);

    AuthorEntity findById(Serializable id);

    void update(AuthorEntity t);

    void delete(AuthorEntity t);

}

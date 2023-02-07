package by.mishastoma.model.dao;

import by.mishastoma.model.entity.GenreEntity;

import java.io.Serializable;

public interface GenreDao {
    void save(GenreEntity entity);

    GenreEntity findById(Serializable id);

    void update(GenreEntity t);

    void delete(GenreEntity t);

    GenreEntity findByGenreName(String genre);
}

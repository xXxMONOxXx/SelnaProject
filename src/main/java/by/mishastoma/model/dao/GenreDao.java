package by.mishastoma.model.dao;

import by.mishastoma.model.entity.Genre;

import java.util.Optional;

public interface GenreDao extends GenericDao<Genre> {

    Optional<Genre> findByName(String genre);
}

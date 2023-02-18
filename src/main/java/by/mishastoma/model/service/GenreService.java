package by.mishastoma.model.service;

import by.mishastoma.model.dto.GenreDto;

public interface GenreService extends CrudService<GenreDto> {
    GenreDto findGenreByName(String name);
}

package by.mishastoma.service;

import by.mishastoma.web.dto.GenreDto;

public interface GenreService extends CrudService<GenreDto> {
    GenreDto findGenreByName(String name);
}

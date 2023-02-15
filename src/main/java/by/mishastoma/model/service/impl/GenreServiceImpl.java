package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.dto.GenreDto;
import by.mishastoma.model.entity.Genre;
import by.mishastoma.model.service.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;

@Component
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;
    private final ModelMapper modelMapper;

    private GenreServiceImpl(GenreDao dao, ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void insert(GenreDto genreDto) {
        Genre genre = modelMapper.map(genreDto, Genre.class);
        dao.save(genre);
    }

    @Override
    public void delete(GenreDto genreDto) {
        Genre genre = modelMapper.map(genreDto, Genre.class);
        dao.delete(genre);
    }

    @Override
    public GenreDto findById(Serializable id) {
        Genre genreEntity = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find genre with id " + id));
        return modelMapper.map(genreEntity, GenreDto.class);
    }

    @Override
    public void update(GenreDto genreDto) {
        Genre genre = modelMapper.map(genreDto, Genre.class);
        dao.update(genre);
    }

    @Override
    public GenreDto findGenreByName(String genre) {
        Genre genreEntity = dao.findByName(genre).orElseThrow(() -> new EntityNotFoundException("Can't find genre with name " + genre));
        return modelMapper.map(genreEntity, GenreDto.class);
    }
}

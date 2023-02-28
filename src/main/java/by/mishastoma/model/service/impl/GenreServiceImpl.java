package by.mishastoma.model.service.impl;

import by.mishastoma.exception.GenreNotFoundException;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.dto.GenreDto;
import by.mishastoma.model.entity.Genre;
import by.mishastoma.model.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final ModelMapper modelMapper;

    @Override
    public void insert(GenreDto genreDto) {
        Genre genre = modelMapper.map(genreDto, Genre.class);
        genreDao.save(genre);
    }

    @Transactional
    @Override
    public void delete(Serializable id) {
        Genre genre = genreDao.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
        genreDao.delete(genre);
    }

    @Override
    public GenreDto findById(Serializable id) {
        Genre genreEntity = genreDao.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
        return modelMapper.map(genreEntity, GenreDto.class);
    }

    @Override
    public void update(GenreDto genreDto) {
        Genre genre = modelMapper.map(genreDto, Genre.class);
        genreDao.update(genre);
    }

    @Override
    public GenreDto findGenreByName(String name) {
        Genre genreEntity = genreDao.findByName(name).orElseThrow(() -> new GenreNotFoundException(name));
        return modelMapper.map(genreEntity, GenreDto.class);
    }
}

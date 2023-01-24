package by.mishastoma.service.impl;

import by.mishastoma.dao.GenreDao;
import by.mishastoma.dto.DTOGenre;
import by.mishastoma.entity.Genre;
import by.mishastoma.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao dao;
    private final ModelMapper modelMapper;

    @Override
    public void insert(DTOGenre dtoGenre) {
        Genre genre = modelMapper.map(dtoGenre, Genre.class);
        dao.insert(genre);
    }

    @Override
    public void delete(DTOGenre dtoGenre) {
        Genre genre = modelMapper.map(dtoGenre, Genre.class);
        dao.delete(genre);
    }

    @Override
    public List<DTOGenre> findAll() {
        List<Genre> genres = dao.findAll();
        return genres.stream().map(x -> modelMapper.map(x, DTOGenre.class)).toList();
    }

    @Override
    public void update(DTOGenre dtoGenre) {
        Genre genre = modelMapper.map(dtoGenre, Genre.class);
        dao.update(genre);
    }
}

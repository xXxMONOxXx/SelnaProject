package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.dto.DTOGenre;
import by.mishastoma.model.entity.Genre;
import by.mishastoma.model.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao dao;
    private final ModelMapper modelMapper;

    @Override
    public void insert(DTOGenre dtoGenre) {
        try {
            Genre genre = modelMapper.map(dtoGenre, Genre.class);
            dao.insert(genre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(DTOGenre dtoGenre) {
        try {
            Genre genre = modelMapper.map(dtoGenre, Genre.class);
            dao.delete(genre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DTOGenre> findAll() {
        try {
            List<Genre> genres = dao.findAll();
            return genres.stream().map(x -> modelMapper.map(x, DTOGenre.class)).toList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(DTOGenre dtoGenre) {
        try {
            Genre genre = modelMapper.map(dtoGenre, Genre.class);
            dao.update(genre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

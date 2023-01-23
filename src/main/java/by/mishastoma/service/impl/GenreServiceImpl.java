package by.mishastoma.service.impl;

import by.mishastoma.dao.GenreDao;
import by.mishastoma.dto.DTOGenre;
import by.mishastoma.entity.Genre;
import by.mishastoma.exception.DaoException;
import by.mishastoma.exception.ServiceException;
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
    public void insert(DTOGenre dtoGenre) throws ServiceException {
        Genre genre = modelMapper.map(dtoGenre, Genre.class);
        try {
            dao.insert(genre);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(DTOGenre dtoGenre) throws ServiceException {
        Genre genre = modelMapper.map(dtoGenre, Genre.class);
        try {
            dao.delete(genre);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<DTOGenre> findAll() throws ServiceException {
        try {
            List<Genre> genres = dao.findAll();
            return genres.stream().map(x -> modelMapper.map(x, DTOGenre.class)).toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(DTOGenre dtoGenre) throws ServiceException {
        Genre genre = modelMapper.map(dtoGenre, Genre.class);
        try {
            dao.update(genre);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

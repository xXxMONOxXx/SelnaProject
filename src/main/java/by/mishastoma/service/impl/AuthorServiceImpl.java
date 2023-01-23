package by.mishastoma.service.impl;

import by.mishastoma.dao.AuthorDao;
import by.mishastoma.dto.DTOAuthor;
import by.mishastoma.entity.Author;
import by.mishastoma.exception.DaoException;
import by.mishastoma.exception.ServiceException;
import by.mishastoma.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;
    private final ModelMapper modelMapper;

    @Override
    public void insert(DTOAuthor dtoAuthor) throws ServiceException {
        Author author = modelMapper.map(dtoAuthor, Author.class);
        try {
            dao.insert(author);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(DTOAuthor dtoAuthor) throws ServiceException {
        Author author = modelMapper.map(dtoAuthor, Author.class);
        try {
            dao.delete(author);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<DTOAuthor> findAll() throws ServiceException {
        try {
            List<Author> authors = dao.findAll();
            return authors.stream().map(x -> modelMapper.map(x, DTOAuthor.class)).toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(DTOAuthor dtoAuthor) throws ServiceException {
        Author author = modelMapper.map(dtoAuthor, Author.class);
        try {
            dao.update(author);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

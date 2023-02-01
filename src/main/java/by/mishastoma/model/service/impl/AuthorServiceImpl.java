package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dto.DTOAuthor;
import by.mishastoma.model.entity.Author;
import by.mishastoma.model.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;


@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;
    private final ModelMapper modelMapper;

    @Override
    public void insert(DTOAuthor dtoAuthor) {
        Author author = modelMapper.map(dtoAuthor, Author.class);
        try {
            dao.insert(author);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(DTOAuthor dtoAuthor) {
        try {
            Author author = modelMapper.map(dtoAuthor, Author.class);

            dao.delete(author);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DTOAuthor> findAll() {
        try {
            List<Author> authors = dao.findAll();
            return authors.stream().map(x -> modelMapper.map(x, DTOAuthor.class)).toList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(DTOAuthor dtoAuthor) {
        Author author = modelMapper.map(dtoAuthor, Author.class);
        try {
            dao.update(author);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

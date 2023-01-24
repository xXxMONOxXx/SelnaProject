package by.mishastoma.service.impl;

import by.mishastoma.dao.AuthorDao;
import by.mishastoma.dto.DTOAuthor;
import by.mishastoma.entity.Author;
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
    public void insert(DTOAuthor dtoAuthor) {
        Author author = modelMapper.map(dtoAuthor, Author.class);
        dao.insert(author);
    }

    @Override
    public void delete(DTOAuthor dtoAuthor) {
        Author author = modelMapper.map(dtoAuthor, Author.class);
        dao.delete(author);
    }

    @Override
    public List<DTOAuthor> findAll() {
        List<Author> authors = dao.findAll();
        return authors.stream().map(x -> modelMapper.map(x, DTOAuthor.class)).toList();
    }

    @Override
    public void update(DTOAuthor dtoAuthor) {
        Author author = modelMapper.map(dtoAuthor, Author.class);
        dao.update(author);
    }
}

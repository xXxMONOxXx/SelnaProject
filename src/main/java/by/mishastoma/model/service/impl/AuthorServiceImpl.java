package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dto.AuthorDto;
import by.mishastoma.model.entity.Author;
import by.mishastoma.model.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;


@Component
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    private final ModelMapper modelMapper;

    private AuthorServiceImpl(AuthorDao dao, ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void insert(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        dao.save(author);
    }

    @Transactional
    @Override
    public void delete(Serializable id) {
        Author author = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find author with id " + id));
        dao.delete(author);
    }

    @Override
    public AuthorDto findById(Serializable id) {
        Author authorEntity = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find author with id " + id));
        ;
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

    @Override
    public void update(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        dao.update(author);
    }
}

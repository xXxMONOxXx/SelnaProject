package by.mishastoma.model.service.impl;

import by.mishastoma.exception.AuthorNotFoundException;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dto.AuthorDto;
import by.mishastoma.model.entity.Author;
import by.mishastoma.model.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service
@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    private final ModelMapper modelMapper;

    @Override
    public void insert(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorDao.save(author);
    }

    @Transactional
    @Override
    public void delete(Serializable id) {
        Author author = authorDao.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        authorDao.delete(author);
    }

    @Override
    public AuthorDto findById(Serializable id) {
        Author authorEntity = authorDao.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

    @Override
    public void update(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorDao.update(author);
    }
}

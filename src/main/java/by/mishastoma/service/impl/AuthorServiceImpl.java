package by.mishastoma.service.impl;

import by.mishastoma.exception.AuthorNotFoundException;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.entity.Author;
import by.mishastoma.service.AuthorService;
import by.mishastoma.web.dto.AuthorDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public AuthorDto save(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        return modelMapper.map(authorDao.save(author), AuthorDto.class);
    }

    @Override
    @Transactional
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
    @Transactional
    public void update(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorDao.update(author);
    }

    @Override
    @Transactional
    public Page<AuthorDto> getAll(int pageNumber, int pageSize) {
        Page<Author> authors = authorDao.getAll(pageNumber, pageSize);
        return authors.map(mappingContext -> modelMapper.map(mappingContext, AuthorDto.class));
    }

    @Override
    @Transactional
    public Page<AuthorDto> search(AuthorDto authorDto, int pageNumber, int pageSize) {
        Page<Author> authors = authorDao.search(modelMapper.map(authorDto, Author.class), pageNumber, pageSize);
        return authors.map(mappingContext -> modelMapper.map(mappingContext, AuthorDto.class));
    }
}

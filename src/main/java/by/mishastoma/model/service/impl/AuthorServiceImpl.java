package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dto.DTOAuthor;
import by.mishastoma.model.entity.AuthorEntity;
import by.mishastoma.model.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    private final ModelMapper modelMapper;

    private AuthorServiceImpl(AuthorDao dao, ModelMapper modelMapper){
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void insert(DTOAuthor dtoAuthor) {
        AuthorEntity author = modelMapper.map(dtoAuthor, AuthorEntity.class);
        dao.save(author);
    }

    @Override
    public void delete(DTOAuthor dtoAuthor) {
        AuthorEntity author = modelMapper.map(dtoAuthor, AuthorEntity.class);
        dao.delete(author);
    }

    @Override
    public DTOAuthor findById(int id) {
        return modelMapper.map(dao.findById(id), DTOAuthor.class);
    }

    @Override
    public void update(DTOAuthor dtoAuthor) {
        AuthorEntity author = modelMapper.map(dtoAuthor, AuthorEntity.class);
        dao.update(author);
    }
}

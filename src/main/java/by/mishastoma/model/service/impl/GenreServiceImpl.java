package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.dto.DTOGenre;
import by.mishastoma.model.entity.GenreEntity;
import by.mishastoma.model.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;
    private final ModelMapper modelMapper;

    private GenreServiceImpl(GenreDao dao, ModelMapper modelMapper){
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void insert(DTOGenre dtoGenre) {
        GenreEntity genre = modelMapper.map(dtoGenre, GenreEntity.class);
        dao.save(genre);
    }

    @Override
    public void delete(DTOGenre dtoGenre) {
        GenreEntity genre = modelMapper.map(dtoGenre, GenreEntity.class);
        dao.delete(genre);
    }

    @Override
    public DTOGenre findById(int id) {
        return modelMapper.map(dao.findById(id), DTOGenre.class);
    }

    @Override
    public void update(DTOGenre dtoGenre) {
        GenreEntity genre = modelMapper.map(dtoGenre, GenreEntity.class);
        dao.update(genre);
    }

    @Override
    public DTOGenre findGenreByName(String genre) {
        return modelMapper.map(dao.findByGenreName(genre), DTOGenre.class);
    }
}

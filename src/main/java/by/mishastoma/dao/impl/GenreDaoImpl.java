package by.mishastoma.dao.impl;

import by.mishastoma.dao.GenreDao;
import by.mishastoma.entity.Genre;
import by.mishastoma.exception.DaoException;
import by.mishastoma.util.DaoUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreDaoImpl implements GenreDao {

    protected List<Genre> list = new ArrayList<>();

    private GenreDaoImpl() {
        list.add(Genre.builder()
                .id(1)
                .genre("Fantasy")
                .build());
        list.add(Genre.builder()
                .id(2)
                .genre("Sci-Fi")
                .build());
    }

    @Override
    public void insert(Genre t) throws DaoException {
        if (list.contains(t)) {
            throw new DaoException("Can't add entity to dao, entity already exists " + t.toString());
        }
        list.add(t);
    }

    @Override
    public void delete(Genre t) throws DaoException {
        int index = DaoUtils.getIndexOfEntity(t, list);
        if (index == -1) {
            throw new DaoException("Can't delete entity from dao, entity doesn't exist " + t.toString());
        }
        list.remove(index);
    }

    @Override
    public List<Genre> findAll() throws DaoException {
        return new ArrayList<>(list);
    }

    @Override
    public void update(Genre t) throws DaoException {
        int index = DaoUtils.getIndexOfEntity(t, list);
        if (index == -1) {
            throw new DaoException("Can't update entity, dao doesn't contain this entity " + t.toString());
        }
        list.set(index, t);
    }
}

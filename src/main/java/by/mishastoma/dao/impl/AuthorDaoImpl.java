package by.mishastoma.dao.impl;

import by.mishastoma.dao.AuthorDao;
import by.mishastoma.entity.Author;
import by.mishastoma.util.DaoUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    protected List<Author> list = new ArrayList<>();

    private AuthorDaoImpl() {
        list.add(Author.builder()
                .id(1L)
                .firstname("William")
                .surname("Shakespeare")
                .build());
        list.add(Author.builder()
                .id(2L)
                .firstname("Charles")
                .surname("Dickens")
                .build());
    }

    @Override
    public void insert(Author t) {
        if (list.contains(t)) {
            throw new RuntimeException("Can't add entity to dao, entity already exists " + t.toString());
        }
        list.add(t);
    }

    @Override
    public void delete(Author t) {
        int index = DaoUtils.getIndexOfEntity(t, list);
        if (index == -1) {
            throw new RuntimeException("Can't delete entity from dao, entity doesn't exist " + t.toString());
        }
        list.remove(index);
    }

    @Override
    public List<Author> findAll() {
        return new ArrayList<>(list);
    }

    @Override
    public void update(Author t) {
        int index = DaoUtils.getIndexOfEntity(t, list);
        if (index == -1) {
            throw new RuntimeException("Can't update entity, dao doesn't contain this entity " + t.toString());
        }
        list.set(index, t);
    }
}

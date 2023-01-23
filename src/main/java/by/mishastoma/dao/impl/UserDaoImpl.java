package by.mishastoma.dao.impl;

import by.mishastoma.dao.UserDao;
import by.mishastoma.entity.Role;
import by.mishastoma.entity.User;
import by.mishastoma.exception.DaoException;
import by.mishastoma.util.DaoUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class UserDaoImpl implements UserDao {

    protected List<User> list = new ArrayList<>();

    private UserDaoImpl() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate dateMock1 = LocalDate.parse("2000-09-16", formatter);
        LocalDate dateMock2 = LocalDate.parse("1998-02-13", formatter);
        list.add(User.builder()
                .id(1)
                .username("addy")
                .password("6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b")
                .role(Role.CUSTOMER)
                .firstname("Addy")
                .surname("Lamb")
                .phone("375332949604")
                .email("rogahn.charlie@franecki.com")
                .birthdate(dateMock1)
                .isBlocked(false)
                .itemIds(new ArrayList<>())
                .build());
        list.add(User.builder()
                .id(2)
                .username("max")
                .password("d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35")
                .role(Role.CUSTOMER)
                .firstname("Max")
                .surname("Evans")
                .phone("375446926083")
                .email("terry.maye@stracke.com")
                .birthdate(dateMock2)
                .isBlocked(false)
                .itemIds(new ArrayList<>())
                .build());
    }

    @Override
    public void insert(User t) throws DaoException {
        if (list.contains(t)) {
            throw new DaoException("Can't add entity to dao, entity already exists " + t.toString());
        }
        list.add(t);
    }

    @Override
    public void delete(User t) throws DaoException {
        int index = DaoUtils.getIndexOfEntity(t, list);
        if (index == -1) {
            throw new DaoException("Can't delete entity from dao, entity doesn't exist " + t.toString());
        }
        list.remove(index);
    }

    @Override
    public List<User> findAll() throws DaoException {
        return new ArrayList<>(list);
    }

    @Override
    public void update(User t) throws DaoException {
        int index = DaoUtils.getIndexOfEntity(t, list);
        if (index == -1) {
            throw new DaoException("Can't update entity, dao doesn't contain this entity " + t.toString());
        }
        list.set(index, t);
    }
}

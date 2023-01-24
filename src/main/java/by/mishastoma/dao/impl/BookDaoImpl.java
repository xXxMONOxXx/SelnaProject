package by.mishastoma.dao.impl;

import by.mishastoma.dao.BookDao;
import by.mishastoma.entity.Book;
import by.mishastoma.util.DaoUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class BookDaoImpl implements BookDao {

    protected List<Book> list = new ArrayList<>();

    private BookDaoImpl() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate dateMock1 = LocalDate.parse("2020-01-01", formatter);
        LocalDate dateMock2 = LocalDate.parse("2020-01-01", formatter);
        list.add(Book.builder()
                .id(1L)
                .title("Romeo and Juliet")
                .isbn("9785171025212")
                .releaseDate(dateMock1)
                .quantity(2)
                .authorIds(Arrays.asList(1L))
                .genreIds(Arrays.asList(9L))
                .build());
        list.add(Book.builder()
                .id(2L)
                .title("Hamlet. Macbeth")
                .isbn("9785171081591")
                .releaseDate(dateMock2)
                .quantity(2)
                .authorIds(Arrays.asList(1L))
                .genreIds(Arrays.asList(9L))
                .build());
    }

    @Override
    public void insert(Book t) {
        if (list.contains(t)) {
            throw new RuntimeException("Can't add entity to dao, entity already exists " + t.toString());
        }
        list.add(t);
    }

    @Override
    public void delete(Book t) {
        int index = DaoUtils.getIndexOfEntity(t, list);
        if (index == -1) {
            throw new RuntimeException("Can't delete entity from dao, entity doesn't exist " + t.toString());
        }
        list.remove(index);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(list);
    }

    @Override
    public void update(Book t) {
        int index = DaoUtils.getIndexOfEntity(t, list);
        if (index == -1) {
            throw new RuntimeException("Can't update entity, dao doesn't contain this entity " + t.toString());
        }
        list.set(index, t);
    }

}

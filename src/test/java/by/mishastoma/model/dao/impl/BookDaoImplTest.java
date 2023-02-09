package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.entity.BookEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {LiquibaseConfig.class, HibernateConfig.class, BookDaoImpl.class},
        loader = AnnotationConfigContextLoader.class)
public class BookDaoImplTest {

    private static final Integer EXPECTED_ID = 1;
    private static final String EXPECTED_TITLE = "Romeo and Juliet";
    private static final String EXPECTED_ISBN = "9785171025212";
    private static final LocalDate EXPECTED_RELEASE_DATE = LocalDate.parse("2020-01-01");
    private static final String SAVE_TITLE = "Test";
    private static final String SAVE_ISBN = "9999999999999";
    private static final LocalDate SAVE_RELEASE_DATE = LocalDate.parse("2023-01-01");
    private static BookEntity expectedEntity;
    private static BookEntity saveEntity;

    @Autowired
    private BookDao dao;

    @Before
    public void setUp() {
        expectedEntity = BookEntity.builder()
                .id(EXPECTED_ID)
                .title(EXPECTED_TITLE)
                .isbn(EXPECTED_ISBN)
                .releaseDate(EXPECTED_RELEASE_DATE)
                .build();

        saveEntity = BookEntity.builder()
                .title(SAVE_TITLE)
                .isbn(SAVE_ISBN)
                .releaseDate(SAVE_RELEASE_DATE)
                .build();
    }

    @Test
    public void saveTest() {
        dao.save(saveEntity);
    }

    @Test
    public void updateTest() {
        dao.update(expectedEntity);
    }

    @Test
    public void deleteTest() {
        dao.delete(expectedEntity);
    }

    @Test
    public void getTest() {
        BookEntity actualEntity = dao.findById(EXPECTED_ID);
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findBookByUdJpql() {
        BookEntity actualEntity = dao.findBookByIdJpql(EXPECTED_ID);
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findBookByEntityGraph() {
        BookEntity actualEntity = dao.findBookByIdEntityGraph(EXPECTED_ID);
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findBookByIdCriteria() {
        BookEntity actualEntity = dao.findBookByIdCriteria(EXPECTED_ID);
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findBookByIsbn() {
        BookEntity actualEntity = dao.findBookByIsbn(EXPECTED_ISBN);
        Assert.assertEquals(expectedEntity, actualEntity);
    }
}

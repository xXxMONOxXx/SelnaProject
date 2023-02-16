package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.entity.Book;
import by.mishastoma.model.entity.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {LiquibaseConfig.class, HibernateConfig.class, BookDaoImpl.class},
        loader = AnnotationConfigContextLoader.class)
public class BookDaoImplTest {

    private static final Long EXPECTED_ID = 1L;
    private static final String EXPECTED_TITLE = "Romeo and Juliet";
    private static final String UPDATE_TITLE = "Update test";
    private static final String EXPECTED_ISBN = "9785171025212";
    private static final Date EXPECTED_RELEASE_DATE = Date.valueOf("2020-01-01");
    private static final String SAVE_TITLE = "Test";
    private static final String SAVE_ISBN = "9999999999999";
    private static final Date SAVE_RELEASE_DATE = Date.valueOf("2023-01-01");
    private static final Long EXPECTED_ITEM_ID = 1L;
    private static final Long SAVE_ID = 6L;
    private static final Long DELETE_ID = 2L;
    private static Item expectedItem;
    private static Set<Item> expectedItems;
    private static Book expectedEntity;
    private static Book saveEntity;

    @Autowired
    private BookDao dao;

    @Before
    public void setUp() {

        expectedItems = new HashSet<>();
        expectedItem = Item.builder()
                .id(EXPECTED_ITEM_ID)
                .bookId(EXPECTED_ID)
                .build();
        expectedItems.add(expectedItem);
        expectedEntity = Book.builder()
                .id(EXPECTED_ID)
                .title(EXPECTED_TITLE)
                .isbn(EXPECTED_ISBN)
                .releaseDate(EXPECTED_RELEASE_DATE)
                .build();
        saveEntity = Book.builder()
                .title(SAVE_TITLE)
                .isbn(SAVE_ISBN)
                .releaseDate(SAVE_RELEASE_DATE)
                .build();
    }

    @Test
    public void saveTest() {
        dao.save(saveEntity);
        Book actualEntity = dao.findById(SAVE_ID).get();
        Assert.assertEquals(saveEntity, actualEntity);
    }

    @Test
    public void updateTest() {
        expectedEntity.setTitle(UPDATE_TITLE);
        dao.update(expectedEntity);
        Book actualEntity = dao.findById(EXPECTED_ID).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void deleteTest() {
        Book deleteEntity = dao.findById(DELETE_ID).get();
        dao.delete(deleteEntity);
        Assert.assertTrue(dao.findById(DELETE_ID).isEmpty());
    }

    @Test
    public void getTest() {
        Book actualEntity = dao.findById(EXPECTED_ID).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findBookByUdJpql() {
        Book actualEntity = dao.findByIdJpql(EXPECTED_ID).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findBookByEntityGraph() {
        Book actualEntity = dao.findByIdEntityGraph(EXPECTED_ID).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findBookByIdCriteria() {
        Book actualEntity = dao.findByIdCriteria(EXPECTED_ID).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findBookByIsbn() {
        Book actualEntity = dao.findByIsbn(EXPECTED_ISBN).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }
}

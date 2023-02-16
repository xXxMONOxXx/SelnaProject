package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.ItemDao;
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

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {LiquibaseConfig.class, HibernateConfig.class, ItemDaoImpl.class},
        loader = AnnotationConfigContextLoader.class)
public class ItemDaoImplTest {

    private static final Long EXPECTED_ID = 1L;
    private static final Date EXPECTED_TAKING_DATE = Date.valueOf("2023-02-08");
    private static final Date EXPECTED_EXPIRATION_DATE = Date.valueOf("2023-02-23");
    private static final Date SAVE_TAKING_DATE = Date.valueOf("2023-01-29");
    private static final Date SAVE_EXPIRATION_DATE = Date.valueOf("2023-02-19");
    private static final Date UPDATE_TAKING_DATE = Date.valueOf("2023-02-09");
    private static final Long EXPECTED_BOOK_ID = 1L;
    private static final Long SAVE_BOOK_ID = 1L;
    private static final Long SAVE_ID = 10L;
    private static final Long DELETE_ID = 2L;
    private static Item expectedEntity;
    private static Item saveEntity;

    @Autowired
    private ItemDao dao;

    @Before
    public void setUp() {
        expectedEntity = Item.builder()
                .id(EXPECTED_ID)
                .bookId(EXPECTED_BOOK_ID)
                .takingDate(EXPECTED_TAKING_DATE)
                .expirationDate(EXPECTED_EXPIRATION_DATE)
                .build();

        saveEntity = Item.builder()
                .bookId(SAVE_BOOK_ID)
                .takingDate(SAVE_TAKING_DATE)
                .expirationDate(SAVE_EXPIRATION_DATE)
                .build();
    }

    @Test
    public void saveTest() {
        dao.save(saveEntity);
        Item actualEntity = dao.findById(SAVE_ID).get();
        saveEntity.setId(SAVE_ID);
        Assert.assertEquals(actualEntity, saveEntity);

    }

    @Test
    public void updateTest() {
        expectedEntity.setTakingDate(UPDATE_TAKING_DATE);
        dao.update(expectedEntity);
        Item actualEntity = dao.findById(EXPECTED_ID).get();
        Assert.assertEquals(actualEntity, expectedEntity);
    }

    @Test
    public void deleteTest() {
        Item deleteEntity = dao.findById(DELETE_ID).get();
        dao.delete(deleteEntity);
        Assert.assertTrue(dao.findById(DELETE_ID).isEmpty());
    }

    @Test
    public void getTest() {
        Item actualEntity = dao.findById(EXPECTED_ID).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }
}

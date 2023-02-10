package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.entity.ItemEntity;
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
        classes = {LiquibaseConfig.class, HibernateConfig.class, ItemDaoImpl.class},
        loader = AnnotationConfigContextLoader.class)
public class ItemDaoImplTest {

    private static final Integer EXPECTED_ID = 1;
    private static final LocalDate EXPECTED_TAKING_DATE = LocalDate.parse("2023-02-08");
    private static final LocalDate EXPECTED_EXPIRATION_DATE = LocalDate.parse("2023-02-23");
    private static final LocalDate SAVE_TAKING_DATE = LocalDate.parse("2023-01-29");
    private static final LocalDate SAVE_EXPIRATION_DATE = LocalDate.parse("2023-02-19");
    private static final Integer EXPECTED_BOOK_ID = 1;
    private static final int SAVE_BOOK_ID = 1;
    private static ItemEntity expectedEntity;
    private static ItemEntity saveEntity;

    @Autowired
    private ItemDao dao;

    @Before
    public void setUp() {
        expectedEntity = ItemEntity.builder()
                .id(EXPECTED_ID)
                .bookId(EXPECTED_BOOK_ID)
                .takingDate(EXPECTED_TAKING_DATE)
                .expirationDate(EXPECTED_EXPIRATION_DATE)
                .build();

        saveEntity = ItemEntity.builder()
                .bookId(SAVE_BOOK_ID)
                .takingDate(SAVE_TAKING_DATE)
                .expirationDate(SAVE_EXPIRATION_DATE)
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
        ItemEntity actualEntity = dao.findById(EXPECTED_ID);
        Assert.assertEquals(expectedEntity, actualEntity);
    }
}

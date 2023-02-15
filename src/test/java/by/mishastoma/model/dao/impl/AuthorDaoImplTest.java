package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.entity.Author;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {LiquibaseConfig.class, HibernateConfig.class, AuthorDaoImpl.class},
        loader = AnnotationConfigContextLoader.class)
public class AuthorDaoImplTest {
    private static final Long EXPECTED_ID = 1L;
    private static final String EXPECTED_UPDATE_PATRONYMIC = "Patrotest";
    private static final String EXPECTED_FIRSTNAME = "William";
    private static final String EXPECTED_SURNAME = "Shakespeare";
    private static final String SAVE_FIRSTNAME = "Test";
    private static final String SAVE_SURNAME = "Unique";
    private static final String SAVE_PATRONYMIC = "Name";
    private static final Long DELETE_ID = 2L;
    private static final Long SAVE_ID = 4L;
    private static Author expectedEntity;
    private static Author saveEntity;
    private static Author deleteEntity;

    @Autowired
    private AuthorDao dao;

    @Before
    public void setUp() {
        expectedEntity = Author.builder()
                .id(EXPECTED_ID)
                .firstname(EXPECTED_FIRSTNAME)
                .surname(EXPECTED_SURNAME)
                .build();

        saveEntity = Author.builder()
                .firstname(SAVE_FIRSTNAME)
                .surname(SAVE_SURNAME)
                .patronymic(SAVE_PATRONYMIC)
                .build();

        deleteEntity = Author.builder()
                .id(DELETE_ID)
                .build();
    }

    @Test
    public void saveTest() {
        dao.save(saveEntity);
        Author actualEntity = dao.findById(SAVE_ID).get();
        saveEntity.setId(SAVE_ID);
        Assert.assertEquals(saveEntity, actualEntity);
    }

    @Test
    public void updateTest() {
        expectedEntity.setPatronymic(EXPECTED_UPDATE_PATRONYMIC);
        dao.update(expectedEntity);
        Author actualEntity = dao.findById(EXPECTED_ID).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void getTest() {
        Author actualEntity = dao.findById(EXPECTED_ID).get();
        expectedEntity.setPatronymic(EXPECTED_UPDATE_PATRONYMIC);
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void deleteTest() {
        dao.delete(deleteEntity);
        Assert.assertTrue(dao.findById(DELETE_ID).isEmpty());
    }


}

package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.entity.AuthorEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {LiquibaseConfig.class, HibernateConfig.class, AuthorDaoImpl.class },
        loader = AnnotationConfigContextLoader.class)
public class AuthorDaoImplTest {
    private static final Integer EXPECTED_ID = 1;
    private static final String EXPECTED_FIRSTNAME = "William";
    private static final String EXPECTED_SURNAME = "Shakespeare";
    private static final String SAVE_FIRSTNAME = "Test";
    private static final String SAVE_SURNAME = "Unique";
    private static final String SAVE_PATRONYMIC = "Name";
    private static AuthorEntity expectedEntity;
    private static AuthorEntity saveEntity;

    @Autowired
    private AuthorDao dao;

    @Before
    public void setUp(){
        expectedEntity = AuthorEntity.builder()
                .id(EXPECTED_ID)
                .firstname(EXPECTED_FIRSTNAME)
                .surname(EXPECTED_SURNAME)
                .build();

        saveEntity = AuthorEntity.builder()
                .firstname(SAVE_FIRSTNAME)
                .surname(SAVE_SURNAME)
                .patronymic(SAVE_PATRONYMIC)
                .build();
    }

    @Test
    public void saveTest(){
        dao.save(saveEntity);
    }

    @Test
    public void updateTest(){
        dao.update(expectedEntity);
    }

    @Test
    public void deleteTest(){
        dao.delete(expectedEntity);
    }

    @Test
    public void getTest(){
        AuthorEntity actualEntity = dao.findById(EXPECTED_ID);
        Assert.assertEquals(expectedEntity, actualEntity);
    }
}

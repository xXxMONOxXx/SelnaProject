package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.entity.AuthorEntity;
import by.mishastoma.model.entity.GenreEntity;
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
        classes = {LiquibaseConfig.class, HibernateConfig.class, GenreDaoImpl.class },
        loader = AnnotationConfigContextLoader.class)
public class GenreDaoImplTest {
    private static final Integer EXPECTED_ID = 1;
    private static final String EXPECTED_GENRE = "Fantasy";
    private static final String SAVE_GENRE = "Test";
    private static GenreEntity expectedEntity;
    private static GenreEntity saveEntity;

    @Autowired
    private GenreDao dao;

    @Before
    public void setUp(){
        expectedEntity = GenreEntity.builder()
                .id(EXPECTED_ID)
                .genre(EXPECTED_GENRE)
                .build();

        saveEntity = GenreEntity.builder()
                .genre(SAVE_GENRE)
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
        GenreEntity actualEntity = dao.findById(EXPECTED_ID);
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findByGenre(){
        GenreEntity actualEntity = dao.findByGenreName(EXPECTED_GENRE);
        Assert.assertEquals(expectedEntity, actualEntity);
    }
}

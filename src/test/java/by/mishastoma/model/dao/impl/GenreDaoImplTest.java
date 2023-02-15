package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.entity.Genre;
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
        classes = {LiquibaseConfig.class, HibernateConfig.class, GenreDaoImpl.class},
        loader = AnnotationConfigContextLoader.class)
public class GenreDaoImplTest {
    private static final Long EXPECTED_ID = 1L;
    private static final String EXPECTED_GENRE = "Fantasy";
    private static final String SAVE_GENRE = "Test";
    private static final Long SAVE_ID = 12L;
    private static final String UPDATE_NAME = "Testupdt";
    private static final Long DELETE_ID = 2L;
    private static Genre expectedEntity;
    private static Genre deleteGenre;
    private static Genre saveEntity;

    @Autowired
    private GenreDao dao;

    @Before
    public void setUp() {
        expectedEntity = Genre.builder()
                .id(EXPECTED_ID)
                .name(EXPECTED_GENRE)
                .build();

        saveEntity = Genre.builder()
                .name(SAVE_GENRE)
                .build();

        deleteGenre = Genre.builder()
                .id(DELETE_ID)
                .build();
    }

    @Test
    public void saveTest() {
        dao.save(saveEntity);
        Genre actualEntity = dao.findById(SAVE_ID).get();
        Assert.assertEquals(actualEntity, saveEntity);
    }

    @Test
    public void updateTest() {
        expectedEntity.setName(UPDATE_NAME);
        dao.update(expectedEntity);
        Genre actualEntity = dao.findById(EXPECTED_ID).get();
        Assert.assertEquals(actualEntity, expectedEntity);
    }

    @Transactional
    @Test
    public void findByGenre() {
        Genre actualEntity = dao.findByName(EXPECTED_GENRE).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void deleteTest() {
        dao.delete(deleteGenre);
        Assert.assertTrue(dao.findById(DELETE_ID).isEmpty());
    }

    @Test
    public void getTest() {
        Genre actualEntity = dao.findById(EXPECTED_ID).get();
        expectedEntity.setName(UPDATE_NAME);
        Assert.assertEquals(expectedEntity, actualEntity);
    }
}

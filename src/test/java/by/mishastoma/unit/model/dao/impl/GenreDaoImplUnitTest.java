package by.mishastoma.unit.model.dao.impl;

import by.mishastoma.config.db.HibernateConfig;
import by.mishastoma.config.db.LiquibaseConfig;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.dao.impl.GenreDaoImpl;
import by.mishastoma.model.entity.Genre;
import by.mishastoma.util.TestUtils;
import org.junit.Assert;
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
public class GenreDaoImplUnitTest {

    @Autowired
    private GenreDao genreDao;


    @Test
    public void saveTest() {
        //preparation
        Genre expectedGenre = TestUtils.buildSaveGenre();
        //when
        Genre returnedGenre = genreDao.save(expectedGenre);
        //then
        Genre savedGenre = genreDao.findById(returnedGenre.getId()).get();
        Assert.assertEquals(expectedGenre, returnedGenre);
        Assert.assertEquals(returnedGenre, savedGenre);
    }

    @Test
    public void updateTest() {
        //preparation
        Genre expectedGenre = TestUtils.buildUpdateGenre();
        //when
        genreDao.update(expectedGenre);
        //then
        Genre actualGenre = genreDao.findById(expectedGenre.getId()).get();
        Assert.assertEquals(expectedGenre, actualGenre);
    }

    @Test
    public void findByGenre() {
        //preparation
        Genre expectedGenre = TestUtils.buildDefaultGenre();
        //when
        Genre actualGenre = genreDao.findByName(expectedGenre.getName()).get();
        //then
        Assert.assertEquals(expectedGenre, actualGenre);
    }

    @Test
    public void deleteTest() {
        //preparation
        Genre defaultGenre = TestUtils.buildDefaultGenre();
        Genre foundGenre = genreDao.findById(defaultGenre.getId()).get();
        //when
        genreDao.delete(foundGenre);
        //then
        Assert.assertTrue(genreDao.findById(defaultGenre.getId()).isEmpty());
    }

    @Test
    public void getTest() {
        //preparation
        Genre expectedGenre = TestUtils.buildDefaultGenre();
        //when
        Genre actualGenre = genreDao.findById(expectedGenre.getId()).get();
        //then
        Assert.assertEquals(expectedGenre, actualGenre);
    }
}

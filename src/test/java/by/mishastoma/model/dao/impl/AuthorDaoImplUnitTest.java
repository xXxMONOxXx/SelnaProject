package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.entity.Author;
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
        classes = {LiquibaseConfig.class, HibernateConfig.class, AuthorDaoImpl.class},
        loader = AnnotationConfigContextLoader.class)
public class AuthorDaoImplUnitTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void saveTest() {
        //preparation
        Author expectedAuthor = TestUtils.buildSaveAuthor();
        //when
        authorDao.save(expectedAuthor);
        //then
        Author actualAuthor = authorDao.findById(expectedAuthor.getId()).get();
        Assert.assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    public void updateTest() {
        //preparation
        Author expectedAuthor = TestUtils.buildUpdateAuthor();
        authorDao.update(expectedAuthor);
        //when
        Author actualEntity = authorDao.findById(expectedAuthor.getId()).get();
        //then
        Assert.assertEquals(expectedAuthor, actualEntity);
    }

    @Test
    public void getTest() {
        //preparation
        Author expectedAuthor = TestUtils.buildDefaultAuthor();
        //when
        Author actualEntity = authorDao.findById(expectedAuthor.getId()).get();
        //then
        Assert.assertEquals(expectedAuthor, actualEntity);
    }

    @Test
    public void deleteTest() {
        //preparation
        Author defaultAuthor = TestUtils.buildDefaultAuthor();
        Author foundAuthor = authorDao.findById(defaultAuthor.getId()).get();
        //when
        authorDao.delete(foundAuthor);
        //then
        Assert.assertTrue(authorDao.findById(defaultAuthor.getId()).isEmpty());
    }


}

package by.mishastoma.model.dao.impl;

import by.mishastoma.config.db.HibernateConfig;
import by.mishastoma.config.db.LiquibaseConfig;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.entity.Book;
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
        classes = {LiquibaseConfig.class, HibernateConfig.class, BookDaoImpl.class},
        loader = AnnotationConfigContextLoader.class)
public class BookDaoImplUnitTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void saveTest() {
        //preparation
        Book expectedBook = TestUtils.buildSaveBook();
        //when
        Book returnedBook = bookDao.save(expectedBook);
        //then
        Book savedBook = bookDao.findById(returnedBook.getId()).get();
        Assert.assertEquals(expectedBook, returnedBook);
        Assert.assertEquals(returnedBook, savedBook);
    }

    @Test
    public void updateTest() {
        //preparation
        Book expectedBook = TestUtils.buildUpdateBook();
        //when
        bookDao.update(expectedBook);
        //then
        Book actualEntity = bookDao.findById(expectedBook.getId()).get();
        Assert.assertEquals(expectedBook, actualEntity);
    }

    @Test
    public void deleteTest() {
        //preparation
        Book deleteBook = TestUtils.buildGetBook();
        Book foundBook = bookDao.findById(deleteBook.getId()).get();
        //when
        bookDao.delete(foundBook);
        //then
        Assert.assertTrue(bookDao.findById(deleteBook.getId()).isEmpty());
    }

    @Test
    public void getTest() {
        //preparation
        Book expectedBook = TestUtils.buildDefaultBook();
        //when
        Book actualBook = bookDao.findById(expectedBook.getId()).get();
        //then
        Assert.assertEquals(expectedBook, actualBook);
    }

    @Test
    public void findBookByUdJpql() {
        //preparation
        Book expectedBook = TestUtils.buildDefaultBook();
        //when
        Book actualBook = bookDao.findByIdJpql(expectedBook.getId()).get();
        //then
        Assert.assertEquals(expectedBook, actualBook);
    }

    @Test
    public void findBookByEntityGraph() {
        //preparation
        Book expectedBook = TestUtils.buildDefaultBook();
        //when
        Book actualBook = bookDao.findByIdEntityGraph(expectedBook.getId()).get();
        //then
        Assert.assertEquals(expectedBook, actualBook);
    }

    @Test
    public void findBookByIdCriteria() {
        //preparation
        Book expectedBook = TestUtils.buildDefaultBook();
        //when
        Book actualBook = bookDao.findByIdCriteria(expectedBook.getId()).get();
        //then
        Assert.assertEquals(expectedBook, actualBook);
    }

    @Test
    public void findBookByIsbn() {
        //preparation
        Book expectedBook = TestUtils.buildDefaultBook();
        //when
        Book actualBook = bookDao.findByIsbn(expectedBook.getIsbn()).get();
        //then
        Assert.assertEquals(expectedBook, actualBook);
    }
}

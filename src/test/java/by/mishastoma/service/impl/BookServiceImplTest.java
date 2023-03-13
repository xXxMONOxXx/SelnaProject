package by.mishastoma.service.impl;

import by.mishastoma.config.ServiceTestConfig;
import by.mishastoma.config.mapper.MapperConfig;
import by.mishastoma.exception.BookNotFoundException;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.entity.Book;
import by.mishastoma.service.BookService;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.dto.BookDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.Serializable;
import java.util.Optional;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceTestConfig.class, MapperConfig.class})
public class BookServiceImplTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookService bookService;

    @Test
    public void saveTest() {
        //preparation
        BookDto bookDto = TestUtils.buildSaveBookDto();
        Book book = TestUtils.buildSaveBook();
        Mockito.when(bookDao.save(book)).thenReturn(book);
        //when
        BookDto returnedBook = bookService.save(bookDto);
        //then
        Mockito.verify(bookDao, Mockito.times(1)).save(book);
        Assert.assertEquals(bookDto, returnedBook);
    }

    @Test
    public void deleteTest() {
        Serializable id = TestUtils.buildDefaultBookDto().getId();
        Book book = TestUtils.buildDefaultBook();
        Mockito.doNothing().when(bookDao).delete(book);
        Mockito.when(bookDao.findById(id)).thenReturn(Optional.of(book));
        //when
        bookService.delete(id);
        //then
        Mockito.verify(bookDao, Mockito.times(1)).delete(book);
    }

    @Test
    public void updateTest() {
        //preparation
        BookDto bookDto = TestUtils.buildUpdateBookDto();
        Book book = TestUtils.buildUpdateBook();
        Mockito.doNothing().when(bookDao).delete(book);
        //when
        bookService.update(bookDto);
        //then
        Mockito.verify(bookDao, Mockito.times(1)).update(book);
    }

    @Test
    public void getTest() {
        //preparation
        BookDto expectedBook = TestUtils.buildGetBookDto();
        Serializable id = expectedBook.getId();
        Book book = TestUtils.buildGetBook();
        Mockito.when(bookDao.findById(id)).thenReturn(Optional.of(book));
        //when
        BookDto actualBook = bookService.findById(id);
        //then
        Mockito.verify(bookDao, Mockito.times(1)).findById(id);
        Assert.assertEquals(expectedBook, actualBook);
    }

    @Test(expected = BookNotFoundException.class)
    public void getTest_NotFound() {
        //preparation
        Serializable id = TestUtils.NOT_FOUND_ID;
        Mockito.when(bookDao.findById(id)).thenReturn(Optional.ofNullable(null));
        //when
        bookService.findById(id);
        //then
        Mockito.verify(bookDao, Mockito.times(1)).findById(id);
    }
}

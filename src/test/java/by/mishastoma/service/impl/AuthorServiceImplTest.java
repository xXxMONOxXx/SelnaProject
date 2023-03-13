package by.mishastoma.service.impl;

import by.mishastoma.config.ServiceTestConfig;
import by.mishastoma.config.mapper.MapperConfig;
import by.mishastoma.exception.AuthorNotFoundException;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.entity.Author;
import by.mishastoma.service.AuthorService;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.dto.AuthorDto;
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
public class AuthorServiceImplTest {
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private AuthorService authorService;

    @Test
    public void saveTest() {
        //preparation
        AuthorDto authorDto = TestUtils.buildSaveAuthorDto();
        Author author = TestUtils.buildSaveAuthor();
        Mockito.when(authorDao.save(author)).thenReturn(author);
        //when
        AuthorDto returnedAuthor = authorService.save(authorDto);
        //then
        Mockito.verify(authorDao, Mockito.times(1)).save(author);
        Assert.assertEquals(authorDto, returnedAuthor);
    }

    @Test
    public void deleteTest() {
        //preparation
        Serializable id = TestUtils.buildDefaultAuthorDto().getId();
        Author author = TestUtils.buildDefaultAuthor();
        Mockito.doNothing().when(authorDao).delete(author);
        Mockito.when(authorDao.findById(id)).thenReturn(Optional.of(author));
        //when
        authorService.delete(id);
        //then
        Mockito.verify(authorDao, Mockito.times(1)).delete(author);
    }

    @Test
    public void updateTest() {
        //preparation
        AuthorDto authorDto = TestUtils.buildUpdateAuthorDto();
        Author author = TestUtils.buildUpdateAuthor();
        Mockito.doNothing().when(authorDao).delete(author);
        //when
        authorService.update(authorDto);
        //then
        Mockito.verify(authorDao, Mockito.times(1)).update(author);
    }

    @Test
    public void getTest() {
        //preparation
        AuthorDto expectedAuthor = TestUtils.buildGetAuthorDto();
        Serializable id = expectedAuthor.getId();
        Author author = TestUtils.buildGetAuthor();
        Mockito.when(authorDao.findById(id)).thenReturn(Optional.of(author));
        //when
        AuthorDto actualAuthor = authorService.findById(id);
        //then
        Mockito.verify(authorDao, Mockito.times(1)).findById(id);
        Assert.assertEquals(expectedAuthor, actualAuthor);
    }

    @Test(expected = AuthorNotFoundException.class)
    public void getTest_NotFoundException() {
        //preparation
        Serializable id = TestUtils.NOT_FOUND_ID;
        Mockito.when(authorDao.findById(id)).thenReturn(Optional.ofNullable(null));
        //when
        authorService.findById(id);
        //then
        Mockito.verify(authorDao, Mockito.times(1)).findById(id);
    }

}

package by.mishastoma.service.impl;

import by.mishastoma.config.ServiceTestConfig;
import by.mishastoma.config.mapper.MapperConfig;
import by.mishastoma.exception.GenreNotFoundException;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.entity.Genre;
import by.mishastoma.service.GenreService;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.dto.GenreDto;
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
public class GenreServiceImplTest {

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private GenreService genreService;

    @Test
    public void saveTest() {
        //preparation
        GenreDto genreDto = TestUtils.buildSaveGenreDto();
        Genre genre = TestUtils.buildSaveGenre();
        Mockito.when(genreDao.save(genre)).thenReturn(genre);
        //when
        GenreDto returnedGenre = genreService.save(genreDto);
        //then
        Mockito.verify(genreDao, Mockito.times(1)).save(genre);
        Assert.assertEquals(genreDto, returnedGenre);
    }

    @Test
    public void deleteTest() {
        //preparation
        Serializable id = TestUtils.buildDefaultGenreDto().getId();
        Genre genre = TestUtils.buildDefaultGenre();
        Mockito.doNothing().when(genreDao).delete(genre);
        Mockito.when(genreDao.findById(id)).thenReturn(Optional.of(genre));
        //when
        genreService.delete(id);
        //then
        Mockito.verify(genreDao, Mockito.times(1)).delete(genre);
    }

    @Test
    public void updateTest() {
        //preparation
        GenreDto genreDto = TestUtils.buildUpdateGenreDto();
        Genre genre = TestUtils.buildUpdateGenre();
        Mockito.doNothing().when(genreDao).delete(genre);
        //when
        genreService.update(genreDto);
        //then
        Mockito.verify(genreDao, Mockito.times(1)).update(genre);
    }

    @Test
    public void getTest() {
        //preparation
        GenreDto expectedGenre = TestUtils.buildGetGenreDto();
        Serializable id = expectedGenre.getId();
        Genre genre = TestUtils.buildGetGenre();
        Mockito.when(genreDao.findById(id)).thenReturn(Optional.of(genre));
        //when
        GenreDto actualGenre = genreService.findById(id);
        //then
        Mockito.verify(genreDao, Mockito.times(1)).findById(id);
        Assert.assertEquals(expectedGenre, actualGenre);
    }

    @Test(expected = GenreNotFoundException.class)
    public void getTest_NotFound() {
        //preparation
        Serializable id = TestUtils.NOT_FOUND_ID;
        Mockito.when(genreDao.findById(id)).thenReturn(Optional.ofNullable(null));
        //when
        genreService.findById(id);
        //then
        Mockito.verify(genreDao, Mockito.times(1)).findById(id);
    }
}

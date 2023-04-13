package by.mishastoma.unit.contoller.impl;

import by.mishastoma.config.ControllerTestConfig;
import by.mishastoma.config.mapper.MapperConfig;
import by.mishastoma.exception.GenreNotFoundException;
import by.mishastoma.service.GenreService;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.controller.impl.GenreControllerImpl;
import by.mishastoma.web.dto.GenreDto;
import by.mishastoma.web.handler.ControllerExceptionHandler;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.Serializable;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ControllerTestConfig.class, MapperConfig.class})
public class GenreControllerImplTest {

    private MockMvc mockMvc;

    @Autowired
    private GenreControllerImpl genreController;

    @Autowired
    private GenreService genreService;

    @Test
    public void deleteTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
        Serializable id = TestUtils.buildDefaultGenre().getId();
        Mockito.doNothing().when(genreService).delete(id);
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/genres/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        //then
        Mockito.verify(genreService, Mockito.times(1)).delete(id);
    }

    @Test
    public void addTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
        GenreDto genreDto = TestUtils.buildSaveGenreDto();
        Mockito.when(genreService.save(genreDto)).thenReturn(genreDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/genres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildDefaultGenreJson()))
                .andExpect(status().isCreated());
        //then
        Mockito.verify(genreService, Mockito.times(1)).save(genreDto);
    }

    @Test
    public void updateTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
        GenreDto genreDto = TestUtils.buildUpdateGenreDto();
        Mockito.doNothing().when(genreService).update(genreDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/genres/{id}", genreDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildUpdateGenreJson()))
                .andExpect(status().isOk());
        //then
        Mockito.verify(genreService, Mockito.times(1)).update(genreDto);
    }

    @Test
    public void findByIdTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
        GenreDto genreDto = TestUtils.buildGetGenreDto();
        Serializable id = genreDto.getId();
        Mockito.when(genreService.findById(id)).thenReturn(genreDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(genreDto.getId()))
                .andExpect(jsonPath("name").value(genreDto.getName()));
        //then
        Mockito.verify(genreService, Mockito.times(1)).findById(id);
    }

    @Test
    public void findByIdTest_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(genreController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
        Serializable id = TestUtils.NOT_FOUND_ID;
        Mockito.when(genreService.findById(id)).thenThrow(GenreNotFoundException.class);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        //then
        Mockito.verify(genreService, Mockito.times(1)).findById(id);
    }

    @Test
    public void findByName() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
        GenreDto genreDto = TestUtils.buildGetGenreDto();
        String name = genreDto.getName();
        Mockito.when(genreService.findGenreByName(name)).thenReturn(genreDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/?name=" + name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(genreDto.getId()))
                .andExpect(jsonPath("name").value(genreDto.getName()));
        //then
        Mockito.verify(genreService, Mockito.times(1)).findGenreByName(name);
    }

    @Test
    public void findByName_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(genreController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
        String name = TestUtils.buildDefaultGenre().getName();
        Mockito.when(genreService.findGenreByName(name)).thenThrow(GenreNotFoundException.class);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/?name=" + name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        //then
        Mockito.verify(genreService, Mockito.times(1)).findGenreByName(name);
    }
}

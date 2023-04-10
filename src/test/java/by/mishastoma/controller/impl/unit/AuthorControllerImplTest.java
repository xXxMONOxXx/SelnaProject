package by.mishastoma.controller.impl.unit;

import by.mishastoma.config.ControllerTestConfig;
import by.mishastoma.config.mapper.MapperConfig;
import by.mishastoma.exception.AuthorNotFoundException;
import by.mishastoma.service.AuthorService;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.controller.impl.AuthorControllerImpl;
import by.mishastoma.web.dto.AuthorDto;
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
public class AuthorControllerImplTest {

    private MockMvc mockMvc;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorControllerImpl authorController;

    @Test
    public void deleteTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
        Serializable id = TestUtils.buildDefaultAuthor().getId();
        Mockito.doNothing().when(authorService).delete(id);
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //then
        Mockito.verify(authorService, Mockito.times(1)).delete(id);
    }

    @Test
    public void addTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
        AuthorDto authorDto = TestUtils.buildSaveAuthorDto();
        Mockito.when(authorService.save(authorDto)).thenReturn(authorDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildDefaultAuthorJson()))
                .andExpect(status().isCreated());
        //then
        Mockito.verify(authorService, Mockito.times(1)).save(authorDto);
    }

    @Test
    public void updateTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
        AuthorDto authorDto = TestUtils.buildUpdateAuthorDto();
        Mockito.doNothing().when(authorService).update(authorDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/authors/{id}", authorDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildUpdateAuthorJson()))
                .andExpect(status().isOk());
        //then
        Mockito.verify(authorService, Mockito.times(1)).update(authorDto);
    }

    @Test
    public void findByIdTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
        AuthorDto authorDto = TestUtils.buildGetAuthorDto();
        Serializable id = authorDto.getId();
        Mockito.when(authorService.findById(id)).thenReturn(authorDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(authorDto.getId()))
                .andExpect(jsonPath("firstname").value(authorDto.getFirstname()))
                .andExpect(jsonPath("surname").value(authorDto.getSurname()));
        //then
        Mockito.verify(authorService, Mockito.times(1)).findById(id);
    }

    @Test
    public void findByIdTest_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(authorController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
        Serializable id = TestUtils.NOT_FOUND_ID;
        Mockito.when(authorService.findById(id)).thenThrow(AuthorNotFoundException.class);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        //then
        Mockito.verify(authorService, Mockito.times(1)).findById(id);
    }
}

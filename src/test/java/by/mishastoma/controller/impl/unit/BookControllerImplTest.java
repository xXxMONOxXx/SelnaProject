package by.mishastoma.controller.impl.unit;

import by.mishastoma.config.ControllerTestConfig;
import by.mishastoma.config.mapper.MapperConfig;
import by.mishastoma.exception.BookNotFoundException;
import by.mishastoma.service.BookService;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.controller.impl.BookControllerImpl;
import by.mishastoma.web.dto.BookDto;
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
public class BookControllerImplTest {

    private MockMvc mockMvc;

    @Autowired
    private BookControllerImpl bookController;

    @Autowired
    private BookService bookService;

    @Test
    public void deleteTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        Serializable id = TestUtils.buildDefaultBook().getId();
        Mockito.doNothing().when(bookService).delete(id);
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //then
        Mockito.verify(bookService, Mockito.times(1)).delete(id);
    }

    @Test
    public void addTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        BookDto bookDto = TestUtils.buildSaveBookDto();
        Mockito.when(bookService.save(bookDto)).thenReturn(bookDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildDefaultBookJson()))
                .andExpect(status().isCreated());
        //then
        Mockito.verify(bookService, Mockito.times(1)).save(bookDto);
    }

    @Test
    public void updateTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        BookDto bookDto = TestUtils.buildUpdateBookDto();
        Mockito.doNothing().when(bookService).update(bookDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/books/{id}", bookDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildUpdateBookJson()))
                .andExpect(status().isOk());
        //then
        Mockito.verify(bookService, Mockito.times(1)).update(bookDto);
    }

    @Test
    public void findByIdTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        BookDto bookDto = TestUtils.buildGetBookDto();
        Serializable id = bookDto.getId();
        Mockito.when(bookService.findById(id)).thenReturn(bookDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(bookDto.getId()));
        //then
        Mockito.verify(bookService, Mockito.times(1)).findById(id);
    }

    @Test
    public void findByIdTest_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
        Serializable id = TestUtils.NOT_FOUND_ID;
        Mockito.when(bookService.findById(id)).thenThrow(BookNotFoundException.class);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        //then
        Mockito.verify(bookService, Mockito.times(1)).findById(id);
    }

    @Test
    public void findByIsbn() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        BookDto bookDto = TestUtils.buildGetBookDto();
        String isbn = TestUtils.buildDefaultBook().getIsbn();
        Mockito.when(bookService.findBookByIsbn(isbn)).thenReturn(bookDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/books/?isbn=" + isbn)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(bookDto.getId()))
                .andExpect(jsonPath("title").value(bookDto.getTitle()))
                .andExpect(jsonPath("isbn").value(bookDto.getIsbn()))
                .andExpect(jsonPath("releaseDate").value(bookDto.getReleaseDate().getTime()));
        //then
        Mockito.verify(bookService, Mockito.times(1)).findBookByIsbn(isbn);
    }

    @Test
    public void findByIsbn_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
        String isbn = TestUtils.buildSaveBook().getIsbn();
        Mockito.when(bookService.findBookByIsbn(isbn)).thenThrow(BookNotFoundException.class);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/books/?isbn=" + isbn)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        //then
        Mockito.verify(bookService, Mockito.times(1)).findBookByIsbn(isbn);
    }
}

package by.mishastoma.controller.impl.it;

import by.mishastoma.config.AppConfig;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.dto.BookDto;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class BookIT {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext applicationContext;

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void deleteTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        Serializable id = TestUtils.buildDefaultBook().getId();
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void addTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        BookDto bookDto = TestUtils.buildSaveBookDto();
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildDefaultBookJson()))
                //then
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void updateTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        BookDto bookDto = TestUtils.buildUpdateBookDto();
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/books/{id}", bookDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildUpdateBookJson()))
                //then
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        BookDto bookDto = TestUtils.buildGetBookDto();
        Serializable id = bookDto.getId();
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(bookDto.getId()));
    }

    @Test
    public void findByIdTest_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        Serializable id = TestUtils.NOT_FOUND_ID;
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());
    }

    @Test
    public void findByIsbn() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        BookDto bookDto = TestUtils.buildDefaultItForBook();
        String isbn = TestUtils.buildDefaultBook().getIsbn();
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/books/?isbn=" + isbn)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(bookDto.getId()))
                .andExpect(jsonPath("title").value(bookDto.getTitle()))
                .andExpect(jsonPath("isbn").value(bookDto.getIsbn()));
    }

    @Test
    public void findByIsbn_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        String isbn = TestUtils.ISBN_NOT_FOUND;
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/books/?isbn=" + isbn)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());
    }
}
